package com.architecture.clean.domain.usecase

import com.architecture.clean.domain.mapper.DomainErrorUtil
import com.architecture.clean.domain.model.FoodDto
import com.architecture.clean.domain.repository.AppRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetHomeUseCaseTest {
    @Mock
    lateinit var errorUtil: DomainErrorUtil

    @Mock
    lateinit var appRepository: AppRepository

    @InjectMocks
    lateinit var getHomeUseCase: GetHomeUseCase

    @Test
    fun `execute should get a single instance from repository`() {
        // GIVEN
        val mockedSingle = mock<Single<FoodDto>>()
        whenever(appRepository.getHome()).thenReturn(mockedSingle)

        // WHEN
        val result = getHomeUseCase.execute()

        // THEN
        MatcherAssert.assertThat(result, CoreMatchers.`is`(mockedSingle))
    }
}