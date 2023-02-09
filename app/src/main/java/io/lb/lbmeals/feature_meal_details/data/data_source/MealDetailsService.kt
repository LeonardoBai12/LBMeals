package io.lb.lbmeals.feature_meal_details.data.data_source

import io.lb.lbmeals.feature_meals.domain.model.MealResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealDetailsService {
    @GET("api/json/v1/1/lookup.php")
    suspend fun getMealDetailsById(@Query("i") filter: String): Response<MealResponse>
}
