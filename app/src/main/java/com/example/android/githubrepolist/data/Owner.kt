package com.example.android.githubrepolist.data

import com.google.gson.annotations.SerializedName

data class Owner (
    var login: String,
    @SerializedName("avatar_url")
    var avatarUrl: String
) {

    constructor() : this("","")
}