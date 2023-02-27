package io.lb.lbmeals.feature_meals.data.remote

import io.lb.lbmeals.feature_meals.domain.model.MealResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealService {
    @GET("api/json/v1/1/filter.php")
    suspend fun getMealByCategory(@Query("c") filter: String): Response<MealResponse>
}
