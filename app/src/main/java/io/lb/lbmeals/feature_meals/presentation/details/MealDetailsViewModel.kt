package io.lb.lbmeals.feature_meals.presentation.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.lb.lbmeals.feature_meals.domain.repository.MealRepository
import io.lb.lbmeals.util.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
    private val useCases: MealRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val mealId: String
    private val _state = mutableStateOf(MealDetailsState())
    val state: State<MealDetailsState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    sealed class UiEvent {
        data class ShowToast(val message: String) : UiEvent()
    }

    init {
        mealId = savedStateHandle["meal"] ?: ""
        getMealDetailsById()
    }

    fun getMealDetailsById() {
        viewModelScope.launch {
            useCases.getMealDetailsById(mealId).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            _state.value = state.value.copy(
                                meal = it,
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            meal = null
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
