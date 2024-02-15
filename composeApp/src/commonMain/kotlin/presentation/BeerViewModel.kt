package presentation

import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.plcoding.composepaging3caching.data.mappers.toBeer
import database.BeerEntity
import kotlinx.coroutines.flow.map

class BeerViewModel(pager: Pager<Int, BeerEntity>): ScreenModel {
    val beerPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toBeer() }
        }
        .cachedIn(screenModelScope)
}