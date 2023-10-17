package com.icorbal.melisearch.domain

import com.icorbal.melisearch.data.SearchRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SearchForWordsUseCaseTest {

    @RelaxedMockK
    private lateinit var repository : SearchRepository

    private lateinit var searchForWordsUseCase: SearchForWordsUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        searchForWordsUseCase = SearchForWordsUseCase(repository)
    }

    @Test
    fun `when searchForWords then return any SearchModel` () = runBlocking {

        // Given
        val query = "hola"

        coEvery {
            repository.getSearch(query)
        } returns mockk(relaxed = true)


        // When
        val searchForWords = searchForWordsUseCase.searchForWords(query)

        // Then
        coEvery() {
            repository.getSearch(query)
        }

        Assert.assertNotNull(searchForWords)
    }

    @Test
    fun `when searchForWords then return any null` () = runBlocking {

        // Given
        val query = "hola"

        coEvery {
            repository.getSearch(query)
        } returns null


        // When
        val searchForWords = searchForWordsUseCase.searchForWords(query)

        // Then
        coEvery() {
            repository.getSearch(query)
        }

        Assert.assertNull(searchForWords)
    }
}