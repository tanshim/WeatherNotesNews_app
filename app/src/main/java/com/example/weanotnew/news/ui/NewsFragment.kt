package com.example.weanotnew.news.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weanotnew.R
import com.example.weanotnew.news.adapters.NewsAdapter
import com.example.weanotnew.news.model.Article
import com.example.weanotnew.weather.ui.WeatherPresenterImpl
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class NewsFragment : Fragment(), NewsContract.NewsView, CoroutineScope {

    private lateinit var recyclerView: RecyclerView
    private lateinit var loadingView: RelativeLayout
    private lateinit var presenter: NewsContract.NewsPresenter
    private var articles: List<Article>? = null

    private lateinit var job: Job
    // context for io thread
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        job = Job()

        recyclerView = view.findViewById(R.id.rvNews)
        loadingView = view.findViewById(R.id.loadingPanelNews)
        recyclerView.visibility = View.GONE

        recyclerView.layoutManager = LinearLayoutManager(view.context)
        val adapter = NewsAdapter(view.context)

        setPresenter(NewsPresenterImpl(this, view.context))

        launch {
            presenter.onViewCreated()
            withContext(Dispatchers.Main) {
                articles?.let { adapter.setArticles(it) }
                recyclerView.adapter = adapter
                loadingView.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }
        return view
    }

    override fun setViews(articles: List<Article>?) {
        this.articles = articles
    }

    override fun setPresenter(presenter: NewsContract.NewsPresenter) {
        this.presenter = presenter
    }

    override fun onDestroy() {
        job.cancel()
        presenter.onDestroy()
        super.onDestroy()
    }
}