package data.local.sqldelidht

import domain.Beer


interface DatabaseDataSource {

    fun insertBeerEntity(beer: Beer)
    fun clearDatabase()

}