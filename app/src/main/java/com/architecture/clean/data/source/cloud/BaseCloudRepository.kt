package com.architecture.clean.data.source.cloud

import com.architecture.clean.domain.model.FoodDto
import io.reactivex.Single

interface BaseCloudRepository {
    fun getHome(): Single<FoodDto>
}