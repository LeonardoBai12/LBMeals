package io.lb.lbmeals.feature_categories.data.repository

import io.lb.lbmeals.feature_categories.data.data_source.CategoryService
import io.lb.lbmeals.feature_categories.domain.model.Category
import io.lb.lbmeals.feature_categories.domain.repository.CategoryRepository
import io.lb.lbmeals.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CategoryRepositoryImpl(
    private val service: CategoryService
) : CategoryRepository {
    override suspend fun getCategories(): Flow<Resource<List<Category>>> {
        return flow {
            val response = try {
                service.getCategories()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            response?.let { categories ->
                emit(
                    Resource.Success(
                        data = categories.takeIf {
                            it.isSuccessful
                        }?.let {
                            it.body()?.categories
                        } ?: emptyList()
                    )
                )
            }

            emit(Resource.Loading(false))
        }
    }
}
