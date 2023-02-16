package com.felipe.starwars.features.category.data

import retrofit2.http.GET

interface CategoryApi {

    @GET("api/")
    suspend fun getCategories(): Map<String, String>
}
