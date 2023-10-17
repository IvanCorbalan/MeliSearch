package com.icorbal.melisearch.data

import com.icorbal.melisearch.data.model.SearchModel
import com.icorbal.melisearch.data.network.SearchService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SearchRepositoryTest {

    @RelaxedMockK
    private lateinit var api: SearchService

    private lateinit var searchRepository: SearchRepository

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        searchRepository = SearchRepository(api)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when SearchRepository search then return SearchModel` () = runTest {
        val query = "hola"
        val searchModel = mockk<SearchModel>(relaxed = true)
        coEvery {
            api.search(query)
        } returns searchModel

        val searchResult = searchRepository.getSearch(query)

        assertEquals(searchModel, searchResult)
    }

    @Test
    fun `when SearchRepository search then return null` () = runTest {
        val query = "hola"
        val searchModel = null
        coEvery {
            api.search(query)
        } returns searchModel

        val searchResult = searchRepository.getSearch(query)

        assertNull(searchResult)
    }

}