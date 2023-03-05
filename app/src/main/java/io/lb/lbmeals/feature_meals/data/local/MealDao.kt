package io.lb.lbmeals.feature_meals.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSingleMeal(
        mealEntity: MealEntity
    )

    @Update
    suspend fun updateMeal(mealEntity: MealEntity)

    @Query(
        """
            SELECT * 
            FROM meals
            WHERE LOWER(category) = LOWER(:category)
        """
    )
    suspend fun searchMealsByCategory(category: String): List<MealEntity>

    @Query(
        """
            SELECT * 
            FROM meals
            WHERE LOWER(id) = LOWER(:id)
            LIMIT 1
        """
    )
    suspend fun searchMealById(id: String): MealEntity?
}
