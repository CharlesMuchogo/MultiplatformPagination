package data.remote

import data.network.Http
import domain.Beer
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter


class RemoteRepositoryImpl :
    RemoteRepository {
    override suspend fun getBeers(page: Long,perPage: Long): List<Beer> {
        return try {

            val beers = Http.client.get("beers"){
                parameter("page",page)
                parameter("per_page",perPage)
            }
            beers.body<List<Beer>>()
        }catch (e:Exception){
            e.printStackTrace()
            emptyList()
        }
    }

}