package com.example.android.githubrepolist.data.remote

import com.example.android.githubrepolist.data.Owner
import com.example.android.githubrepolist.data.Repo
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitRemoteDataSource {

    //region Public functions

    //returns a list of repo items
    fun getRepositoryList(callback: GitDataSource.LoadReposCallback) {

        //retrofit call
        GitRemoteDataSource.getInstance().create(GitInterface::class.java)
            .getAllRepositories().enqueue(object : Callback<JsonObject> {
                override fun onFailure(call: Call<JsonObject>, t: Throwable) = callback.onError()

                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    val repoList = ArrayList<Repo>()

                    if (response.isSuccessful) {

                        //getting response as json object
                        val jsonObject = response.body()

                        //getting an array of item
                        val jsonArray = jsonObject?.getAsJsonArray("items")

                        //materialize each jsonObject into Repo object and add it to the repoList
                        jsonArray!!.iterator().forEach { it ->
                            val newObj = it.asJsonObject

                            val ownerJsonObject = newObj.getAsJsonObject("owner")

                            val owner = Owner(
                                ownerJsonObject.get("login").asString
                                , ownerJsonObject.get("avatar_url").asString
                            )

                            val repo = Repo(
                                newObj.asJsonObject.get("name").asString
                                , newObj.get("description").asString
                                , newObj.get("id").asLong
                                , owner
                                , newObj.get("forks").asInt
                                , newObj.get("watchers").asInt
                                , newObj.get("url").asString
                            )

                            repoList.add(repo)

                        }

                        callback.onReposLoaded(repoList)

                    } else {
                        callback.onError()
                    }
                }
            })
    }

    //returns single repo item
    fun getRepository(repoId: String, callback: GitDataSource.LoadRepoCallback) {

        //retrofit call
        GitRemoteDataSource.getInstance().create(GitInterface::class.java)
            .getRepository(repoId).enqueue(object : Callback<JsonObject> {
                override fun onFailure(call: Call<JsonObject>, t: Throwable) = callback.onError()

                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                    if (response.isSuccessful) {

                        //getting response as json object
                        val jsonObject = response.body()

                        //materialize jsonObject into Repo object and sends it to be visualizedf
                        jsonObject?.let {

                            val repo = Repo(
                                it.asJsonObject.get("name").asString
                                , it.get("description").asString
                                , it.get("id").asLong
                                , Owner()
                                , it.get("forks").asInt
                                , it.get("watchers").asInt
                                , it.get("url").asString
                            )

                            callback.onRepoLoaded(repo)
                        }

                    } else {
                        callback.onError()
                    }
                }
            })
    }

    //return selected repository readme URL
    fun getRepositoryReadme(repoId: String, callback: GitDataSource.LoadURLCallback) {

        //retrofit call
        GitRemoteDataSource.getInstance().create(GitInterface::class.java)
            .getRepositoryReadme(repoId).enqueue(object : Callback<JsonObject> {

                override fun onFailure(call: Call<JsonObject>, t: Throwable) = callback.onError()

                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                    if (response.isSuccessful) {

                        //getting response as json object
                        val jsonObject = response.body()

                        jsonObject?.let {
                            val readmeURL = it.get("html_url").asString

                            callback.onURLLoaded(readmeURL)
                        }

                    } else {
                        callback.onError()
                    }
                }
            })
    }

    //endregion

    companion object {
        @Volatile
        private var INSTANCE: Retrofit? = null

        fun getInstance(): Retrofit =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Retrofit.Builder().baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create()).build().also { INSTANCE = it }
            }

    }
}