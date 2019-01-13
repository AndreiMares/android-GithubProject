package com.example.android.githubrepolist.activities

import android.arch.lifecycle.Observer
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import com.example.android.githubrepolist.Constants
import com.example.android.githubrepolist.R
import com.example.android.githubrepolist.fragments.RepoDetailFragment
import com.example.android.githubrepolist.utils.replaceFragmentInActivity
import com.example.android.githubrepolist.viewmodel.RepoDetailViewModel
import com.example.android.githubrepolist.utils.obtainViewModel

class RepoDetailActivity : BaseActivity() {

    //region Override

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        super.setContentView(R.layout.activity_repo_detail)

        super.configureToolbar(
            true
            , resources.getString(R.string.activity_repoDetail_tv_title)
        )

        this.findOrCreateFragment()

        this.obtainViewModel().apply {
            openReadmeURL.observe(this@RepoDetailActivity, Observer<String> { it ->

                it?.let { openURLActivity(it) }
            })

        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when (item?.itemId) {

            android.R.id.home -> {
                super.onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    //endregion

    //region Private functions

    private fun findOrCreateFragment() =
        supportFragmentManager.findFragmentById(R.id.activity_repoDetail_rl_middle)
            ?: RepoDetailFragment.newInstance(intent.getStringExtra(Constants.REPOSITORY_NAME)).also {
                replaceFragmentInActivity(it, R.id.activity_repoDetail_rl_middle)
            }

    private fun openURLActivity(value: String) {

        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(value)
        startActivity(i)
    }

    //endregion

    //region public functions

    fun obtainViewModel(): RepoDetailViewModel = obtainViewModel(RepoDetailViewModel::class.java)

    //endregion

}