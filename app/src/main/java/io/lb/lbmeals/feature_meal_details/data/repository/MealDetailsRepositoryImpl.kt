package io.lb.lbmeals.feature_meal_details.data.repository

import io.lb.lbmeals.feature_meal_details.data.data_source.MealDetailsService
import io.lb.lbmeals.feature_meal_details.domain.repository.MealDetailsRepository
import io.lb.lbmeals.feature_meals.domain.model.Meal
import io.lb.lbmeals.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class MealDetailsRepositoryImpl(
    private val service: MealDetailsService,
): MealDetailsRepository {
    override suspend fun getMealDetailsById(id: String): Flow<Resource<Meal?>> {
        return flow {
            val response = try {
                service.getMealDetailsById(id)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            response?.let { meal ->
                emit(
                    Resource.Success(
                        data = meal.takeIf {
                            it.isSuccessful
                        }?.let {
                            it.body()?.meals?.first()
                        }
                    )
                )
            }

            emit(Resource.Loading(false))
        }
    }
}
