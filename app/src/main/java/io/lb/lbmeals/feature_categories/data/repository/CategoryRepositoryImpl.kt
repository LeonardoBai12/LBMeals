package io.lb.lbmeals.feature_categories.data.repository

import io.lb.lbmeals.feature_categories.data.local.CategoryDao
import io.lb.lbmeals.feature_categories.data.mapper.toCategory
import io.lb.lbmeals.feature_categories.data.mapper.toCategoryEntity
import io.lb.lbmeals.feature_categories.data.remote.CategoryService
import io.lb.lbmeals.feature_categories.domain.model.Category
import io.lb.lbmeals.feature_categories.domain.repository.CategoryRepository
import io.lb.lbmeals.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CategoryRepositoryImpl(
    private val service: CategoryService,
    private val dao: CategoryDao,
) : CategoryRepository {

    override suspend fun getCategories(): Flow<Resource<List<Category>>> {
        return flow {
            emit(Resource.Loading(true))

            val localCategories = dao.searchCategories()
            emit(
                Resource.Success(
                    data = localCategories.map { it.toCategory() }
                )
            )

            if (localCategories.isNotEmpty())
                emit(Resource.Loading(false))

            val remoteCategories = try {
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

            remoteCategories?.body()?.categories?.takeIf {
                it.isNotEmpty()
            }?.let { categories ->
                dao.clearCategories()
                dao.insertCategory(
                    categories.map { it.toCategoryEntity() }
                )

                emit(
                    Resource.Success(
                        data = categories
                    )
                )
            }

            emit(Resource.Loading(false))
        }
    }
}
