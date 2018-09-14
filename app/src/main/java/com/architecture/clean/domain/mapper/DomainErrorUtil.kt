package com.architecture.clean.domain.mapper

import com.architecture.clean.domain.model.response.DomainErrorException
import com.architecture.clean.domain.model.response.ErrorModel
import com.architecture.clean.domain.model.response.ErrorStatus
import com.google.gson.Gson
import okhttp3.ResponseBody
import javax.inject.Inject

/**
 * A util class that generate an instance of [ErrorModel] with happened [Throwable]
 * @param gson an instance of [Gson] to parse error body from [ResponseBody]
 */
class DomainErrorUtil @Inject constructor(val gson: Gson) {

    /**
     * Generate an instance of [ErrorModel] with happened [Throwable]
     * @param t happened [Throwable]
     *
     * @return rentuns an instance of [ErrorModel]
     */
    fun getErrorModel(t: Throwable?): ErrorModel {
        if (t is DomainErrorException)
            return t.errorModel

        // if response was successful but no data received
        if (t is NullPointerException) {
            return ErrorModel(ErrorStatus.EMPTY_RESPONSE)
        }

        // something happened that we are not make our self ready for it
        return ErrorModel(ErrorStatus.NOT_DEFINED)
    }
}
