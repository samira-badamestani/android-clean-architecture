package com.architecture.clean.data.mapper

import com.architecture.clean.domain.model.response.DomainErrorException
import com.architecture.clean.domain.model.response.ErrorModel
import com.architecture.clean.domain.model.response.ErrorStatus
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class CloudErrorMapper @Inject constructor(private val gson: Gson) {

    fun mapToDomainErrorException(throwable: Throwable?): Throwable? {
        val errorModel: ErrorModel? = when (throwable) {

        // if throwable is an instance of HttpException
        // then attempt to parse error data from response body
            is HttpException -> {
                // handle UNAUTHORIZED situation (when token expired)
                if (throwable.code() == 401) {
                    ErrorModel(ErrorStatus.UNAUTHORIZED)
                } else {
                    getHttpError(throwable.response().errorBody())
                }
            }

        // handle api call timeout error
            is SocketTimeoutException -> {
                ErrorModel(ErrorStatus.TIMEOUT)
            }

        // handle connection error
            is IOException -> {
                ErrorModel(ErrorStatus.NO_CONNECTION)
            }
            else -> null
        }
        return errorModel?.let { DomainErrorException(it) } ?: throwable
    }

    /**
     * attempts to parse http response body and get error data from it
     *
     * @param body retrofit response body
     * @return returns an instance of [ErrorModel] with parsed data or NOT_DEFINED status
     */
    private fun getHttpError(body: ResponseBody?): ErrorModel {
        return try {
            // use response body to get error detail
            ErrorModel("error detail", 400, ErrorStatus.BAD_RESPONSE)
        } catch (e: Throwable) {
            e.printStackTrace()
            ErrorModel(ErrorStatus.NOT_DEFINED)
        }

    }
}