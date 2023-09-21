package com.rifaqat.newsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.rifaqat.newsapp.NewsActivity
import com.rifaqat.newsapp.R
import com.rifaqat.newsapp.databinding.FragmentArticleBinding
import com.rifaqat.newsapp.databinding.FragmentSavedNewsBinding
import com.rifaqat.newsapp.viewModel.NewsViewModel

class ArticleFragment:Fragment(R.layout.fragment_article) {

    private lateinit var viewModel:NewsViewModel

    val args:ArticleFragmentArgs by navArgs()

    // Declare a private binding variable
    private lateinit var binding:FragmentArticleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialize the binding object
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as NewsActivity).viewModel

        val article=args.article
        binding.webView.apply {
            webViewClient= WebViewClient()
            article.url?.let { loadUrl(it) }
        }

//        saved article by clicking on fab button
        binding.fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view,"Article Saved Successfully",Snackbar.LENGTH_SHORT).show()
        }


    }
}