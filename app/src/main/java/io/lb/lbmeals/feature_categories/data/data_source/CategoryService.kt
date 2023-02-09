package io.lb.lbmeals.feature_categories.data.data_source

import io.lb.lbmeals.feature_categories.domain.model.CategoryResponse
import retrofit2.Response
import retrofit2.http.GET

interface CategoryService {
    @GET("api/json/v1/1/categories.php")
    suspend fun getCategories(): Response<CategoryResponse>
}
