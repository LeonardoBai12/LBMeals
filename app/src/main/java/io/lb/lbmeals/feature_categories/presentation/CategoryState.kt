package io.lb.lbmeals.feature_categories.presentation

import io.lb.lbmeals.feature_categories.domain.model.Category

data class CategoryState(
    val categories: List<Category> = emptyList(),
    val loading: Boolean = true,
)
