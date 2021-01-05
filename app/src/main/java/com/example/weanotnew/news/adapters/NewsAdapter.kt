package com.example.weanotnew.news.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weanotnew.R
import com.example.weanotnew.news.model.Article

class NewsAdapter(var context: Context): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private lateinit var articles: List<Article>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]
        holder.tvArticleTitle.text = article.title
        holder.tvArticleDescription.text = article.description
        holder.tvArticleSource.text = article.source?.name
        val url = article.urlToImage
        Glide.with(context).load(url).into(holder.ivArticlePicture)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvArticleTitle: TextView = itemView.findViewById(R.id.textViewNewsTitle)
        val tvArticleDescription: TextView = itemView.findViewById(R.id.textViewNewsDescription)
        val tvArticleSource: TextView = itemView.findViewById(R.id.textViewNewsSource)
        val ivArticlePicture: ImageView = itemView.findViewById(R.id.imageViewPicture)
    }

    fun setArticles(articles: List<Article>) {
        this.articles = articles
        notifyDataSetChanged()
    }
}