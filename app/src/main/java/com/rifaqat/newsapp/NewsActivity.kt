package com.rifaqat.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.rifaqat.newsapp.databinding.ActivityNewsBinding
import com.rifaqat.newsapp.db.ArticleDatabase
import com.rifaqat.newsapp.repository.NewsRepository
import com.rifaqat.newsapp.viewModel.NewsViewModel
import com.rifaqat.newsapp.viewModel.NewsViewModelProviderFactory

class NewsActivity : AppCompatActivity() {

    lateinit var viewModel:NewsViewModel

    private val binding by lazy {
        ActivityNewsBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Setting up viewModel and other requirement needed for it.
        val newsRepository=NewsRepository(ArticleDatabase(this))
        val newsViewModelProviderFactory=NewsViewModelProviderFactory(newsRepository,application)
        viewModel= ViewModelProvider(this,newsViewModelProviderFactory)[NewsViewModel::class.java]


//        set bottom_nav_view with news_nav_graph
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment)
        if (navHostFragment != null) {
            binding.bottomNavigationView.setupWithNavController(navHostFragment.findNavController())
        }
    }
}