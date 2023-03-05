package io.lb.lbmeals.feature_meals.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.lb.lbmeals.core.data.local.AppDatabase
import io.lb.lbmeals.feature_meals.data.local.MealDao
import io.lb.lbmeals.feature_meals.data.remote.MealService
import io.lb.lbmeals.feature_meals.data.repository.MealRepositoryImpl
import io.lb.lbmeals.feature_meals.domain.repository.MealRepository
import io.lb.lbmeals.feature_meals.domain.use_case.GetMealDetailsByIdUseCase
import io.lb.lbmeals.feature_meals.domain.use_case.GetMealsUseCase
import io.lb.lbmeals.feature_meals.domain.use_case.MealUseCases
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object MealModule {
    @Provides
    fun providesMealService(retrofit: Retrofit): MealService {
        return retrofit.create(MealService::class.java)
    }

    @Provides
    fun providesMealDao(appDatabase: AppDatabase): MealDao {
        return appDatabase.mealDao
    }

    @Provides
    fun providesMealRepository(
        service: MealService,
        dao: MealDao,
    ): MealRepository {
        return MealRepositoryImpl(
            service,
            dao,
        )
    }

    @Provides
    fun providesMealUseCases(repository: MealRepository): MealUseCases {
        return MealUseCases(
            getMealsUseCase = GetMealsUseCase(repository),
            getMealDetailsByIdUseCase = GetMealDetailsByIdUseCase(repository)
        )
    }
}
