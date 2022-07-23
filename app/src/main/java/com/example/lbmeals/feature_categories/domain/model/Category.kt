package com.example.lbmeals.feature_categories.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    @SerializedName("idMeal")
    val id: String,
    @SerializedName("strCategory")
    val category: String,
    @SerializedName("strCategoryThumb")
    val thumbnail: String,
    @SerializedName("strCategoryDescription")
    val description: String,
): Parcelable