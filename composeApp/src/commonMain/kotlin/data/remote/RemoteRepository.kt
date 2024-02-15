package data.remote

import domain.Beer

interface RemoteRepository {
 suspend fun getBeers(page: Long,perPage: Long): List<Beer>
}