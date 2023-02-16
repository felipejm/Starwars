package com.felipe.starwars.features.category.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.felipe.starwars.GetAllFilmsQuery
import com.felipe.starwars.GetAllPeopleQuery
import com.felipe.starwars.GetAllPlanetsQuery
import com.felipe.starwars.GetAllVehiclesQuery
import com.felipe.starwars.features.category.list.domain.Category
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    suspend fun getCategories(): Map<String, String>
    suspend fun getPlanets(): ApolloResponse<GetAllPlanetsQuery.Data>
    suspend fun getPeople(): ApolloResponse<GetAllPeopleQuery.Data>
    suspend fun getVehicles(): ApolloResponse<GetAllVehiclesQuery.Data>
    suspend fun getFilms(): ApolloResponse<GetAllFilmsQuery.Data>
    suspend fun saveLocal(category: Category): Long
    suspend fun deleteLocal(category: Category): Int
    fun getAllLocal(): Flow<List<Category>>
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

    override suspend fun getPlanets(): ApolloResponse<GetAllPlanetsQuery.Data> {
        return apolloClient.query(GetAllPlanetsQuery()).execute()
    }

    override suspend fun getPeople(): ApolloResponse<GetAllPeopleQuery.Data> {
        return apolloClient.query(GetAllPeopleQuery()).execute()
    }

    override suspend fun getVehicles(): ApolloResponse<GetAllVehiclesQuery.Data> {
        return apolloClient.query(GetAllVehiclesQuery()).execute()
    }

    override suspend fun getFilms(): ApolloResponse<GetAllFilmsQuery.Data> {
        return apolloClient.query(GetAllFilmsQuery()).execute()
    }
}
