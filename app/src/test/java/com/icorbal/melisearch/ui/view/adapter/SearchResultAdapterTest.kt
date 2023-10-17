package com.icorbal.melisearch.ui.view.adapter

import com.icorbal.melisearch.ui.view.holder.SearchResultViewHolder
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SearchResultAdapterTest {

    private lateinit var searchResultAdapter: SearchResultAdapter

    @Before
    fun onBefore() {
        searchResultAdapter = SearchResultAdapter(listOf(mockk(relaxed = true))) { searchResult ->
        }

    }

    @Test
    fun `itemCount` () {
        assertEquals(1, searchResultAdapter.itemCount)
    }

    @Test
    fun `onBindViewHolder` () {
        var searchResultViewHolder = mockk<SearchResultViewHolder>(relaxed = true)
        searchResultAdapter.onBindViewHolder(searchResultViewHolder, 0)

        verify {
            searchResultViewHolder.bind(any(), any())
        }
    }
}