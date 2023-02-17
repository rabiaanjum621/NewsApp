package com.example.newsapp.presentation.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.data.model.NewsItem
import com.example.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        newsViewModel.fetchNewsData()
        initRecycleView()
        newsViewModel.newsLiveData?.observe(this, Observer {
          displayData(it)
        })

    }


    private fun initRecycleView(){
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NewsAdapter()
        binding.newsRecyclerView.adapter = adapter
    }

    private fun displayData(news: List<NewsItem>){
        adapter.setList(news)
        adapter.notifyDataSetChanged()
        binding.newsProgressBar.visibility = View.GONE
    }
}