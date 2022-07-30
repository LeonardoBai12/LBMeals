package com.example.lbmeals.feature_categories.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lbmeals.feature_categories.domain.model.Category
import com.example.lbmeals.feature_categories.domain.use_case.CategoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val useCases: CategoryUseCases
): ViewModel() {
    private val _state = mutableStateOf(CategoryState())
    val state: State<CategoryState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    sealed class UiEvent {
        data class OnCategoryClicked(val category: Category): UiEvent()
        data class ShowToast(val message: String): UiEvent()
        object Finish: UiEvent()
    }

    init {
        viewModelScope.launch {
            getCategories()
        }
    }

    fun onEvent(event: CategoryEvent) {
        viewModelScope.launch {
            when (event) {
                is CategoryEvent.OnClickCategory -> {
                    _eventFlow.emit(UiEvent.OnCategoryClicked(event.category))
                }
                is CategoryEvent.SearchedForCategory -> {

                }
            }
        }
    }

    private suspend fun getCategories() {
        useCases.getCategoryUseCase().takeIf {
            it.isNotEmpty()
        }?.let {
            _state.value = state.value.copy(
                categories = it,
            )
        } ?: _eventFlow.emit(
            UiEvent.ShowToast("Something went wrong!")
        )
    }
}