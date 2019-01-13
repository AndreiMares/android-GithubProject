package com.example.android.githubrepolist.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import com.example.android.githubrepolist.SingleLiveEvent
import com.example.android.githubrepolist.data.Repo
import com.example.android.githubrepolist.data.remote.GitDataSource
import com.example.android.githubrepolist.data.remote.GitRepository

class ReposViewModel(
    private val mGitRepository: GitRepository
) : ViewModel() {

    internal val openRepoEvent = SingleLiveEvent<String>()

    //region Observable fields for updating View

    val items: ObservableList<Repo> = ObservableArrayList()
    val onDataLoading = ObservableBoolean(false)
    val onEmpty = ObservableBoolean(false)
    val onDataLoadingError = ObservableBoolean(false)

    //endregion

    //region Public functions

    fun loadList() = this.loadRepoList()

    //endregion

    //region Private functions

    private fun loadRepoList() {
        this.onDataLoading.set(true)

        this.mGitRepository.getRepositoryList(object : GitDataSource.LoadReposCallback {
            override fun onReposLoaded(repos: List<Repo>) {
                val repoList: List<Repo> = repos

                onDataLoading.set(false)
                onDataLoadingError.set(false)

                with(items) {
                    clear()
                    addAll(repoList)
                    onEmpty.set(isEmpty())
                }
            }

            override fun onError() {

                onDataLoadingError.set(true)

            }
        })
    }

    //endregion

}