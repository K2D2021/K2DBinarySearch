package ru.k2d.k2dbinarysearch.repository

import ru.k2d.k2dbinarysearch.api.RetrofitInstance
import ru.k2d.k2dbinarysearch.model.Post

class Repository {
    suspend fun getPost(): Post {
        return RetrofitInstance.api.getPost()
    }
}