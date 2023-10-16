package com.icorbal.melisearch.data.network

import com.icorbal.melisearch.data.model.SearchModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiClient {

    @GET("sites/MLA/search?")
    suspend fun search(@Query("q") query: String): Response<SearchModel>
}