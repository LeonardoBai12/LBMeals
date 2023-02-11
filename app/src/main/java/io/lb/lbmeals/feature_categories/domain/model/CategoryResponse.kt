package io.lb.lbmeals.feature_categories.domain.model

/**
 * Data obtained from a meal categories request.
 *
 * @property categories List of meal categories.
 */
data class CategoryResponse(
    val categories: List<Category>,
)
