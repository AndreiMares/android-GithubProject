package com.example.android.githubrepolist.activities

import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.snippet_top_appbar.*

open class BaseActivity : AppCompatActivity() {

    protected fun configureToolbar(showArrow: Boolean, title: String) {
        //sets the custom toolbar
        setSupportActionBar(snippet_top_appbar_tb_header)

        //sets toolbar title
        snippet_top_appBar_tv_title.text = title

        //set toolbar back arrow menu item
        supportActionBar?.setDisplayHomeAsUpEnabled(showArrow)
        supportActionBar?.setDisplayShowHomeEnabled(showArrow)

    }

}