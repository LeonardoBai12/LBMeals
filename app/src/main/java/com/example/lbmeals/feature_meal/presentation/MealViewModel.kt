package com.example.lbmeals.feature_meal.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lbmeals.feature_meal.domain.use_case.MealUseCases
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
    private var category: String
    private val _state = mutableStateOf(MealState())
    val state: State<MealState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    sealed class UiEvent {
        data class ShowToast(val message: String): UiEvent()
    }

    init {
        category = savedStateHandle["category"] ?: ""
        getMealsByCategory()
    }

    fun onEvent(event: MealEvent) {
        when(event) {
            is MealEvent.SearchedForMeal -> {

            }
        }
    }

    private fun getMealsByCategory() {
        viewModelScope.launch {
            useCases.getMealsUseCase(category).takeIf {
                it.isNotEmpty()
            }?.let {
                _state.value = state.value.copy(
                    meals = it,
                )
            } ?: _eventFlow.emit(
                UiEvent.ShowToast("Something went wrong!")
            )
        }
    }
}