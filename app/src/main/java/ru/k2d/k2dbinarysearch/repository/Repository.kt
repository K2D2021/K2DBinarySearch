package ru.k2d.k2dbinarysearch.repository

import retrofit2.Response
import ru.k2d.k2dbinarysearch.api.RetrofitInstance
import ru.k2d.k2dbinarysearch.model.Post

class Repository {
    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }
}