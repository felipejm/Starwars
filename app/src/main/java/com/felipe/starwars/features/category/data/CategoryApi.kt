package com.felipe.starwars.features.category.data

import com.felipe.starwars.features.category.data.response.CategoryDetailPagedResponse
import com.felipe.starwars.features.category.data.response.FilmsResponse
import com.felipe.starwars.features.category.data.response.PeopleResponse
import com.felipe.starwars.features.category.data.response.PlanetResponse
import retrofit2.http.GET

interface CategoryApi {

    @GET("api/")
    suspend fun getCategories(): Map<String, String>

    @GET("api/people")
    suspend fun getPeople(): CategoryDetailPagedResponse<PeopleResponse>

    @GET("api/planets")
    suspend fun getPlanets(): CategoryDetailPagedResponse<PlanetResponse>

    @GET("api/films")
    suspend fun getFilms(): CategoryDetailPagedResponse<FilmsResponse>
}
