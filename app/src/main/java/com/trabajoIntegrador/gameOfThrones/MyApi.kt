package com.trabajoIntegrador.gameOfThrones

import retrofit2.Call
import retrofit2.http.GET
interface MyApi {

    @GET("api/v2/Characters")
    fun getCharacters(): Call<List<Personaje>>
}