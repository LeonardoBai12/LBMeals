package com.example.lbmeals.feature_meals.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lbmeals.feature_categories.presentation.CategoryViewModel
import com.example.lbmeals.feature_meals.domain.use_case.MealUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val useCases: MealUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val category: String
    private val _state = mutableStateOf(MealState())
    val state: State<MealState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    sealed class UiEvent {
        data class ShowToast(val message: String): UiEvent()
    }

    init {
        category = savedStateHandle["category"] ?: ""
        viewModelScope.launch {
            getMealsByCategory()
        }
    }

    fun onEvent(event: MealEvent) {
        when(event) {
            is MealEvent.SearchedForMeal -> {

            }
        }
    }

    private suspend fun getMealsByCategory() {
        loadingState()

        useCases.getMealsUseCase(category).takeIf {
            it.isNotEmpty()
        }?.let {
            _state.value = state.value.copy(
                meals = it,
                loading = false
            )
        } ?: errorState()
    }

    private fun loadingState() {
        _state.value = state.value.copy(
            meals = emptyList(),
            loading = true,
        )
    }

    private suspend fun errorState() {
        _state.value = state.value.copy(
            meals = emptyList(),
            loading = false,
        )
        _eventFlow.emit(
            UiEvent.ShowToast("Something went wrong!")
        )
    }
}