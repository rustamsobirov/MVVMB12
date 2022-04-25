package me.ruyeo.mvvmb12.data.api

import me.ruyeo.mvvmb12.model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts() : List<Post>

}