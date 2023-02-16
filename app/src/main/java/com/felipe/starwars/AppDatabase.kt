package com.felipe.starwars

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.felipe.starwars.features.category.data.CategoryDao
import com.felipe.starwars.features.category.list.domain.Category

@Database(entities = [Category::class], version = 1)
@TypeConverters()
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}
