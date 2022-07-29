package com.example.lbmeals.feature_categories.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.lbmeals.feature_categories.domain.model.Category
import com.example.lbmeals.feature_categories.domain.use_case.CategoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

@HiltViewModel
class CategoryViewModel(
    private val useCases: CategoryUseCases
) {
    private val _state = mutableStateOf(CategoryState())
    val state: State<CategoryState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    sealed class UiEvent {
        data class OnCategoryClicked(val category: Category): UiEvent()
        data class ShowToast(val message: String): UiEvent()
        object Finish: UiEvent()
    }
}