package com.icorbal.melisearch.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.icorbal.melisearch.data.model.Result
import com.icorbal.melisearch.databinding.ActivityMainBinding
import com.icorbal.melisearch.ui.view.adapter.SearchResultAdapter
import com.icorbal.melisearch.ui.viewmodel.SearchViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        binding.search.doOnTextChanged { text, start, before, count ->
            searchViewModel.onCreate(text.toString())
        }

        searchViewModel.searchModel.observe(this, Observer {
            binding.searchResults.adapter = SearchResultAdapter(it.results) { searchResult ->
                onItemSelected(searchResult)
            }
        })
        searchViewModel.isLoading.observe(this, Observer { isLoading ->
            binding.progressBar.isVisible = isLoading
            binding.searchResults.isVisible = !isLoading
        })


    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        val decorator = DividerItemDecoration(this, linearLayoutManager.orientation)
        val recyclerView = binding.searchResults
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.addItemDecoration(decorator)

    }

    private fun onItemSelected(result: Result) {
        Toast.makeText(this, result.title, Toast.LENGTH_SHORT).show()
    }
}