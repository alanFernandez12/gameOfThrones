package com.trabajoIntegrador.gameOfThrones

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    private val baseURL="https://thronesapi.com/"
    private val moshi= Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    val retrofit= Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}