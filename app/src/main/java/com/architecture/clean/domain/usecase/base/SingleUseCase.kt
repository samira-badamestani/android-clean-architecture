package com.architecture.clean.domain.usecase.base

import com.architecture.clean.domain.mapper.DomainErrorUtil
import com.architecture.clean.domain.model.response.ErrorResponse
import com.architecture.clean.domain.model.response.ErrorStatus
import com.architecture.clean.domain.model.response.SuccessResponse
import com.architecture.clean.domain.model.response.UseCaseResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<T>( val errorUtil: DomainErrorUtil) : UseCase<Single<T>>() {

    fun execute(
            compositeDisposable: CompositeDisposable,
            onResponse: (UseCaseResponse<T>) -> Unit,
            onTokenExpire: (() -> Unit)? = null
    ): Disposable {
        return this.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            onResponse(SuccessResponse(it))
                        },
                        {
                            val error = errorUtil.getErrorModel(it)

                            if (error.errorStatus == ErrorStatus.UNAUTHORIZED) {
                                onTokenExpire?.invoke()
                            }
                            onResponse(ErrorResponse(error))

                        }).also { compositeDisposable.add(it) }
    }

}