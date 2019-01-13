package com.example.android.githubrepolist.data.remote

import com.example.android.githubrepolist.data.Repo

interface GitDataSource {

    interface LoadReposCallback {

        fun onReposLoaded(repos: List<Repo>)

        fun onError()

    }

    interface LoadRepoCallback {

        fun onRepoLoaded(repo: Repo)

        fun onError()
    }

    interface LoadURLCallback {

        fun onURLLoaded(url: String)

        fun onError()
    }


}