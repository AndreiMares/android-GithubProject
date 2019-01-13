package com.example.android.githubrepolist.utils

import com.example.android.githubrepolist.data.remote.GitRemoteDataSource
import com.example.android.githubrepolist.data.remote.GitRepository

object InjectorUtils {

    fun provideGitRepository(): GitRepository{

        return GitRepository.getInstance(GitRemoteDataSource())
    }
}