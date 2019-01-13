package com.example.android.githubrepolist.data

data class Repo(
    var name: String = "",
    var description: String = "",
    var id: Long = 0,
    val owner: Owner = Owner(),
    var forks: Int = 0,
    var watchers: Int = 0,
    var url: String = ""
) {


    var repoForks: String = ""
        get() = this.forks.toString()

    var repoWatchers: String = ""
        get() = this.watchers.toString()

//    constructor() : this("", "", 0, Owner(), 0,0 )
}