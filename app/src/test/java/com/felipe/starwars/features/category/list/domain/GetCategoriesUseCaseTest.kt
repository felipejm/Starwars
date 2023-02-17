package com.felipe.starwars.features.category.list.domain

import com.felipe.starwars.base.presentation.Response
import com.felipe.starwars.features.category.data.CategoriesRepository
import com.felipe.starwars.features.category.list.CategoriesFilter
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetCategoriesUseCaseTest {

    @InjectMockKs
    lateinit var useCase: GetCategoriesUseCaseImpl

    @MockK
    lateinit var repository: CategoriesRepository

    @MockK
    lateinit var mapper: CategoryMapper

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    private val categories = listOf(
        Category(
            title = "title",
            imageUrl = "imageUrl",
            detailUrl = "detailUrl",
            isFavorite = false,
        )
    )

    @Test
    fun `when is filter remote should call repository`() = runBlocking {
        // Given
        val remoteRepositoryResult = mockk<Map<String, String>>()
        coEvery { repository.getCategories() } returns remoteRepositoryResult

        val localRepositoryResult = mockk<List<Category>>()
        coEvery { repository.getAllLocal() } returns flowOf(localRepositoryResult)

        val mapperResult = mockk<List<Category>>()
        every {
            mapper.parseCategories(
                localRepositoryResult,
                remoteRepositoryResult
            )
        } returns mapperResult

        // When
        val result = useCase(CategoriesFilter.REMOTE).single()

        // Then
        Assert.assertEquals(Response.Success(mapperResult), result)
    }

    @Test
    fun `when is filter remote should return error`() = runBlocking {
        // Given
        val exception = RuntimeException()
        coEvery { repository.getCategories() }.throws(exception)
        coEvery { repository.getAllLocal() } throws exception

        val mapperResult = mockk<List<Category>>()
        every {
            mapper.parseCategories(any(), any())
        } returns mapperResult

        // When
        val result = useCase(CategoriesFilter.REMOTE).single()

        // Then
        Assert.assertEquals(Response.Error<List<Category>>(exception), result)
    }

    @Test
    fun `when is filter local should call repository`() = runBlocking {
        // Given
        coEvery { repository.getAllLocal() } returns flowOf(categories)

        // When
        val result = useCase(CategoriesFilter.LOCAL).single()

        // Then
        Assert.assertEquals(Response.Success<List<Category>>(categories), result)
    }

    @Test
    fun `when is filter local should return error`() = runBlocking {
        // Given
        val error = RuntimeException()
        coEvery { repository.getAllLocal() } throws error

        // When
        val result = useCase(CategoriesFilter.LOCAL).single()

        // Then
        Assert.assertEquals(Response.Error<List<Category>>(error), result)
    }
}
