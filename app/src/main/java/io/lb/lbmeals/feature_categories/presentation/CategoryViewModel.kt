package io.lb.lbmeals.feature_categories.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.lb.lbmeals.feature_categories.domain.use_case.CategoryUseCases
import io.lb.lbmeals.util.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val useCases: CategoryUseCases
) : ViewModel() {
    private val _state = mutableStateOf(CategoryState())
    val state: State<CategoryState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    sealed class UiEvent {
        data class ShowToast(val message: String) : UiEvent()
    }

    init {
        getCategories()
    }

    fun getCategories() {
        viewModelScope.launch {
            useCases.getCategoriesUseCase().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            _state.value = state.value.copy(
                                categories = it,
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            categories = emptyList(),
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
