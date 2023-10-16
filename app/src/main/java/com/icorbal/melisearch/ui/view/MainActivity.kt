package com.icorbal.melisearch.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.icorbal.melisearch.R
import com.icorbal.melisearch.data.model.Result
import com.icorbal.melisearch.databinding.ActivityMainBinding
import com.icorbal.melisearch.ui.view.adapter.SearchResultAdapter
import com.icorbal.melisearch.ui.viewmodel.SearchViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val searchViewModel: SearchViewModel by viewModels()

    private var textToSearch = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        binding.search.doOnTextChanged { text, start, before, count ->
            textToSearch = text.toString()
            search()
        }

        searchViewModel.searchModel.observe(this, Observer {
            it?.let {
                binding.searchResults.adapter = SearchResultAdapter(it.results) { searchResult ->
                    onItemSelected(searchResult)
                }
            } ?: run {
                showError()
            }

        })
        searchViewModel.isLoading.observe(this, Observer { isLoading ->
            binding.progressBar.isVisible = isLoading
            binding.searchResults.isVisible = !isLoading
        })

        searchViewModel.error.observe(this,Observer { error ->
            if (error) showError()
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

    private fun search() {
        searchViewModel.search(textToSearch)
    }

    private fun showError() {
        Snackbar.make(binding.root, getString(R.string.search_error), Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat.getColor(this, android.R.color.holo_red_dark))
            .setAction(getString(R.string.retry), View.OnClickListener {
                search()
            })
            .show()
    }
}