package com.example.android.githubrepolist.listeners

import com.example.android.githubrepolist.data.Repo

interface RepoItemUserActionListener {

  fun onRepoClicked(repo: Repo)
}