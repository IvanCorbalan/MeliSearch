package com.icorbal.melisearch.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icorbal.melisearch.data.model.SearchModel
import com.icorbal.melisearch.domain.SearchForWordsUseCase
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    val searchModel = MutableLiveData<SearchModel>()
    private val isLoading = MutableLiveData<Boolean>()

    var searchForWordsUseCase = SearchForWordsUseCase()

    fun onCreate(query: String) {
        viewModelScope.launch {
            isLoading.postValue(true )
            val result = searchForWordsUseCase.searchForWords(query)

            result.let {
                searchModel.postValue(it)
            }
            isLoading.postValue(false)
        }
    }
}