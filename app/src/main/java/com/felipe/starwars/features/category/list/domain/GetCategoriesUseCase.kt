package com.felipe.starwars.features.category.list.domain

import com.felipe.starwars.base.presentation.Response
import com.felipe.starwars.features.category.data.CategoriesRepository
import com.felipe.starwars.features.category.list.CategoriesFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

interface GetCategoriesUseCase {
    suspend operator fun invoke(filter: CategoriesFilter): Flow<Response<List<Category>>>
}

class GetCategoriesUseCaseImpl(
    private val repository: CategoriesRepository,
    private val mapper: CategoryMapper
) : GetCategoriesUseCase {

    override suspend operator fun invoke(filter: CategoriesFilter): Flow<Response<List<Category>>> {
        return try {
            when (filter) {
                CategoriesFilter.LOCAL -> getOnlyLocal()
                CategoriesFilter.REMOTE ->
                    getRemote()
            }
        } catch (e: Exception) {
            flow { Response.Error<List<Category>>(e) }
        }
    }

    private fun getRemote() = flow {
        emit(repository.getCategories())
    }.combine(repository.getAllLocal()) { remote, local ->
        Response.Success(mapper.parseCategories(local, remote))
    }

    private fun getOnlyLocal(): Flow<Response.Success<List<Category>>> =
        repository.getAllLocal().map { Response.Success(it) }
}
