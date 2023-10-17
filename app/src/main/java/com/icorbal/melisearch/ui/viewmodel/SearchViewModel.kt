package com.icorbal.melisearch.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icorbal.melisearch.data.model.SearchModel
import com.icorbal.melisearch.domain.SearchForWordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchForWordsUseCase : SearchForWordsUseCase
) : ViewModel() {

    val searchModel = MutableLiveData<SearchModel>()
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()

    fun search(query: String) {
        viewModelScope.launch {
            isLoading.postValue(true )
            try {
                val result = searchForWordsUseCase.searchForWords(query)
                result.let {
                    searchModel.postValue(it)
                }
            } catch (cancellationException: CancellationException) {
                error.postValue(true)
            } catch (e: Exception) {
                error.postValue(true)
            }

            isLoading.postValue(false)
        }
    }
}