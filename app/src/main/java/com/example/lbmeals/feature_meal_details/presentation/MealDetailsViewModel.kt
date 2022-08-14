package com.example.lbmeals.feature_meal_details.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lbmeals.feature_meal_details.domain.use_case.MealDetailsUseCases
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

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    sealed class UiEvent {
        data class ShowToast(val message: String): UiEvent()
    }

    init {
        mealId = savedStateHandle["meal"] ?: ""
        viewModelScope.launch {
            getMealDetailsById()
        }
    }

    private suspend fun getMealDetailsById() {
        loadingState()
        useCases.getMealDetailsByIdUseCase(mealId)?.let {
            _state.value = state.value.copy(
                meal = it,
                loading = false
            )
        } ?: errorState()
    }

    private fun loadingState() {
        _state.value = state.value.copy(
            meal = null,
            loading = true,
        )
    }

    private suspend fun errorState() {
        _state.value = state.value.copy(
            meal = null,
            loading = false,
        )
        _eventFlow.emit(
            UiEvent.ShowToast("Something went wrong!")
        )
    }
}