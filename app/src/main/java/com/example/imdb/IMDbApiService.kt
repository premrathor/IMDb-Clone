package com.example.imdb

import retrofit2.Call
import retrofit2.http.GET

interface IMDbApiService {
    @GET("todos")
    fun getIMDbClones(): Call<List<IMDbClone>>
}
