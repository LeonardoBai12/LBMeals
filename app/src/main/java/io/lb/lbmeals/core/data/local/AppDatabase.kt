package io.lb.lbmeals.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.lb.lbmeals.feature_categories.data.local.CategoryDao
import io.lb.lbmeals.feature_categories.data.local.CategoryEntity
import io.lb.lbmeals.feature_meals.data.local.MealDao
import io.lb.lbmeals.feature_meals.data.local.MealEntity

@Database(
    version = 1,
    entities = [
        CategoryEntity::class,
        MealEntity::class,
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract val categoryDao: CategoryDao
    abstract val mealDao: MealDao
}
