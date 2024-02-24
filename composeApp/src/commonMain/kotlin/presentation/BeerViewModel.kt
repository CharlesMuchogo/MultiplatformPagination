package presentation

import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.plcoding.composepaging3caching.data.mappers.toBeer
import data.network.ConnectivityObserver
import database.BeerEntity
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class BeerViewModel(pager: Pager<Int, BeerEntity>, private val  connectivityObserver: ConnectivityObserver): ScreenModel {
    val beerPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toBeer() }
        }
        .cachedIn(screenModelScope)

    init {
        getConnectionFlow()
    }

    private fun getConnectionFlow() {
        screenModelScope.launch {
            connectivityObserver.observe().catch {
                it.printStackTrace()
            }.collect{
                println("Network state is -> $it")
            }
        }
    }
}