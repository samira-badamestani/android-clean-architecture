package com.architecture.clean.domain.usecase

import com.architecture.clean.data.mapper.CloudErrorMapper
import com.architecture.clean.domain.mapper.DomainErrorUtil
import com.architecture.clean.domain.model.FoodDto
import com.architecture.clean.domain.repository.AppRepository
import com.architecture.clean.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetHomeUseCase @Inject constructor(
        errorUtil: CloudErrorMapper,
        private val appRepository: AppRepository
) : SingleUseCase<FoodDto>(errorUtil) {

    override fun execute(): Single<FoodDto> {
        return appRepository.getHome()
    }
}