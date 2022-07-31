package com.example.lbmeals.feature_meal_details.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lbmeals.feature_meal_details.domain.use_case.MealDetailsUseCases
import com.example.lbmeals.feature_meals.presentation.MealViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
    private val useCases: MealDetailsUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val mealId: String
    private val _state = mutableStateOf(MealDetailsState())
    val state: State<MealDetailsState> = _state

    private val _eventFlow = MutableSharedFlow<MealViewModel.UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    sealed class UiEvent {
        data class ShowToast(val message: String): UiEvent()
    }

    init {
        mealId = savedStateHandle["meal"] ?: ""
        getMealDetailsById()
    }

    private fun getMealDetailsById() {
        viewModelScope.launch {
            useCases.getMealDetailsByIdUseCase(mealId)?.let {
                _state.value = state.value.copy(
                    meal = it,
                )
            } ?: _eventFlow.emit(
                MealViewModel.UiEvent.ShowToast("Something went wrong!")
            )
        }
    }
}