package com.architecture.clean.domain.repository

import com.architecture.clean.domain.model.FoodDto
import io.reactivex.Single

interface AppRepository{
    fun getHome(): Single<FoodDto>
}