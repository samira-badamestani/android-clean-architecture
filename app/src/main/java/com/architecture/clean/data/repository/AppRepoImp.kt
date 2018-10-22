package com.architecture.clean.data.repository

import com.architecture.clean.data.source.cloud.BaseCloudRepository
import com.architecture.clean.data.source.db.FoodDao
import com.architecture.clean.domain.model.FoodDto
import com.architecture.clean.domain.repository.AppRepository
import io.reactivex.Single
import javax.inject.Inject

class AppRepoImp  @Inject constructor(
        private val cloudRepository: BaseCloudRepository,
        private val foodDao: FoodDao
): AppRepository {
    override fun getHome(): Single<FoodDto> {
        return cloudRepository
                .getHome().map(this::saveFoods)
    }

    private fun saveFoods(foodDto: FoodDto):FoodDto{
        if (foodDto.results.size>0){
            for (food in foodDto.results){
                foodDao.insertFood(food)
            }
        }
        return foodDto
    }
}