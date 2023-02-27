package io.lb.lbmeals.feature_categories.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "categories"
)
data class CategoryEntity(
    @PrimaryKey
    val localId: Int? = null,
    val id: String,
    val name: String,
    val thumbnail: String,
    val description: String,
)
