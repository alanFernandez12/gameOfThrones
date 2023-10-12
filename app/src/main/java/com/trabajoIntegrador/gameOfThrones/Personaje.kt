package com.trabajoIntegrador.gameOfThrones

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Personaje(
    @Json(name = "id") val id: Int,
    @Json(name = "firstName") val firstName: String,
    @Json(name = "lastName") val lastName: String,
    @Json(name = "title") val title: String,
    @Json(name = "family") val family: String,
    @Json(name = "imageUrl") val imageUrl: String
)