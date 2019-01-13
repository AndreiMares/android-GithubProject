package com.example.android.githubrepolist.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.android.githubrepolist.R
import com.example.android.githubrepolist.activities.RepoActivity
import com.example.android.githubrepolist.adapters.RepoListAdapter
import com.example.android.githubrepolist.data.Repo
import com.example.android.githubrepolist.databinding.FragmentRepoListBinding

class RepoListFragment : Fragment() {

    private lateinit var mViewBinding: FragmentRepoListBinding

    //region Override

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        this.mViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_repo_list, container, false)

        this.mViewBinding.model = (activity as RepoActivity).obtainViewModel()

        return this.mViewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        this.setAdapter()

    }

    override fun onResume() {
        super.onResume()

        this.mViewBinding.model?.loadList()
    }

    //endregion

    //region Private functions

    private fun setAdapter() {
        val viewModel = this.mViewBinding.model

        if (viewModel != null) {
            val repoListAdapter = RepoListAdapter(ArrayList(0), viewModel)
            val viewManager = LinearLayoutManager(context)

            this.mViewBinding.fragmentRecycleView.adapter = repoListAdapter
            this.mViewBinding.fragmentRecycleView.layoutManager = viewManager
        }
    }

    //endregion

    companion object {
        fun newInstance(): RepoListFragment = RepoListFragment()
    }

}