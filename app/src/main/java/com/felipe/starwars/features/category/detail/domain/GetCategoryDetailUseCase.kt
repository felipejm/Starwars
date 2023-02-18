package com.felipe.starwars.features.category.detail.domain

import com.felipe.starwars.base.presentation.Response
import com.felipe.starwars.features.category.data.CategoriesRepository
import java.util.Locale

interface GetCategoryDetailUseCase {
    suspend operator fun invoke(category: String): Response<List<CategoryDetail>>
}

class GetCategoryDetailUseCaseImpl(
    private val repository: CategoriesRepository,
    private val mapper: CategoryDetailMapper
) : GetCategoryDetailUseCase {

    override suspend operator fun invoke(category: String): Response<List<CategoryDetail>> {
        return try {
            return when (category.lowercase(Locale.getDefault())) {
                "films" ->
                    Response.Success(mapper.parseFilms(repository.getFilms().results.orEmpty()))
                "people" ->
                    Response.Success(mapper.parsePeople(repository.getPeople().results.orEmpty()))
                "planets" ->
                    Response.Success(mapper.parsePlanets(repository.getPlanets().results.orEmpty()))
                else -> Response.Error(RuntimeException(ERROR_CATEGORY_NOT_FOUND))
            }
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    private companion object {
        const val ERROR_CATEGORY_NOT_FOUND = "Category Not Found"
    }
}
