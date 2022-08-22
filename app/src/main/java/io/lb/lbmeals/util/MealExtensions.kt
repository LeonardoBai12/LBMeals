package io.lb.lbmeals.util

import io.lb.lbmeals.feature_meals.domain.model.Meal

fun Meal.listOfMeasuredIngredients(): List<String> {
    val ingredients = mutableListOf<String>()

    ingredient1?.takeIf {
        it.isNotEmpty()
    }?.let {
        ingredients.add("$measure1 $it")
    }

    ingredient2?.takeIf {
        it.isNotEmpty()
    }?.let {
        ingredients.add("$measure2 $it")
    }

    ingredient3?.takeIf {
        it.isNotEmpty()
    }?.let {
        ingredients.add("$measure3 $it")
    }

    ingredient4?.takeIf {
        it.isNotEmpty()
    }?.let {
        ingredients.add("$measure4 $it")
    }

    ingredient5?.takeIf {
        it.isNotEmpty()
    }?.let {
        ingredients.add("$measure5 $it")
    }

    ingredient6?.takeIf {
        it.isNotEmpty()
    }?.let {
        ingredients.add("$measure6 $it")
    }

    ingredient7?.takeIf {
        it.isNotEmpty()
    }?.let {
        ingredients.add("$measure7 $it")
    }

    ingredient8?.takeIf {
        it.isNotEmpty()
    }?.let {
        ingredients.add("$measure8 $it")
    }

    ingredient9?.takeIf {
        it.isNotEmpty()
    }?.let {
        ingredients.add("$measure9 $it")
    }

    ingredient10?.takeIf {
        it.isNotEmpty()
    }?.let {
        ingredients.add("$measure10 $it")
    }

    ingredient11?.takeIf {
        it.isNotEmpty()
    }?.let {
        ingredients.add("$measure11 $it")
    }

    ingredient12?.takeIf {
        it.isNotEmpty()
    }?.let {
        ingredients.add("$measure12 $it")
    }

    ingredient13?.takeIf {
        it.isNotEmpty()
    }?.let {
        ingredients.add("$measure13 $it")
    }

    ingredient14?.takeIf {
        it.isNotEmpty()
    }?.let {
        ingredients.add("$measure14 $it")
    }

    ingredient15?.takeIf {
        it.isNotEmpty()
    }?.let {
        ingredients.add("$measure15 $it")
    }

    ingredient16?.takeIf {
        it.isNotEmpty()
    }?.let {
        ingredients.add("$measure16 $it")
    }

    ingredient17?.takeIf {
        it.isNotEmpty()
    }?.let {
        ingredients.add("$measure17 $it")
    }

    ingredient18?.takeIf {
        it.isNotEmpty()
    }?.let {
        ingredients.add("$measure18 $it")
    }

    ingredient19?.takeIf {
        it.isNotEmpty()
    }?.let {
        ingredients.add("$measure19 $it")
    }

    ingredient20?.takeIf {
        it.isNotEmpty()
    }?.let {
        ingredients.add("$measure20 $it")
    }

    return ingredients
}