package com.felipe.starwars.features.category.detail.domain

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

class GetCategoryDetailUseCaseTest {

    @InjectMockKs
    lateinit var useCase: GetCategoryDetailUseCaseImpl

    @MockK
    lateinit var repository: CategoriesRepository

    @MockK
    lateinit var mapper: CategoryDetailMapper

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when category is people should return category detail`() = runBlocking {
        // Given
        val peopleResponse = mockk<List<PeopleResponse>>()
        val response = CategoryDetailPagedResponse(results = peopleResponse)
        coEvery { repository.getPeople() } returns response

        val mapperResult = mockk<List<CategoryDetail>>()
        coEvery { mapper.parsePeople(peopleResponse) } returns mapperResult

        // When
        val result = useCase("people")

        // Then
        Assert.assertEquals(Response.Success(mapperResult), result)
    }

    @Test
    fun `when category is planets should return category detail`() = runBlocking {
        // Given
        val planetResponse = mockk<List<PlanetResponse>>()
        val response = CategoryDetailPagedResponse(results = planetResponse)
        coEvery { repository.getPlanets() } returns response

        val mapperResult = mockk<List<CategoryDetail>>()
        coEvery { mapper.parsePlanets(planetResponse) } returns mapperResult

        // When
        val result = useCase("planets")

        // Then
        Assert.assertEquals(Response.Success(mapperResult), result)
    }

    @Test
    fun `when category is films should return category detail`() = runBlocking {
        // Given
        val filmsResponse = mockk<List<FilmsResponse>>()
        val response = CategoryDetailPagedResponse(results = filmsResponse)
        coEvery { repository.getFilms() } returns response

        val mapperResult = mockk<List<CategoryDetail>>()
        coEvery { mapper.parseFilms(filmsResponse) } returns mapperResult

        // When
        val result = useCase("films")

        // Then
        Assert.assertEquals(Response.Success(mapperResult), result)
    }

    @Test
    fun `when category is not found should return error`() = runBlocking {
        // Given
        val peopleResponse = mockk<List<PeopleResponse>>()
        val response = CategoryDetailPagedResponse(results = peopleResponse)
        coEvery { repository.getPeople() } returns response

        val mapperResult = mockk<List<CategoryDetail>>()
        coEvery { mapper.parsePeople(peopleResponse) } returns mapperResult

        // When
        val result = useCase("not found")

        // Then
        Assert.assertTrue(result is Response.Error)
    }
}
