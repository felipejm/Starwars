package com.felipe.starwars.features.category.detail.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.felipe.starwars.base.presentation.Response
import com.felipe.starwars.base.presentation.ViewState
import com.felipe.starwars.features.category.detail.domain.CategoryDetail
import com.felipe.starwars.features.category.detail.domain.GetCategoryDetailUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class CategoryDetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @InjectMockKs
    lateinit var viewModel: CategoryDetailViewModel

    @MockK
    lateinit var getCategoryDetailUseCase: GetCategoryDetailUseCase

    @RelaxedMockK
    lateinit var categoryObserver: Observer<ViewState<List<CategoryDetail>>>

    @RelaxedMockK
    lateinit var savedStateHandle: SavedStateHandle

    private val category = "people"

    private val categoryDetails = listOf(mockk<CategoryDetail>())

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel.categoryDetailsLiveData.observeForever(categoryObserver)

        every { savedStateHandle.get<String>(CategoriesDetailActivity.CATEGORY_ARGS) } returns category
    }

    @Test
    fun `onCreate should return call getCategories`() = runBlocking {
        // Given
        coEvery { getCategoryDetailUseCase(category) } returns Response.Success(categoryDetails)

        // When
        viewModel.onCreate(mockk())

        // Then
        verify { categoryObserver.onChanged(ViewState.Success(categoryDetails)) }
    }

    @Test
    fun `tryAgain should call getCategories`() = runBlocking {
        // Given
        coEvery { getCategoryDetailUseCase(category) } returns Response.Success(categoryDetails)

        // When
        viewModel.tryAgain()

        // Then
        verify { categoryObserver.onChanged(ViewState.Success(categoryDetails)) }
    }
}
