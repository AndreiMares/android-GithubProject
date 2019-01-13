package com.example.android.githubrepolist.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.example.android.githubrepolist.SingleLiveEvent
import com.example.android.githubrepolist.data.Repo
import com.example.android.githubrepolist.data.remote.GitDataSource
import com.example.android.githubrepolist.data.remote.GitRepository

class RepoDetailViewModel(
    private val mGitRepository: GitRepository
) : ViewModel() {

    //region Observable fields for updating View
    internal val openReadmeURL = SingleLiveEvent<String>()
    val repository = ObservableField<Repo>()
    val onDataLoading = ObservableBoolean(false)

    //endregion

    //region Public functions

    fun loadRepository(repoId: String?) =
        repoId?.let {
            this.loadData(it)
        }

    fun onButtonClick(repoId: Long?) =
        repoId?.let {
            this.loadRepositoryReadme(it.toString())
        }

    //endregion

    //region Private functions

    private fun loadData(repoId: String) {
        this.onDataLoading.set(true)

        this.mGitRepository.getRepository(repoId, object : GitDataSource.LoadRepoCallback {
            override fun onRepoLoaded(repo: Repo) {

                onDataLoading.set(false)

                repository.set(repo)

            }

            override fun onError() {
                repository.set(null)
                onDataLoading.set(false)

            }
        })
    }

    private fun loadRepositoryReadme(repoId: String){
        this.onDataLoading.set(true)

        this.mGitRepository.getRepositoryReadme(repoId, object : GitDataSource.LoadURLCallback {
            override fun onURLLoaded(url: String) {

                onDataLoading.set(false)

                openReadmeURL.value = url

            }

            override fun onError() {
                onDataLoading.set(false)

            }
        })
    }

    //endregion

}