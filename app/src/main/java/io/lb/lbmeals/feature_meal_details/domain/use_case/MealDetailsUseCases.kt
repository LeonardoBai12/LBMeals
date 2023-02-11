package io.lb.lbmeals.feature_meal_details.domain.use_case

/**
 * Use cases related to meal details.
 *
 * @property getMealDetailsByIdUseCase Use case to request a single meal.
 */
data class MealDetailsUseCases(
    val getMealDetailsByIdUseCase: GetMealDetailsByIdUseCase,
)
