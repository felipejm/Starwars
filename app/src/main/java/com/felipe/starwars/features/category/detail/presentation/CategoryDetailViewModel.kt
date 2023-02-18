package com.felipe.starwars.features.category.detail.presentation

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.felipe.starwars.base.presentation.BaseViewModel
import com.felipe.starwars.base.presentation.Response
import com.felipe.starwars.base.presentation.ViewState
import com.felipe.starwars.features.category.detail.domain.CategoryDetail
import com.felipe.starwars.features.category.detail.domain.GetCategoryDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailViewModel @Inject constructor(
    private val useCase: GetCategoryDetailUseCase,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _categoryDetailsLiveData = MutableLiveData<ViewState<List<CategoryDetail>>>()
    val categoryDetailsLiveData: LiveData<ViewState<List<CategoryDetail>>> =
        _categoryDetailsLiveData

    private val category: String? by lazy {
        savedStateHandle.get(CategoriesDetailActivity.CATEGORY_ARGS) as? String
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        loadCategoryDetail()
    }

    fun tryAgain() {
        loadCategoryDetail()
    }

    private fun loadCategoryDetail() {
        viewModelScope.launch {
            _categoryDetailsLiveData.value = ViewState.Loading()
            when (val response = useCase(category.orEmpty())) {
                is Response.Success ->
                    _categoryDetailsLiveData.value = if (response.value.isEmpty()) {
                        ViewState.Empty()
                    } else {
                        ViewState.Success(response.value)
                    }
                is Response.Error -> {
                    _categoryDetailsLiveData.value = ViewState.Error(response.error)
                    Log.e("CategoryDetailViewModel", "loadCategories", response.error)
                }
            }
        }
    }
}
