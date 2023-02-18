package com.felipe.starwars.features.category.list.presentation

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.felipe.starwars.base.presentation.BaseViewModel
import com.felipe.starwars.base.presentation.Response
import com.felipe.starwars.base.presentation.SingleLiveEvent
import com.felipe.starwars.base.presentation.ViewState
import com.felipe.starwars.features.category.list.CategoriesFilter
import com.felipe.starwars.features.category.list.domain.Category
import com.felipe.starwars.features.category.list.domain.DeleteCategoryUseCase
import com.felipe.starwars.features.category.list.domain.GetCategoriesUseCase
import com.felipe.starwars.features.category.list.domain.SaveCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val saveCategoryUseCase: SaveCategoryUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _categoriesLiveData = MutableLiveData<ViewState<List<Category>>>()
    val categoriesLiveData: LiveData<ViewState<List<Category>>> = _categoriesLiveData

    private val _commandLiveData = SingleLiveEvent<CategoriesCommand>()
    val commandLiveData: LiveData<CategoriesCommand> = _commandLiveData

    private val filter: CategoriesFilter by lazy {
        savedStateHandle.get<CategoriesFilter>(CategoriesFragment.FILTER_ARGS)
            ?: CategoriesFilter.REMOTE
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        loadCategories()
    }

    fun onCategoryClicked(category: Category) {
        _commandLiveData.value = CategoriesCommand.OpenCategory(category.title)
    }

    fun onFavoriteClicked(checked: Boolean, category: Category) {
        if (checked) {
            saveCategory(category)
        } else {
            deleteCategory(category)
        }
    }

    fun tryAgain() {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            _categoriesLiveData.value = ViewState.Loading()
            val responseFlow = getCategoriesUseCase(filter)
            responseFlow.collect { response ->
                when (response) {
                    is Response.Success ->
                        _categoriesLiveData.value = if (response.value.isEmpty()) {
                            ViewState.Empty()
                        } else {
                            ViewState.Success(response.value)
                        }
                    is Response.Error -> {
                        _categoriesLiveData.value = ViewState.Error(response.error)
                        Log.e("CategoriesViewModel", "loadCategories", response.error)
                    }
                }
            }
        }
    }

    private fun saveCategory(category: Category) {
        viewModelScope.launch {
            when (val response = saveCategoryUseCase(category)) {
                is Response.Success -> {}
                is Response.Error ->
                    _categoriesLiveData.value = ViewState.Error(response.error)
            }
        }
    }

    private fun deleteCategory(category: Category) {
        viewModelScope.launch {
            when (val response = deleteCategoryUseCase(category)) {
                is Response.Success -> {}
                is Response.Error ->
                    _categoriesLiveData.value = ViewState.Error(response.error)
            }
        }
    }
}
