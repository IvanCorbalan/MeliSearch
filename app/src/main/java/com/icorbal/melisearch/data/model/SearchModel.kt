package com.icorbal.melisearch.data.model

import com.google.gson.annotations.SerializedName

data class SearchModel(
    val siteId: String = "",
    val query: String = "",
    val results: List<Result>
)

data class Result(
    val id: String,
    val title: String,
    val price: String,
    @SerializedName("currency_id")val currencyId: String,
    val thumbnail: String,
    val permalink: String
)