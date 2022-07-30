package com.example.lbmeals.feature_categories.presentation

import com.example.lbmeals.feature_categories.domain.model.Category

data class CategoryState(
    val categories: List<Category> = emptyList(),
)
