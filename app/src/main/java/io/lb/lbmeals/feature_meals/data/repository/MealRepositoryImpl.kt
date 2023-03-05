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

            val localMeals = dao.searchMealsByCategory(category)

            localMeals.takeIf {
                it.isNotEmpty()
            }?.let { meals ->
                emit(
                    Resource.Success(
                        data = meals.map { it.toMeal() }
                    )
                )

                emit(Resource.Loading(false))
            }

            val remoteMeals = try {
                service.getMealByCategory(category).body()?.meals
            } catch (e: IOException) {
                e.printStackTrace()
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                null
            }

            remoteMeals?.takeIf {
                it.isNotEmpty()
            }?.let { meals ->
                meals.filter { meal ->
                    !localMeals.any { meal.id == it.id }
                }.forEach { meal ->
                    dao.insertSingleMeal(meal.toMealEntity())
                }

                emit(
                    Resource.Success(
                        data = meals
                    )
                )
            }

            if (localMeals.isEmpty() && remoteMeals.isNullOrEmpty())
                emit(Resource.Error("Couldn't load data"))

            emit(Resource.Loading(false))
        }
    }

    override suspend fun getMealDetailsById(id: String): Flow<Resource<Meal?>> {
        return flow {
            emit(Resource.Loading(true))

            val localMeal = dao.searchMealById(id)

            localMeal?.takeIf {
                it.ingredient1 != null
            }?.let { mealEntity ->
                emit(
                    Resource.Success(
                        mealEntity.toMeal()
                    )
                )

                emit(Resource.Loading(false))
            }

            val remoteMeal = try {
                service.getMealDetailsById(id).body()?.meals?.first()
            } catch (e: IOException) {
                e.printStackTrace()
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                null
            }

            remoteMeal?.let { meal ->
                localMeal?.let {
                    dao.updateMeal(meal.toMealEntity(localId = localMeal.localId))
                }

                emit(
                    Resource.Success(
                        data = meal
                    )
                )
            }

            localMeal ?: remoteMeal ?: emit(
                Resource.Error("Couldn't load data")
            )

            emit(Resource.Loading(false))
        }
    }
}
