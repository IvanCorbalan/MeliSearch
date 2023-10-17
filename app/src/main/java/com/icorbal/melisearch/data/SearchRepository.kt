package com.icorbal.melisearch.data

import com.icorbal.melisearch.data.model.SearchModel
import com.icorbal.melisearch.data.network.SearchService
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val api : SearchService
) {

    suspend fun getSearch(query: String): SearchModel? {
        return api.search(query)
    }
}