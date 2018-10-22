package com.architecture.clean.data.source.db

import android.arch.persistence.room.*
import com.architecture.clean.domain.model.Food


@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFood(food: Food): Long

    @Delete
    fun deleteFood(food: Food): Int

    @Query("SELECT * from Food")
    fun selectAllFoods(): MutableList<Food>

}