package io.lb.lbmeals.feature_categories.data.mapper

import io.lb.lbmeals.feature_categories.data.local.CategoryEntity
import io.lb.lbmeals.feature_categories.domain.model.Category

fun Category.toCategoryEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        name = name,
        thumbnail = thumbnail,
        description = description,
    )
}

fun CategoryEntity.toCategory(): Category {
    return Category(
        id = id,
        name = name,
        thumbnail = thumbnail,
        description = description,
    )
}
