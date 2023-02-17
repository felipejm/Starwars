package com.felipe.starwars.features.category.list.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.felipe.starwars.base.presentation.Response
import com.felipe.starwars.base.presentation.ViewState
import com.felipe.starwars.features.category.list.CategoriesFilter
import com.felipe.starwars.features.category.list.domain.Category
import com.felipe.starwars.features.category.list.domain.DeleteCategoryUseCase
import com.felipe.starwars.features.category.list.domain.GetCategoriesUseCase
import com.felipe.starwars.features.category.list.domain.SaveCategoryUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class CategoriesViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @InjectMockKs
    lateinit var viewModel: CategoriesViewModel

    @MockK
    lateinit var getCategoriesUseCase: GetCategoriesUseCase

    @MockK
    lateinit var saveCategoryUseCase: SaveCategoryUseCase

    @MockK
    lateinit var deleteCategoryUseCase: DeleteCategoryUseCase

    @RelaxedMockK
    lateinit var categoryObserver: Observer<ViewState<List<Category>>>

    @RelaxedMockK
    lateinit var commandObserver: Observer<CategoriesCommand>

    @RelaxedMockK
    lateinit var savedStateHandle: SavedStateHandle

    private val categories = listOf(
        Category(
            title = "title",
            imageUrl = "imageUrl",
            detailUrl = "detailUrl",
            isFavorite = false,
        )
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel.categoriesLiveData.observeForever(categoryObserver)
        viewModel.commandLiveData.observeForever(commandObserver)

        every { savedStateHandle.get<CategoriesFilter>(CategoriesFragment.FILTER_ARGS) } returns CategoriesFilter.LOCAL
    }

    @Test
    fun `onCreate should return call getCategories`() = runBlocking {
        // Given
        coEvery { getCategoriesUseCase(CategoriesFilter.LOCAL) } returns flowOf(Response.Success(categories))

        // When
        viewModel.onCreate(mockk())

        // Then
        verify { categoryObserver.onChanged(ViewState.Success(categories)) }
    }

    @Test
    fun `tryAgain should call getCategories`() = runBlocking {
        // Given
        coEvery { getCategoriesUseCase(CategoriesFilter.LOCAL) } returns flowOf(Response.Success(categories))

        // When
        viewModel.tryAgain()

        // Then
        verify { categoryObserver.onChanged(ViewState.Success(categories)) }
    }

    @Test
    fun `onCategoryClicked should send open category detail`() = runBlocking {
        // Then
        viewModel.onCategoryClicked(categories.first())

        // When
        verify { commandObserver.onChanged(CategoriesCommand.OpenCategory(categories.first())) }
    }

    @Test
    fun `onFavoriteClicked should save category`() = runBlocking {
        // Given
        coEvery { saveCategoryUseCase(categories.first()) }

        // When
        viewModel.onFavoriteClicked(checked = true, categories.first())

        // When
        coVerify { saveCategoryUseCase(eq(categories.first())) }
    }

    @Test
    fun `onFavoriteClicked should delete category`() = runBlocking {
        // Given
        coEvery { deleteCategoryUseCase(categories.first()) }

        // When
        viewModel.onFavoriteClicked(checked = false, categories.first())

        // When
        coVerify { deleteCategoryUseCase(eq(categories.first())) }
    }
}
