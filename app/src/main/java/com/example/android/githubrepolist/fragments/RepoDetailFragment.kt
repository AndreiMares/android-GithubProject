package com.example.android.githubrepolist.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.githubrepolist.Constants
import com.example.android.githubrepolist.R
import com.example.android.githubrepolist.activities.RepoDetailActivity
import com.example.android.githubrepolist.databinding.FragmentRepoDetailBinding

class RepoDetailFragment : Fragment() {

    private lateinit var mViewBinding: FragmentRepoDetailBinding

    //region Override

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        this.mViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_repo_detail, container, false)

        this.mViewBinding.model = (activity as RepoDetailActivity).obtainViewModel()

        return this.mViewBinding.root
    }

    override fun onResume() {
        super.onResume()
        this.mViewBinding.model?.loadRepository(arguments?.getString(Constants.REPOSITORY_NAME))
    }

    //endregion

    companion object {

        fun newInstance(taskId: String) = RepoDetailFragment().apply {
            arguments = Bundle().apply {
                putString(Constants.REPOSITORY_NAME, taskId)
            }
        }
    }
}