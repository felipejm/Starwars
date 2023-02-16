package com.felipe.starwars.features.category.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.felipe.starwars.features.category.list.domain.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category")
    fun getAll(): Flow<List<Category>>

    @Query("SELECT * FROM category WHERE title=:title ")
    suspend fun getByTitle(title: String): Category

    @Insert
    suspend fun insert(character: Category): Long

    @Delete
    suspend fun delete(character: Category): Int
}
