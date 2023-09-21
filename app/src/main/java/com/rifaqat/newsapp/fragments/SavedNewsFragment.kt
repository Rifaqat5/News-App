package com.rifaqat.newsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rifaqat.newsapp.NewsActivity
import com.rifaqat.newsapp.R
import com.rifaqat.newsapp.adapters.NewsAdapter
import com.rifaqat.newsapp.databinding.FragmentSavedNewsBinding
import com.rifaqat.newsapp.viewModel.NewsViewModel

class SavedNewsFragment:Fragment(R.layout.fragment_saved_news) {

    private lateinit var viewModel: NewsViewModel

    private lateinit var newsAdapter: NewsAdapter

    // Declare a private binding variable
    private lateinit var binding: FragmentSavedNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialize the binding object
        binding = FragmentSavedNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as NewsActivity).viewModel


        setUpRecyclerView()

        //        Open article in web View when some article is clicked.
        newsAdapter.setOnItemClickListener {article ->
            val bundle=Bundle().apply {
                putSerializable("article",article)
            }
            findNavController().navigate(
                R.id.action_breakingNewsFragment_to_articleFragment,
                bundle
            )
        }

//        Do the work of delete any article by swapping from the saved articles.
        val itemTouchHelperCallback=object :ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position=viewHolder.adapterPosition
                val article=newsAdapter.differ.currentList[position]
                viewModel.deleteArticle(article)

//                Now we are going to undo delete article
                Snackbar.make(view,"Article Deleted...",Snackbar.LENGTH_LONG).apply {
                    setAction("UNDO"){
                        viewModel.saveArticle(article)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvSavedNews)
        }

//        show saved news in the form of Recycler View
        viewModel.getSavedNews().observe(viewLifecycleOwner, Observer { article->
            newsAdapter.differ.submitList(article)
        })
    }

    private fun setUpRecyclerView() {
        newsAdapter = NewsAdapter()
        // Now you can use `binding` to access views from your layout
        binding.rvSavedNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}