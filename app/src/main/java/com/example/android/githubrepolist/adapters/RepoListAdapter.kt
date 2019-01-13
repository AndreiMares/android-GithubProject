package com.example.android.githubrepolist.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.android.githubrepolist.R
import com.example.android.githubrepolist.listeners.RepoItemUserActionListener
import com.example.android.githubrepolist.data.Repo
import com.example.android.githubrepolist.databinding.RowMemberListItemBinding
import com.example.android.githubrepolist.viewholders.RepoListViewHolder
import com.example.android.githubrepolist.viewmodel.ReposViewModel
import kotlinx.android.synthetic.main.row_member_list_item.view.*

class RepoListAdapter(
    private var mRepoList: List<Repo>,
    private val mReposViewModel: ReposViewModel
) : RecyclerView.Adapter<RepoListViewHolder>() {

    private lateinit var mRowMemberListItemBinding: RowMemberListItemBinding

    //region Override

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RepoListViewHolder {

        val inflater = LayoutInflater.from(p0.context)

        this.mRowMemberListItemBinding = DataBindingUtil
            .inflate(
                inflater
                , R.layout.row_member_list_item
                , p0
                , false
            )

        return RepoListViewHolder(this.mRowMemberListItemBinding.root)
    }

    override fun onBindViewHolder(p0: RepoListViewHolder, p1: Int) {

        //get a single repository item based by position
        val repository = this.mRepoList[p1]

        //update Views
        p0.view.row_member_list_item_tv_description.text = repository.description
        p0.view.row_member_list_item_tv_name.text = repository.name

        //loading picture from URL
        Glide.with(p0.view.row_member_list_item_iv_avatar.context)
            .load(repository.owner.avatarUrl)
            .into(p0.view.row_member_list_item_iv_avatar)

        //get the action when user clicks on a repository
        val userActionListener = object : RepoItemUserActionListener {
            override fun onRepoClicked(repo: Repo) {

                val a = repo.id.toString()
                mReposViewModel.openRepoEvent.value = a
            }
        }

//        //set the internal layout variables
        with(this.mRowMemberListItemBinding){
            repoItem = repository
            listener = userActionListener
        }
    }

    override fun getItemCount(): Int = this.mRepoList.size

    //endregion

    //region Public functions

    fun loadList(repoList: List<Repo>) = this.setAdapterList(repoList)

    //endregion

    //region Private functions

    private fun setAdapterList(repoList: List<Repo>) {

        this.mRepoList = repoList
        notifyDataSetChanged()

    }

    //endregion

}