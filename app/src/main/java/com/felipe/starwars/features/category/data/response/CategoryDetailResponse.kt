package com.felipe.starwars.features.category.data.response

import com.squareup.moshi.Json

data class CategoryDetailPagedResponse<T>(
    @Json(name = "count") val count: Int? = null,
    @Json(name = "previous") val previous: String? = null,
    @Json(name = "next") val next: String? = null,
    @Json(name = "results") val results: List<T>?,
)

data class PeopleResponse(
    @Json(name = "name") val name: String?,
    @Json(name = "height") val height: String?,
    @Json(name = "mass") val mass: String?,
    @Json(name = "gender") val gender: String?,
    @Json(name = "birth_year") val birthYear: String?,
    @Json(name = "url") val url: String?,
)

data class PlanetResponse(
    @Json(name = "name") val name: String?,
    @Json(name = "climate") val climate: String?,
    @Json(name = "gravity") val gravity: String?,
    @Json(name = "terrain") val terrain: String?,
    @Json(name = "population") val population: String?,
    @Json(name = "url") val url: String?,
)

data class FilmsResponse(
    @Json(name = "title") val title: String?,
    @Json(name = "director") val director: String?,
    @Json(name = "producer") val producer: String?,
    @Json(name = "release_date") val releaseDate: String?,
    @Json(name = "episode_id") val episodeNumber: String?,
    @Json(name = "url") val url: String?,
)
