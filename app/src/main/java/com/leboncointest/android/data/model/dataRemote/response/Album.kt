package com.leboncointest.android.data.model.dataRemote.response

import com.google.gson.annotations.SerializedName

data class Album (
    @SerializedName("albumId")
    val albumId: Long,
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String
)