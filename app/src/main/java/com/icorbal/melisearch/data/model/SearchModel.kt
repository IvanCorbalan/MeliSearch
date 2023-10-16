package com.icorbal.melisearch.data.model

data class SearchModel(
    private val siteId: String = "",
    private val query: String = "",
    private val results: List<Result>
)

data class Result(
    val id: String,
    val title: String,
    val price: String,
    val currencyId: String,
)