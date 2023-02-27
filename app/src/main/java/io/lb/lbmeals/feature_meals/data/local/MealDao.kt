package io.lb.lbmeals.feature_meals.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(
        mealEntities: List<MealEntity>
    )

    @Query("DELETE FROM meals")
    suspend fun clearMeals()

    @Query(
        """
            SELECT * 
            FROM meals
        """
    )
    suspend fun searchMeals(): List<MealEntity>
}
