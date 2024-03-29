package ru.k2d.k2dbinarysearch.domain

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.k2d.k2dbinarysearch.data.Constants.Companion.BASE_URL

object RetrofitClient {
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}