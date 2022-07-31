package com.example.lbmeals.feature_categories.presentation

sealed class CategoryEvent {
    data class SearchedForCategory(val value: String): CategoryEvent()
}
