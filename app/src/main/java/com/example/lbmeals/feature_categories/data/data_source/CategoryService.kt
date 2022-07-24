package com.example.lbmeals.feature_categories.data.data_source

import com.example.lbmeals.feature_categories.domain.model.Category
import com.example.lbmeals.feature_categories.domain.model.CategoryRequest
import retrofit2.Response
import retrofit2.http.GET

interface CategoryService {
    @GET("api/json/v1/1/categories.php")
    suspend fun getCategories(): Response<CategoryRequest>
}