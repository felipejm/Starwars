package com.felipe.starwars.features.category.detail.domain

import com.felipe.starwars.features.category.data.CategoriesRepository
import com.felipe.starwars.features.category.list.domain.Category

interface GetCategoryDetailUseCase {
    suspend operator fun invoke(category: Category): List<CategoryDetail>
}

class GetCategoryDetailUseCaseImpl(
    private val repository: CategoriesRepository,
    private val mapper: CategoryDetailMapper
) : GetCategoryDetailUseCase {

    override suspend operator fun invoke(category: Category): List<CategoryDetail> {
        return listOf(CategoryDetail("", "", "", "", ""))
    }
}
