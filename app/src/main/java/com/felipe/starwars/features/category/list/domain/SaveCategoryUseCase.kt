package com.felipe.starwars.features.category.list.domain

import com.felipe.starwars.base.presentation.Response
import com.felipe.starwars.features.category.data.CategoriesRepository

interface SaveCategoryUseCase {
    suspend operator fun invoke(category: Category): Response<Long>
}

class SaveCategoryUseCaseImpl(
    private val repository: CategoriesRepository,
) : SaveCategoryUseCase {

    override suspend operator fun invoke(category: Category): Response<Long> {
        return try {
            Response.Success(repository.saveLocal(category.copy(isFavorite = true)))
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}
