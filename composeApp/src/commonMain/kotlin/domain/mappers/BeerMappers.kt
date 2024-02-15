package com.plcoding.composepaging3caching.data.mappers


import database.BeerEntity
import domain.Beer


fun BeerEntity.toBeer(): Beer {
    return Beer(
        id = id.toLong(),
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = firstBrewed,
        imageUrl = imageUrl
    )
}