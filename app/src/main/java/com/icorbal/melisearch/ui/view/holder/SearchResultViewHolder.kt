package com.icorbal.melisearch.ui.view.holder

import android.app.appsearch.SearchResult
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.icorbal.melisearch.data.model.Result
import com.icorbal.melisearch.databinding.ItemSearchResultBinding

class SearchResultViewHolder(view: View) : ViewHolder(view) {

    val binding = ItemSearchResultBinding.bind(view)

    fun bind(result: Result, onClickListener: (Result) -> Unit) {
        binding.title.text = result.title

        val image = binding.image
        Glide.with(image.context).load(result.thumbnail).into(image)

        itemView.setOnClickListener {
            onClickListener(result)
        }
    }
}