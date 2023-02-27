package io.lb.lbmeals.feature_categories.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.lb.lbmeals.core.data.local.AppDatabase
import io.lb.lbmeals.feature_categories.data.local.CategoryDao
import io.lb.lbmeals.feature_categories.data.remote.CategoryService
import io.lb.lbmeals.feature_categories.data.repository.CategoryRepositoryImpl
import io.lb.lbmeals.feature_categories.domain.repository.CategoryRepository
import io.lb.lbmeals.feature_categories.domain.use_case.CategoryUseCases
import io.lb.lbmeals.feature_categories.domain.use_case.GetCategoriesUseCase
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object CategoryModule {
    @Provides
    fun providesCategoryService(retrofit: Retrofit): CategoryService {
        return retrofit.create(CategoryService::class.java)
    }

    @Provides
    fun providesCategoryDao(appDatabase: AppDatabase): CategoryDao {
        return appDatabase.categoryDao
    }

    @Provides
    fun providesCategoryRepository(
        service: CategoryService,
        dao: CategoryDao,
    ): CategoryRepository {
        return CategoryRepositoryImpl(
            service,
            dao
        )
    }

    @Provides
    fun providesCategoryUseCases(repository: CategoryRepository): CategoryUseCases {
        return CategoryUseCases(
            getCategoriesUseCase = GetCategoriesUseCase(repository)
        )
    }
}
