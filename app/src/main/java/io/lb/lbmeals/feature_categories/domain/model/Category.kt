package io.lb.lbmeals.feature_categories.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Meal category data.
 *
 * @property id Category API ID.
 * @property name Category name.
 * @property thumbnail Category thumbnail URL.
 * @property description Category description.
 */
data class Category(
    @SerializedName("idMeal")
    val id: String,
    @SerializedName("strCategory")
    val name: String,
    @SerializedName("strCategoryThumb")
    val thumbnail: String,
    @SerializedName("strCategoryDescription")
    val description: String,
)
