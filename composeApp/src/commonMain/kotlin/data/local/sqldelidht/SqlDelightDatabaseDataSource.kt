package data.local.sqldelidht

import com.charlesmuchogo.multiplatformpaging.AppDatabase
import domain.Beer


class SqlDelightDatabaseDataSource(db: AppDatabase): DatabaseDataSource {
    private val queries = db.appDatabaseQueries

    override fun insertBeerEntity(beer: Beer) {
        queries.insertBeerEntity(
           id = beer.id,
           name = beer.name,
            imageUrl = beer.imageUrl,
            firstBrewed = beer.firstBrewed,
            tagline = beer.tagline,
            description = beer.description
       )
    }

    override fun cleasrDatabase() {
        queries.clearBeerEntities()
    }

}