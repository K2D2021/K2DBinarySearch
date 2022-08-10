package ru.k2d.k2dbinarysearch.api

import retrofit2.Response
import retrofit2.http.GET
import ru.k2d.k2dbinarysearch.models.retrofitRandomNumber

interface ApiInterface {
    @GET("api/number/random_number")
    suspend fun getRandomNumberRetro(): Response<retrofitRandomNumber>
}