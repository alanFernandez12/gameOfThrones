package com.trabajoIntegrador.gameOfThrones

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Personaje(
    val firstName: String,
    val lastName: String,
    val family: String,
    val title: String,
    val imageUrl: String
)