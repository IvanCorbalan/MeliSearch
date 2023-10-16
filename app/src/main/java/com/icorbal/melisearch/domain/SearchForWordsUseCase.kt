package com.icorbal.melisearch.domain

import com.icorbal.melisearch.data.SearchRepository
import com.icorbal.melisearch.data.model.SearchModel

class SearchForWordsUseCase {

    private val repository = SearchRepository()

    suspend fun searchForWords(query: String): SearchModel? = repository.getSearch(query)
}