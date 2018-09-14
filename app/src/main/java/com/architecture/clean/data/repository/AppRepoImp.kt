package com.architecture.clean.data.repository

import com.architecture.clean.data.source.cloud.BaseCloudRepository
import com.architecture.clean.domain.model.FoodDto
import com.architecture.clean.domain.repository.AppRepository
import io.reactivex.Single
import javax.inject.Inject

class AppRepoImp  @Inject constructor(
        private val cloudRepository: BaseCloudRepository
): AppRepository {
    override fun getHome(): Single<FoodDto> {
        return cloudRepository
                .getHome()
    }


}