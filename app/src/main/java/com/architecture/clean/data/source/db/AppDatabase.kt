package com.architecture.clean.data.source.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.architecture.clean.domain.model.Food

@Database(entities = [Food::class], version = AppDatabase.VERSION)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "food.db"
        const val VERSION = 1
    }
    abstract fun foodDao(): FoodDao
}