package com.example.lbmeals.feature_categories.presentation

import com.example.lbmeals.feature_categories.domain.model.Category

data class CategoryState(
    private val categories: List<Category> = emptyList(),
)