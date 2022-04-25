package me.ruyeo.mvvmb12.repository

import me.ruyeo.mvvmb12.data.api.ApiService

class PostRepository(
    private val apiService: ApiService
) {
    suspend fun getPosts() = apiService.getPosts()
}