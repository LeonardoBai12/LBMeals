package io.lb.lbmeals.feature_meals.domain.use_case

/**
 * Use cases related to meal details.
 *
 * @property getMealsUseCase Use case to request a list of meals.
 */
data class MealUseCases(
    val getMealsUseCase: GetMealsUseCase,
)
