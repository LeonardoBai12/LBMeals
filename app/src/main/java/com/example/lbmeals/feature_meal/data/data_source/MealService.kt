package com.example.lbmeals.feature_meal.data.data_source

import com.example.lbmeals.feature_meal.domain.model.MealRequest
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealService {
    @GET("api/json/v1/1/filter.php")
    suspend fun getMealByCategory(@Query("c") filter: String): Response<MealRequest>
}