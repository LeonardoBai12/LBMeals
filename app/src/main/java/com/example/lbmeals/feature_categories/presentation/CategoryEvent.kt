package com.example.lbmeals.feature_categories.presentation

import com.example.lbmeals.feature_categories.domain.model.Category

sealed class CategoryEvent {
    data class OnClickCategory(private val category: Category): CategoryEvent()
}
