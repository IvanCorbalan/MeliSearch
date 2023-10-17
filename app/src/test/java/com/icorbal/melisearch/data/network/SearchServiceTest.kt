package com.icorbal.melisearch.data.network

import com.icorbal.melisearch.data.model.SearchModel
import com.icorbal.melisearch.ui.viewmodel.SearchViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class SearchServiceTest {

    @RelaxedMockK
    private lateinit var api: SearchApiClient

    private lateinit var searchService: SearchService

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        searchService = SearchService(api)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when SearchService search then return searchModelResponse body` () = runTest {
        val query = "hola"
        val searchModelResponse = mockk<Response<SearchModel>>(relaxed = true)
        val searchModel = mockk<SearchModel>(relaxed = true)

        coEvery {
            api.search(query)
        } returns searchModelResponse

        coEvery {
            searchModelResponse.body()
        } returns searchModel

        val search = searchService.search(query)

        assertEquals(searchModel, search)
    }

    @Test
    fun `when SearchService search then return null` () = runTest {
        val query = "hola"
        val searchModelResponse = mockk<Response<SearchModel>>(relaxed = true)

        coEvery {
            api.search(query)
        } returns searchModelResponse

        coEvery {
            searchModelResponse.body()
        } returns null

        val search = searchService.search(query)

        assertNull(search)
    }
}