package com.felipe.starwars.features.category.list.domain

import com.felipe.starwars.base.presentation.Response
import com.felipe.starwars.features.category.data.CategoriesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SaveCategoryUseCaseTest {

    @InjectMockKs
    lateinit var useCase: SaveCategoryUseCaseImpl

    @MockK
    lateinit var repository: CategoriesRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    private val category = Category(
        title = "title",
        imageUrl = "imageUrl",
        detailUrl = "detailUrl",
        isFavorite = false,
    )

    @Test
    fun `invoke should call repository`() = runBlocking {
        // Given
        val expected = 1L
        coEvery { repository.saveLocal(category.copy(isFavorite = true)) } returns expected

        // When
        val result = useCase(category)

        // Then
        coVerify { repository.saveLocal(eq(category.copy(isFavorite = true))) }
        Assert.assertEquals(Response.Success(expected), result)
    }

    @Test
    fun `invoke should return error`() = runBlocking {
        // Given
        val expected = RuntimeException()
        coEvery { repository.saveLocal(category.copy(isFavorite = true)) } throws expected

        // When
        val result = useCase(category)

        // Then
        coVerify { repository.saveLocal(eq(category.copy(isFavorite = true))) }
        Assert.assertEquals(Response.Error<Long>(expected), result)
    }
}
