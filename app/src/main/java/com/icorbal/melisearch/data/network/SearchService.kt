package com.icorbal.melisearch.data.network

import com.icorbal.melisearch.data.model.SearchModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchService @Inject constructor(
    private val api: SearchApiClient
) {


    suspend fun search(query: String): SearchModel? {
        return withContext(Dispatchers.IO) {
            val response = api.search(query)
            response.body()
        }
    }

}