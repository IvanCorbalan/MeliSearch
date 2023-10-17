package com.icorbal.melisearch.domain

import com.icorbal.melisearch.data.SearchRepository
import com.icorbal.melisearch.data.model.SearchModel
import javax.inject.Inject

class SearchForWordsUseCase @Inject constructor(
    private val repository : SearchRepository
) {

    suspend fun searchForWords(query: String): SearchModel? = repository.getSearch(query)
}