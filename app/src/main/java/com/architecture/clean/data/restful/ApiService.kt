package com.architecture.clean.data.restful

import com.architecture.clean.domain.model.FoodDto
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("api/")
    fun getHome(
    ): Single<FoodDto>


}