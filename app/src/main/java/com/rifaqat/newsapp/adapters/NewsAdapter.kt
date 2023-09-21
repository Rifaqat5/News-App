package com.rifaqat.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rifaqat.newsapp.R
import com.rifaqat.newsapp.models.Article


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_article_preview, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        val itemView = holder.itemView

        // Find your views within the item layout
        val articleImage = itemView.findViewById<ImageView>(R.id.ivArticleImage)
        val articleSource = itemView.findViewById<TextView>(R.id.tvSource)
        val articleTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val articleDescription = itemView.findViewById<TextView>(R.id.tvDescription)
        val articlePublishedAt = itemView.findViewById<TextView>(R.id.tvPublishedAt)

        // Load data into views
        Glide.with(itemView).load(article.urlToImage).into(articleImage)
        articleSource.text = article.source?.name
        articleTitle.text = article.title
        articleDescription.text = article.description
        articlePublishedAt.text = article.publishedAt

        // Set item click listener
        itemView.setOnClickListener {
            onItemClickListener?.invoke(article)
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}


