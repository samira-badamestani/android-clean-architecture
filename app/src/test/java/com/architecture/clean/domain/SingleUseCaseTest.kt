package com.architecture.clean.domain


import com.architecture.clean.RxSchedulersOverrideRule
import com.architecture.clean.domain.mapper.DomainErrorUtil
import com.architecture.clean.domain.model.response.*
import com.architecture.clean.domain.usecase.base.SingleUseCase
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class SingleUseCaseTest {
    @get:Rule
    val rule: TestRule = RxSchedulersOverrideRule()

    @Mock
    lateinit var compositeDisposable: CompositeDisposable

    @Mock
    lateinit var onResponse: (UseCaseResponse<Any>) -> Unit

    @Mock
    lateinit var onTokenExpire: () -> Unit

    @Mock
    lateinit var errorUtil: DomainErrorUtil

    @Test
    fun `execute useCase should invoke callback with a valid response if everything is ok`() {
        // GIVEN
        val mockedResponse = mock<Any>()
        val testSingle = Single.just(mockedResponse)
        val singleUseCase = mock<SingleUseCase<Any>>(defaultAnswer = Mockito.CALLS_REAL_METHODS)
        whenever(singleUseCase.execute()).thenReturn(testSingle)

        // WHEN
        val disposable = singleUseCase.execute(compositeDisposable, onResponse, onTokenExpire)

        // THEN
        verify(onResponse).invoke(SuccessResponse(mockedResponse))
        verify(onTokenExpire, never()).invoke()
        verify(compositeDisposable).add(disposable)
    }

    @Test
    fun `execute use case should invoke callback with a ErrorModel if something went wrong`() {
        // GIVEN
        val t = mock<Throwable>()
        val mockedError = mock<ErrorModel>()
        val testSingle = Single.error<Any>(t)
        val singleUseCase = mock<SingleUseCase<Any>>(defaultAnswer = Mockito.CALLS_REAL_METHODS)
        whenever(errorUtil.getErrorModel(t)).thenReturn(mockedError)
        whenever(singleUseCase.errorUtil).thenReturn(errorUtil)
        whenever(singleUseCase.execute()).thenReturn(testSingle)

        // WHEN
        val disposable = singleUseCase.execute(compositeDisposable, onResponse, onTokenExpire)

        // THEN
        verify(onResponse).invoke(ErrorResponse(mockedError))
        verify(onTokenExpire, never()).invoke()
        verify(compositeDisposable).add(disposable)
    }

    @Test
    fun `execute use case should invoke onTokenExpire callback if an unauthorized error happened`() {
        // GIVEN
        val t = mock<Throwable>()
        val mockedError = mock<ErrorModel>()
        whenever(mockedError.errorStatus).thenReturn(ErrorStatus.UNAUTHORIZED)
        whenever(errorUtil.getErrorModel(t)).thenReturn(mockedError)
        val testSingle = Single.error<Any>(t)
        val singleUseCase = mock<SingleUseCase<Any>>(defaultAnswer = Mockito.CALLS_REAL_METHODS)
        whenever(singleUseCase.errorUtil).thenReturn(errorUtil)
        whenever(singleUseCase.execute()).thenReturn(testSingle)

        // WHEN
        val disposable = singleUseCase.execute(compositeDisposable, onResponse, onTokenExpire)

        // THEN
        verify(onResponse).invoke(ErrorResponse(mockedError))
        verify(onTokenExpire).invoke()
        verify(compositeDisposable).add(disposable)
    }
}