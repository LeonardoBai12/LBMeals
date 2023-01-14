package io.lb.lbmeals.feature_meals.data.repository

import io.lb.lbmeals.feature_meals.data.data_source.MealService
import io.lb.lbmeals.feature_meals.domain.model.Meal
import io.lb.lbmeals.feature_meals.domain.repository.MealRepository
import io.lb.lbmeals.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class MealRepositoryImpl(
    private val service: MealService
): MealRepository {
    override suspend fun getMealsByCategory(category: String): Flow<Resource<List<Meal>>> {
        return flow {
            val response = try {
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

            response?.let { meals ->
                emit(
                    Resource.Success(
                        data = meals.takeIf {
                            it.isSuccessful
                        }?.let {
                            it.body()?.meals
                        } ?: emptyList()
                    )
                )
            }

            emit(Resource.Loading(false))
        }
    }
}
