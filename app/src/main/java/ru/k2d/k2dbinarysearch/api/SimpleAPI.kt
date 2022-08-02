package ru.k2d.k2dbinarysearch.api

import retrofit2.http.GET
import ru.k2d.k2dbinarysearch.model.Post

interface SimpleAPI {
    @GET("api/number/random_number")
    suspend fun getPost(): Post
}