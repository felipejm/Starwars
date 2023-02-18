package com.felipe.starwars.features.category.data

import com.apollographql.apollo3.ApolloClient
import com.felipe.starwars.features.category.data.response.CategoryDetailPagedResponse
import com.felipe.starwars.features.category.data.response.FilmsResponse
import com.felipe.starwars.features.category.data.response.PeopleResponse
import com.felipe.starwars.features.category.data.response.PlanetResponse
import com.felipe.starwars.features.category.list.domain.Category
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    suspend fun getCategories(): Map<String, String>
    suspend fun saveLocal(category: Category): Long
    suspend fun deleteLocal(category: Category): Int
    fun getAllLocal(): Flow<List<Category>>
    suspend fun getPeople(): CategoryDetailPagedResponse<PeopleResponse>
    suspend fun getPlanets(): CategoryDetailPagedResponse<PlanetResponse>
    suspend fun getFilms(): CategoryDetailPagedResponse<FilmsResponse>
}

class CategoriesRepositoryImpl(
    private val apolloClient: ApolloClient,
    private val api: CategoryApi,
    private val dao: CategoryDao
) : CategoriesRepository {

    override suspend fun getCategories(): Map<String, String> {
        return api.getCategories()
    }

    override suspend fun saveLocal(category: Category): Long {
        return dao.insert(category)
    }

    override suspend fun deleteLocal(category: Category): Int {
        return dao.delete(category)
    }

    override fun getAllLocal(): Flow<List<Category>> {
        return dao.getAll()
    }

    override suspend fun getPeople(): CategoryDetailPagedResponse<PeopleResponse> {
        return api.getPeople()
    }

    override suspend fun getPlanets(): CategoryDetailPagedResponse<PlanetResponse> {
        return api.getPlanets()
    }

    override suspend fun getFilms(): CategoryDetailPagedResponse<FilmsResponse> {
        return api.getFilms()
    }
}
