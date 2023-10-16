package com.icorbal.melisearch.data.network

import com.icorbal.melisearch.core.RetrofitHelper
import com.icorbal.melisearch.data.model.SearchModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun search(query: String): SearchModel? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(SearchApiClient::class.java).search(query)
            response.body()
        }
    }

}