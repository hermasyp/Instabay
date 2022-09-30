package com.catnip.instabay.model


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("hits")
    val hits: List<Post>?,
    @SerializedName("total")
    val total: Int?,
    @SerializedName("totalHits")
    val totalHits: Int?
)