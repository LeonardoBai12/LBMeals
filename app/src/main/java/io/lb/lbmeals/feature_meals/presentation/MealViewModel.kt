package io.lb.lbmeals.feature_meals.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.lb.lbmeals.feature_meals.domain.use_case.MealUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import io.lb.lbmeals.feature_meals.domain.model.Meal
import io.lb.lbmeals.util.filterByName
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val useCases: MealUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(MealState())
    val category: String
    val state: State<MealState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val meals = mutableListOf<Meal>()
    private var searchJob: Job? = null

    sealed class UiEvent {
        data class ShowToast(val message: String) : UiEvent()
    }

    init {
        category = savedStateHandle["category"] ?: ""
        viewModelScope.launch {
            getMealsByCategory()
        }
    }

    fun onEvent(event: MealEvent) {
        when (event) {
            is MealEvent.SearchedForMeal -> {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(300L)
                    _state.value = state.value.copy(
                        meals = meals.filterByName(event.filter),
                        loading = false,
                    )
                }
            }
        }
    }

    private suspend fun getMealsByCategory() {
        loadingState()

        useCases.getMealsUseCase(category).takeIf {
            it.isNotEmpty()
        }?.let {
            meals.addAll(it)

            _state.value = state.value.copy(
                meals = it,
                loading = false
            )
        } ?: errorState()
    }

    private fun loadingState() {
        meals.clear()

        _state.value = state.value.copy(
            meals = emptyList(),
            loading = true,
        )
    }

    private suspend fun errorState() {
        meals.clear()

        _state.value = state.value.copy(
            meals = emptyList(),
            loading = false,
        )
        _eventFlow.emit(
            UiEvent.ShowToast("Something went wrong!")
        )
    }
}