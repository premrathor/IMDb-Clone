package com.example.blog

import retrofit2.Call
import retrofit2.http.GET

interface BlogApiService {
    @GET("todos") 
    fun getBlogPosts(): Call<List<BlogPost>>
}
