package com.icorbal.melisearch.data.model

data class SearchModel(
    val siteId: String = "",
    val query: String = "",
    val results: List<Result>
)

data class Result(
    val id: String,
    val title: String,
    val price: String,
    val currencyId: String,
    val thumbnail: String
)