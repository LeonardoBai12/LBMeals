package com.example.lbmeals.feature_categories.di

import com.example.lbmeals.feature_categories.data.data_source.CategoryService
import com.example.lbmeals.feature_categories.data.repository.CategoryRepositoryImpl
import com.example.lbmeals.feature_categories.domain.repository.CategoryRepository
import com.example.lbmeals.feature_categories.domain.use_case.CategoryUseCases
import com.example.lbmeals.feature_categories.domain.use_case.GetCategoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class CategoryModule {
    @Provides
    fun providesCategoryService(retrofit: Retrofit): CategoryService {
        return retrofit.create(CategoryService::class.java)
    }

    @Provides
    fun providesCategoryRepository(service: CategoryService): CategoryRepository {
        return CategoryRepositoryImpl(service)
    }

    @Provides
    fun providesCategoryUseCases(repository: CategoryRepository): CategoryUseCases {
        return CategoryUseCases(
            getCategoryUseCase = GetCategoryUseCase(repository)
        )
    }
}