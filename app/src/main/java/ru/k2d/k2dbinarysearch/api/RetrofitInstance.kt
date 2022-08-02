package ru.k2d.k2dbinarysearch.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.k2d.k2dbinarysearch.util.Constants.Companion.BASE_URL

object RetrofitInstance {
    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: SimpleAPI by lazy{
        retrofit.create(SimpleAPI::class.java)
    }
}