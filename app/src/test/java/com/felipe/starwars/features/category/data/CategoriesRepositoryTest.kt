package com.felipe.starwars.features.category.data

import com.felipe.starwars.features.category.data.response.CategoryDetailPagedResponse
import com.felipe.starwars.features.category.data.response.FilmsResponse
import com.felipe.starwars.features.category.data.response.PeopleResponse
import com.felipe.starwars.features.category.data.response.PlanetResponse
import com.felipe.starwars.features.category.list.domain.Category
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CategoriesRepositoryTest {

    @InjectMockKs
    lateinit var repository: CategoriesRepositoryImpl

    @RelaxedMockK
    lateinit var api: CategoryApi

    @RelaxedMockK
    lateinit var dao: CategoryDao

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getCategories should return categories`() = runBlocking {
        // Given
        val expected = mockk<Map<String, String>>()
        coEvery { api.getCategories() } returns expected

        // When
        val result = repository.getCategories()

        // Then
        Assert.assertEquals(expected, result)
    }

    @Test
    fun `getFilms should return films`() = runBlocking {
        // Given
        val expected = mockk<CategoryDetailPagedResponse<FilmsResponse>>()
        coEvery { api.getFilms() } returns expected

        // When
        val result = repository.getFilms()

        // Then
        Assert.assertEquals(expected, result)
    }

    @Test
    fun `getPeople should return films`() = runBlocking {
        // Given
        val expected = mockk<CategoryDetailPagedResponse<PeopleResponse>>()
        coEvery { api.getPeople() } returns expected

        // When
        val result = repository.getPeople()

        // Then
        Assert.assertEquals(expected, result)
    }

    @Test
    fun `getPlanets should return films`() = runBlocking {
        // Given
        val expected = mockk<CategoryDetailPagedResponse<PlanetResponse>>()
        coEvery { api.getPlanets() } returns expected

        // When
        val result = repository.getPlanets()

        // Then
        Assert.assertEquals(expected, result)
    }

    @Test
    fun `saveLocal should call dao`() = runBlocking {
        // Given
        val category = mockk<Category>()

        // When
        repository.saveLocal(category)

        // Then
        coVerify { dao.insert(eq(category)) }
    }

    @Test
    fun `deleteLocal should call dao`() = runBlocking {
        // Given
        val category = mockk<Category>()

        // When
        repository.deleteLocal(category)

        // Then
        coVerify { dao.delete(eq(category)) }
    }

    @Test
    fun `getAllLocal should call dao`() = runBlocking {
        // Given
        val expected = mockk<Flow<List<Category>>>()
        every { dao.getAll() } returns expected

        // When
        val result = repository.getAllLocal()

        // Then
        Assert.assertEquals(expected, result)
    }
}
