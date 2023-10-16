package com.icorbal.melisearch.ui.view.adapter

import android.app.appsearch.SearchResult
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.icorbal.melisearch.R
import com.icorbal.melisearch.data.model.Result
import com.icorbal.melisearch.ui.view.holder.SearchResultViewHolder

class SearchResultAdapter(
    private val searchResults: List<Result>,
    private val onClickListener: (Result) -> Unit
) : RecyclerView.Adapter<SearchResultViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SearchResultViewHolder(layoutInflater.inflate(R.layout.item_search_result, parent, false))
    }

    override fun getItemCount(): Int = searchResults.size

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(searchResults[position], onClickListener)
    }
}