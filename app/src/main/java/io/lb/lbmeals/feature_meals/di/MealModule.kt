package io.lb.lbmeals.feature_meals.di

import io.lb.lbmeals.feature_meals.data.data_source.MealService
import io.lb.lbmeals.feature_meals.data.repository.MealRepositoryImpl
import io.lb.lbmeals.feature_meals.domain.repository.MealRepository
import io.lb.lbmeals.feature_meals.domain.use_case.GetMealsUseCase
import io.lb.lbmeals.feature_meals.domain.use_case.MealUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object MealModule {
    @Provides
    fun providesMealService(retrofit: Retrofit): MealService {
        return retrofit.create(MealService::class.java)
    }

    @Provides
    fun providesMealRepository(service: MealService): MealRepository {
        return MealRepositoryImpl(service)
    }

    @Provides
    fun providesMealUseCases(repository: MealRepository): MealUseCases {
        return MealUseCases(
            getMealsUseCase = GetMealsUseCase(repository),
        )
    }
}