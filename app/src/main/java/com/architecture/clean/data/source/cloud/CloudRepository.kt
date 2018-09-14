package com.architecture.clean.data.source.cloud

import com.architecture.clean.data.restful.ApiService
import com.architecture.clean.domain.model.FoodDto
import io.reactivex.Single

class CloudRepository(private val apIs: ApiService) : BaseCloudRepository {
    override fun getHome(): Single<FoodDto> {
        return apIs.getHome()
    }


}
