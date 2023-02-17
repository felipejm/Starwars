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

class DeleteCategoryUseCaseTest {

    @InjectMockKs
    lateinit var useCase: DeleteCategoryUseCaseImpl

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
        val expected = 1
        coEvery { repository.deleteLocal(category) } returns expected

        // When
        val result = useCase(category)

        // Then
        coVerify { repository.deleteLocal(eq(category)) }
        Assert.assertEquals(Response.Success(expected), result)
    }

    @Test
    fun `invoke should return error`() = runBlocking {
        // Given
        val expected = RuntimeException()
        coEvery { repository.deleteLocal(category) } throws expected

        // When
        val result = useCase(category)

        // Then
        coVerify { repository.deleteLocal(eq(category)) }
        Assert.assertEquals(Response.Error<Long>(expected), result)
    }
}
