package com.felipe.starwars.features.category.detail.domain

import com.felipe.starwars.R
import com.felipe.starwars.base.presentation.Response
import com.felipe.starwars.features.category.data.CategoriesRepository
import com.felipe.starwars.features.category.data.response.CategoryDetailPagedResponse
import com.felipe.starwars.features.category.data.response.FilmsResponse
import com.felipe.starwars.features.category.data.response.PeopleResponse
import com.felipe.starwars.features.category.data.response.PlanetResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CategoryDetailMapperTest {

    @MockK
    lateinit var mapper: CategoryDetailMapper

    @Before
    fun setup() {
        mapper = CategoryDetailMapperImpl()
    }

    @Test
    fun `should map people`() = runBlocking {
        //Given
        val response = PeopleResponse(
            name = "name",
            height = "height",
            mass = "mass",
            gender = "gender",
            birthYear = "birthYear",
            url = "https://swapi.dev/api/people/1/",
        )

        val expected = CategoryDetail(
            imageUrl = "https://starwars-visualguide.com/assets/img/characters/1.jpg",
            title = "name",
            firstAttribute = "height",
            secondAttribute = "mass",
            thirdAttribute = "gender",
            fourthAttribute = "birthYear",
            firstAttributeLabel = R.string.category_people_height,
            secondAttributeLabel = R.string.category_people_mass,
            thirdAttributeLabel = R.string.category_people_gender,
            fourthAttributeLabel = R.string.category_people_birthYear,
        )

        // When
        val result = mapper.parsePeople(listOf(response))

        // Then
        Assert.assertEquals(listOf(expected), result)
    }

    @Test
    fun `should map planets`() = runBlocking {
        //Given
        val response = PlanetResponse(
            name = "name",
            climate = "climate",
            gravity = "gravity",
            terrain = "terrain",
            population = "population",
            url = "https://swapi.dev/api/people/1/",
        )

        val expected = CategoryDetail(
            imageUrl = "https://starwars-visualguide.com/assets/img/planets/1.jpg",
            title = "name",
            firstAttribute = "climate",
            secondAttribute = "gravity",
            thirdAttribute = "terrain",
            fourthAttribute = "population",
            firstAttributeLabel = R.string.category_planets_climate,
            secondAttributeLabel = R.string.category_planets_gravity,
            thirdAttributeLabel = R.string.category_planets_terrain,
            fourthAttributeLabel = R.string.category_planets_population,
        )

        // When
        val result = mapper.parsePlanets(listOf(response))

        // Then
        Assert.assertEquals(listOf(expected), result)
    }


    @Test
    fun `should map films`() = runBlocking {
        //Given
        val response = FilmsResponse(
            title = "name",
            director = "director",
            releaseDate = "releaseDate",
            producer = "producer",
            episodeNumber = "episodeNumber",
            url = "https://swapi.dev/api/people/1/",
        )

        val expected = CategoryDetail(
            imageUrl = "https://starwars-visualguide.com/assets/img/films/1.jpg",
            title = "name",
            firstAttribute = "director",
            secondAttribute = "releaseDate",
            thirdAttribute = "producer",
            fourthAttribute = "episodeNumber",
            firstAttributeLabel = R.string.category_films_director,
            secondAttributeLabel = R.string.category_films_release_data,
            thirdAttributeLabel = R.string.category_films_producer,
            fourthAttributeLabel = R.string.category_films_episodeNumber,
        )

        // When
        val result = mapper.parseFilms(listOf(response))

        // Then
        Assert.assertEquals(listOf(expected), result)
    }
}