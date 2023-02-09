package io.lb.lbmeals.feature_meals.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.lb.lbmeals.feature_meals.domain.model.Meal
import io.lb.lbmeals.feature_meals.domain.use_case.MealUseCases
import io.lb.lbmeals.util.Resource
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
        getMealsByCategory()
    }

    fun onEvent(event: MealEvent) {
        when (event) {
            is MealEvent.SearchedForMeal -> {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    event.filter.takeIf {
                        it.isNotEmpty()
                    }?.let {
                        delay(300L)
                    }
                    _state.value = state.value.copy(
                        meals = meals.filterByName(event.filter),
                    )
                }
            }
        }
    }

    fun getMealsByCategory() {
        viewModelScope.launch {
            useCases.getMealsUseCase(category).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            meals.addAll(it)

                            _state.value = state.value.copy(
                                meals = it,
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            meals = emptyList(),
                        )

                        _eventFlow.emit(
                            UiEvent.ShowToast(
                                result.message ?: "Something went wrong!"
                            )
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            loading = result.isLoading,
                        )
                    }
                }
            }
        }
    }
}
