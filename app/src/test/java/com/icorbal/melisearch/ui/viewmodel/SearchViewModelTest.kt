package com.icorbal.melisearch.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.icorbal.melisearch.data.model.SearchModel
import com.icorbal.melisearch.domain.SearchForWordsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @RelaxedMockK
    private lateinit var searchForWordsUseCase : SearchForWordsUseCase

    private lateinit var searchViewModel : SearchViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        searchViewModel = SearchViewModel(searchForWordsUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when search then return any SearchModel` () = runTest {

        val query = "hola"
        val searchModel = mockk<SearchModel>(relaxed = true)
        coEvery {
            searchForWordsUseCase.searchForWords(query)
        } returns searchModel


        searchViewModel.search(query)

        assertEquals(searchModel, searchViewModel.searchModel.value)
        searchViewModel.error.value?.let { assertFalse(it) }
        searchViewModel.isLoading.value?.let { assertFalse(it) }
    }

    @Test
    fun `when search then return CancellationException` () = runTest {

        val query = "hola"
        val searchModel = mockk<SearchModel>(relaxed = true)
        coEvery {
            searchForWordsUseCase.searchForWords(query)
        }.throws(CancellationException())


        searchViewModel.search(query)

        searchViewModel.error.value?.let { assertTrue(it) }
        searchViewModel.isLoading.value?.let { assertFalse(it) }
    }

    @Test
    fun `when search then return Exception` () = runTest {

        val query = "hola"
        val searchModel = mockk<SearchModel>(relaxed = true)
        coEvery {
            searchForWordsUseCase.searchForWords(query)
        }.throws(Exception())


        searchViewModel.search(query)

        searchViewModel.error.value?.let { assertTrue(it) }
        searchViewModel.isLoading.value?.let { assertFalse(it) }
    }

    @Test
    fun `when search then return null` () = runTest {

        val query = "hola"
        coEvery {
            searchForWordsUseCase.searchForWords(query)
        } returns null


        searchViewModel.search(query)

        assertNull(searchViewModel.searchModel.value)
    }
}