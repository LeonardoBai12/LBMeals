package io.lb.lbmeals.feature_meals.data.repository

import io.lb.lbmeals.feature_meals.data.local.MealDao
import io.lb.lbmeals.feature_meals.data.mapper.toMeal
import io.lb.lbmeals.feature_meals.data.mapper.toMealEntity
import io.lb.lbmeals.feature_meals.data.remote.MealService
import io.lb.lbmeals.feature_meals.domain.model.Meal
import io.lb.lbmeals.feature_meals.domain.repository.MealRepository
import io.lb.lbmeals.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class MealRepositoryImpl(
    private val service: MealService,
    private val dao: MealDao,
) : MealRepository {
    override suspend fun getMealsByCategory(category: String): Flow<Resource<List<Meal>>> {
        return flow {
            emit(Resource.Loading(true))

            dao.searchMeals(category).takeIf {
                it.isNotEmpty()
            }?.let {meals ->
                emit(
                    Resource.Success(
                        data = meals.map { it.toMeal() }
                    )
                )

                emit(Resource.Loading(false))
            }

            val remoteMeals = try {
                service.getMealByCategory(category)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteMeals?.body()?.meals?.takeIf {
                it.isNotEmpty()
            }?.let { meals ->
                dao.clearMeals()
                dao.insertMeal(
                    meals.map { it.toMealEntity() }
                )

                emit(
                    Resource.Success(
                        data = meals
                    )
                )
            }

            emit(Resource.Loading(false))
        }
    }
}
