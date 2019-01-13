package com.example.android.githubrepolist.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.android.githubrepolist.data.remote.GitRepository
import com.example.android.githubrepolist.utils.InjectorUtils

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(
    private val mGitRepository: GitRepository
) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(ReposViewModel::class.java) ->
                    ReposViewModel(mGitRepository)
                isAssignableFrom(RepoDetailViewModel::class.java) ->
                    RepoDetailViewModel(mGitRepository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {

        @Volatile private var INSTANCE: ViewModelFactory? = null

        fun getInstance() =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE ?: ViewModelFactory(
                    InjectorUtils.provideGitRepository())
                    .also { INSTANCE = it }
            }

    }
}