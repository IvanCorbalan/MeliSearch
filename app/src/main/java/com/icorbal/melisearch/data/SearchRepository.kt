package com.icorbal.melisearch.data

import com.icorbal.melisearch.data.model.SearchModel
import com.icorbal.melisearch.data.network.SearchService

class SearchRepository {

    private val api = SearchService()

    suspend fun getSearch(query: String): SearchModel? {
        return api.search(query)
    }
}