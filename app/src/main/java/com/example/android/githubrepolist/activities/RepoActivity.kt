package com.example.android.githubrepolist.activities

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import com.example.android.githubrepolist.Constants
import com.example.android.githubrepolist.R
import com.example.android.githubrepolist.fragments.RepoListFragment
import com.example.android.githubrepolist.utils.obtainViewModel
import com.example.android.githubrepolist.utils.replaceFragmentInActivity
import com.example.android.githubrepolist.viewmodel.ReposViewModel

class RepoActivity : BaseActivity() {

    private lateinit var mReposViewModel: ReposViewModel

    //region Override

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        super.setContentView(R.layout.activity_repo)

        super.configureToolbar(false, resources.getString(R.string.activity_repo_tv_title))

        this.findOrCreateFragment()

        this.mReposViewModel = this.obtainViewModel().apply {
            openRepoEvent.observe(this@RepoActivity, Observer<String> { it ->

                it?.let { openRepoDetailActivity(it) }
            })

        }

    }

    //endregion

    //region Private functions

    private fun findOrCreateFragment() =
        supportFragmentManager.findFragmentById(R.id.activity_repo_rl_middle)
            ?: RepoListFragment.newInstance().also {
                replaceFragmentInActivity(it, R.id.activity_repo_rl_middle)
            }

    private fun openRepoDetailActivity(value: String) {
        val intent = Intent(this@RepoActivity, RepoDetailActivity::class.java)
        intent.putExtra(Constants.REPOSITORY_NAME, value)
        startActivity(intent)
    }

    //endregion

    //region Public functions

    fun obtainViewModel(): ReposViewModel = obtainViewModel(ReposViewModel::class.java)

    //endregion

}
