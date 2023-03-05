package io.lb.lbmeals.feature_meals.domain.use_case

/**
 * Use cases related to meal details.
 *
 * @property getMealsUseCase Use case to request a list of meals.
 * @property getMealDetailsByIdUseCase Use case to request a single meal and all its details.
 */
data class MealUseCases(
    val getMealsUseCase: GetMealsUseCase,
    val getMealDetailsByIdUseCase: GetMealDetailsByIdUseCase,
)
