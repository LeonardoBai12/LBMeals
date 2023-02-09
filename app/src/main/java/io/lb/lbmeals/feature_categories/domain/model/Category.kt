package io.lb.lbmeals.feature_categories.domain.model

import com.google.gson.annotations.SerializedName

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
