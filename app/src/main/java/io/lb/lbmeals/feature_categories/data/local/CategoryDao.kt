package io.lb.lbmeals.feature_categories.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(
        categoryEntities: List<CategoryEntity>
    )

    @Query("DELETE FROM categories")
    suspend fun clearCategories()

    @Query(
        """
            SELECT * 
            FROM categories
        """
    )
    suspend fun searchCategories(): List<CategoryEntity>
}
