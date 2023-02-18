package com.felipe.starwars.features.category.detail.domain

import com.felipe.starwars.BuildConfig
import com.felipe.starwars.R
import com.felipe.starwars.features.category.data.response.FilmsResponse
import com.felipe.starwars.features.category.data.response.PeopleResponse
import com.felipe.starwars.features.category.data.response.PlanetResponse

interface CategoryDetailMapper {
    suspend fun parseFilms(category: List<FilmsResponse>): List<CategoryDetail>
    suspend fun parsePeople(response: List<PeopleResponse>): List<CategoryDetail>
    suspend fun parsePlanets(response: List<PlanetResponse>): List<CategoryDetail>
}

class CategoryDetailMapperImpl : CategoryDetailMapper {

    override suspend fun parseFilms(response: List<FilmsResponse>): List<CategoryDetail> {
        return response.map {
            val imageUrl = parseImageUrl(it.url.orEmpty(), "films")

            CategoryDetail(
                imageUrl = imageUrl,
                title = it.title.orEmpty(),
                firstAttribute = it.director.orEmpty(),
                secondAttribute = it.releaseDate.toString(),
                thirdAttribute = it.producer.orEmpty(),
                fourthAttribute = it.episodeNumber.orEmpty(),
                firstAttributeLabel = R.string.category_films_director,
                secondAttributeLabel = R.string.category_films_release_data,
                thirdAttributeLabel = R.string.category_films_producer,
                fourthAttributeLabel = R.string.category_films_episodeNumber,
            )
        }
    }

    override suspend fun parsePeople(response: List<PeopleResponse>): List<CategoryDetail> {
        return response.map {
            val imageUrl = parseImageUrl(it.url.orEmpty(), "characters")

            CategoryDetail(
                imageUrl = imageUrl,
                title = it.name.orEmpty(),
                firstAttribute = it.height.orEmpty(),
                secondAttribute = it.mass.toString(),
                thirdAttribute = it.gender.toString(),
                fourthAttribute = it.birthYear.orEmpty(),
                firstAttributeLabel = R.string.category_people_height,
                secondAttributeLabel = R.string.category_people_mass,
                thirdAttributeLabel = R.string.category_people_gender,
                fourthAttributeLabel = R.string.category_people_birthYear,
            )
        }
    }

    override suspend fun parsePlanets(response: List<PlanetResponse>): List<CategoryDetail> {
        return response.map {
            val imageUrl = parseImageUrl(it.url.orEmpty(), "planets")

            CategoryDetail(
                imageUrl = imageUrl,
                title = it.name.orEmpty(),
                firstAttribute = it.climate.toString(),
                secondAttribute = it.gravity.toString(),
                thirdAttribute = it.terrain.toString(),
                fourthAttribute = it.population.orEmpty(),
                firstAttributeLabel = R.string.category_planets_climate,
                secondAttributeLabel = R.string.category_planets_gravity,
                thirdAttributeLabel = R.string.category_planets_terrain,
                fourthAttributeLabel = R.string.category_planets_population,
            )
        }
    }

    private fun parseImageUrl(url: String, category: String): String {
        val url = url.dropLast(1)
        val id = url.substring(url.lastIndexOf("/") + 1)
        return "${BuildConfig.IMAGE_BASE_URL}/$category/$id.jpg"
    }
}
