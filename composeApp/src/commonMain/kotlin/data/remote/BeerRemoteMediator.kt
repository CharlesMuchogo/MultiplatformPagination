package data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.plcoding.composepaging3caching.data.mappers.toBeer
import data.local.sqldelidht.DatabaseDataSource
import database.BeerEntity

@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator(
    private val  databaseDataSource: DatabaseDataSource,
    private val beerApi: RemoteRepository
): RemoteMediator<Int, BeerEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeerEntity>
    ): MediatorResult {
        return try {


            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val beers = beerApi.getBeers(
                page = loadKey,
                perPage = state.config.pageSize.toLong()
            )

            if(loadType == LoadType.REFRESH) {
                databaseDataSource.cleasrDatabase()
            }
            beers.map {
                databaseDataSource.insertBeerEntity(it)
            }

            MediatorResult.Success(
                endOfPaginationReached = beers.isEmpty()
            )
        } catch(e: Exception) {
            MediatorResult.Error(e)
        }
    }
}