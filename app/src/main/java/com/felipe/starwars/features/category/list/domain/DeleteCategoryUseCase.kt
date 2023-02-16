package com.felipe.starwars.features.category.list.domain

import com.felipe.starwars.base.presentation.Response
import com.felipe.starwars.features.category.data.CategoriesRepository

interface DeleteCategoryUseCase {
    suspend operator fun invoke(category: Category): Response<Int>
}

class DeleteCategoryUseCaseImpl(
    private val repository: CategoriesRepository,
) : DeleteCategoryUseCase {

    override suspend operator fun invoke(category: Category): Response<Int> {
        return try {
            Response.Success(repository.deleteLocal(category))
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}
