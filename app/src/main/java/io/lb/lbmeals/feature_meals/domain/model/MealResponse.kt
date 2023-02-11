package io.lb.lbmeals.feature_meals.domain.model

/**
 * Data obtained from a meals request.
 *
 * @property meals List of meals.
 */
data class MealResponse(
    val meals: List<Meal>,
)
