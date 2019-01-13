package com.example.android.githubrepolist.data.remote

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitInterface {

    @GET("/search/repositories?q=topic:android&sort=stars&order=desc")
    fun getAllRepositories(): Call<JsonObject>

    @GET("/repositories/{id}")
    fun  getRepository(@Path("id") id: String) : Call<JsonObject>

    @GET("/repositories/{id}/readme")
    fun  getRepositoryReadme(@Path("id") id: String) : Call<JsonObject>

}