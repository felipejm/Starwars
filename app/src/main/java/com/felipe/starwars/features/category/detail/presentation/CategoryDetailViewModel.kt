package com.felipe.starwars.features.category.detail.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.felipe.starwars.base.presentation.BaseViewModel
import com.felipe.starwars.features.category.detail.domain.CategoryDetail
import com.felipe.starwars.features.category.detail.domain.GetCategoryDetailUseCase
import com.felipe.starwars.features.category.list.domain.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailViewModel @Inject constructor(
    private val useCase: GetCategoryDetailUseCase,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _categoryDetailsLiveData = MutableLiveData<List<CategoryDetail>>()
    val categoryDetailsLiveData: LiveData<List<CategoryDetail>> = _categoryDetailsLiveData

    private val _commandLiveData = MutableLiveData<CategoryDetailCommand>()
    val commandLiveData: LiveData<CategoryDetailCommand> = _commandLiveData

    private val categoy: Category? by lazy {
        CategoryDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).categoy
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        viewModelScope.launch() {
            categoy?.let {
                _categoryDetailsLiveData.value = useCase(it)
            }
        }
    }
}
