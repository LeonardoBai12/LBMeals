package com.example.lbmeals.feature_meal.di

import com.example.lbmeals.feature_meal.data.data_source.MealService
import com.example.lbmeals.feature_meal.data.repository.MealRepositoryImpl
import com.example.lbmeals.feature_meal.domain.repository.MealRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class MealModule {
    @Provides
    fun providesMealService(retrofit: Retrofit): MealService {
        return retrofit.create(MealService::class.java)
    }

    @Provides
    fun providesMealRepository(service: MealService): MealRepository {
        return MealRepositoryImpl(service)
    }
}