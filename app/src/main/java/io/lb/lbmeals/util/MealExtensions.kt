package io.lb.lbmeals.util

import io.lb.lbmeals.feature_meals.domain.model.Meal

fun List<Meal>.filterByName(name: String) =
    filter {
        it.name.lowercase().contains(
            name.lowercase()
        )
    }

fun Meal.measuredIngredients(): List<String> {
    val ingredients = mutableListOf<String>()

    ingredients.addMealMeasuredIngredient(ingredient1, measure1)
    ingredients.addMealMeasuredIngredient(ingredient2, measure2)
    ingredients.addMealMeasuredIngredient(ingredient3, measure3)
    ingredients.addMealMeasuredIngredient(ingredient4, measure4)
    ingredients.addMealMeasuredIngredient(ingredient5, measure5)
    ingredients.addMealMeasuredIngredient(ingredient6, measure6)
    ingredients.addMealMeasuredIngredient(ingredient7, measure7)
    ingredients.addMealMeasuredIngredient(ingredient8, measure8)
    ingredients.addMealMeasuredIngredient(ingredient9, measure9)
    ingredients.addMealMeasuredIngredient(ingredient10, measure10)
    ingredients.addMealMeasuredIngredient(ingredient11, measure11)
    ingredients.addMealMeasuredIngredient(ingredient12, measure12)
    ingredients.addMealMeasuredIngredient(ingredient13, measure13)
    ingredients.addMealMeasuredIngredient(ingredient14, measure14)
    ingredients.addMealMeasuredIngredient(ingredient15, measure15)
    ingredients.addMealMeasuredIngredient(ingredient16, measure16)
    ingredients.addMealMeasuredIngredient(ingredient17, measure17)
    ingredients.addMealMeasuredIngredient(ingredient18, measure18)
    ingredients.addMealMeasuredIngredient(ingredient19, measure19)
    ingredients.addMealMeasuredIngredient(ingredient20, measure20)

    return ingredients
}

fun MutableList<String>.addMealMeasuredIngredient(ingredient: String?, measure: String?) {
    ingredient?.takeIf {
        it.isNotBlank()
    }?.let {
        measure?.takeIf { measure ->
            measure.isNotBlank()
        }?.let { measure ->
            add("$measure $it")
        } ?: add(it)
    }
}
