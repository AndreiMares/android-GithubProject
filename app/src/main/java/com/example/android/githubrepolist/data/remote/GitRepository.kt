package com.example.android.githubrepolist.data.remote

import com.example.android.githubrepolist.data.Repo

class GitRepository(
    private val mGitRemoteDataSource: GitRemoteDataSource
) {

    //region Public functions

    fun getRepositoryList(callBack: GitDataSource.LoadReposCallback) {

        this.mGitRemoteDataSource.getRepositoryList(object : GitDataSource.LoadReposCallback {

            override fun onReposLoaded(repos: List<Repo>) = callBack.onReposLoaded(repos)

            override fun onError() = callBack.onError()

        })
    }

    fun getRepository(repoId: String, callback: GitDataSource.LoadRepoCallback) {

        this.mGitRemoteDataSource.getRepository(repoId, object : GitDataSource.LoadRepoCallback {

            override fun onRepoLoaded(repo: Repo) = callback.onRepoLoaded(repo)

            override fun onError() = callback.onError()

        })
    }

    fun getRepositoryReadme(repoId: String, callback: GitDataSource.LoadURLCallback) {

        this.mGitRemoteDataSource.getRepositoryReadme(repoId, object : GitDataSource.LoadURLCallback {

            override fun onURLLoaded(url: String)  = callback.onURLLoaded(url)

            override fun onError()  = callback.onError()

        })
    }

    //endregion

    companion object {
        @Volatile
        private var INSTANCE: GitRepository? = null

        fun getInstance(gitRemoteDateSource: GitRemoteDataSource): GitRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: GitRepository(gitRemoteDateSource).also { INSTANCE = it }
            }

    }
}