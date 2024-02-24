
package di


import com.charlesmuchogo.multiplatformpaging.AppDatabase
import data.local.sqldelidht.DatabaseDataSource
import data.local.sqldelidht.SqlDelightDatabaseDataSource
import data.remote.BeerRemoteMediator
import data.remote.RemoteRepository
import data.remote.RemoteRepositoryImpl
import database.BeerEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import app.cash.sqldelight.paging3.QueryPagingSource
import data.local.DatabaseDriverFactory
import presentation.BeerViewModel

@OptIn(ExperimentalPagingApi::class)
fun commonModule() = module {
    single <AppDatabase>{
        AppDatabase(driver = get<DatabaseDriverFactory>().create())
    }

    single<DatabaseDataSource> {
        SqlDelightDatabaseDataSource(get())
    }

    single<RemoteRepository> {
        RemoteRepositoryImpl()
    }

    single<Pager<Int, BeerEntity>> {
        Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = BeerRemoteMediator(
                databaseDataSource = get(),
                beerApi = get()
            ),
            pagingSourceFactory = {
               QueryPagingSource(
                   context = Dispatchers.IO,
                   transacter = get<AppDatabase>().appDatabaseQueries,
                   countQuery = get<AppDatabase>().appDatabaseQueries.countBeerEntities(),
                   queryProvider = get<AppDatabase>().appDatabaseQueries::selectBeerEntities
               )
            }
        )
    }

    single<BeerViewModel> {
        BeerViewModel(pager = get(), connectivityObserver = get())
    }
}

expect fun platformModule(): Module
