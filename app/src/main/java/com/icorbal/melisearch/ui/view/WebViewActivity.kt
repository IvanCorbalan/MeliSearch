package com.icorbal.melisearch.ui.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.icorbal.melisearch.R
import com.icorbal.melisearch.databinding.ActivityMainBinding
import com.icorbal.melisearch.databinding.ActivityWebViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewActivity : AppCompatActivity() {

    companion object {
        private const val PERMA_LINK_KEY = "permalink"

        fun getIntent(context: Context, permalink: String) =
            Intent(context, WebViewActivity::class.java).apply {
                putExtra(PERMA_LINK_KEY, permalink)
            }
    }

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            it.getString(PERMA_LINK_KEY)?.let { permalink -> binding.webView.loadUrl(permalink) }

        }
    }
}