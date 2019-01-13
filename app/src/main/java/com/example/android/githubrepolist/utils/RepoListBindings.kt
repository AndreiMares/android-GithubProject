package com.example.android.githubrepolist.utils

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.example.android.githubrepolist.adapters.RepoListAdapter
import com.example.android.githubrepolist.data.Repo

@BindingAdapter("bind:listItems")
fun setItems(recyclerView: RecyclerView, items: List<Repo>) {
    with(recyclerView.adapter as RepoListAdapter) {
        loadList(items)
    }
}