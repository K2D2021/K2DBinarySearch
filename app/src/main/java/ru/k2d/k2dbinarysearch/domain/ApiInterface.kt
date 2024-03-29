package ru.k2d.k2dbinarysearch.domain

import retrofit2.Response
import retrofit2.http.GET
import ru.k2d.k2dbinarysearch.domain.models.RetrofitRandomNumber

interface ApiInterface {
    @GET("api/number/random_number")
    suspend fun getRandomNumberRetro(): Response<RetrofitRandomNumber>
}