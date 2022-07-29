package com.example.lbmeals.feature_categories.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.lbmeals.feature_categories.domain.use_case.CategoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class CategoryViewModel(
    private val useCases: CategoryUseCases
) {
    private val _state = mutableStateOf(CategoryState())
    val state: State<CategoryState> = _state
}