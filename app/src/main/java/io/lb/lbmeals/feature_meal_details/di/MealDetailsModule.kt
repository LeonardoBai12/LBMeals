package io.lb.lbmeals.feature_meal_details.di

import io.lb.lbmeals.feature_meal_details.data.data_source.MealDetailsService
import io.lb.lbmeals.feature_meal_details.data.repository.MealDetailsRepositoryImpl
import io.lb.lbmeals.feature_meal_details.domain.repository.MealDetailsRepository
import io.lb.lbmeals.feature_meal_details.domain.use_case.GetMealDetailsByIdUseCase
import io.lb.lbmeals.feature_meal_details.domain.use_case.MealDetailsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class MealDetailsModule {
    @Provides
    fun providesMealDetailsService(retrofit: Retrofit): MealDetailsService {
        return retrofit.create(MealDetailsService::class.java)
    }

    @Provides
    fun providesMealDetailsRepository(service: MealDetailsService): MealDetailsRepository {
        return MealDetailsRepositoryImpl(service)
    }

    @Provides
    fun providesMealDetailsUseCases(repository: MealDetailsRepository): MealDetailsUseCases {
        return MealDetailsUseCases(
            getMealDetailsByIdUseCase = GetMealDetailsByIdUseCase(repository),
        )
    }
}