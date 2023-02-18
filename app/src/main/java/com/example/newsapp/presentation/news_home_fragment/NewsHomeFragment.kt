package com.example.newsapp.presentation.news_home_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.data.api.NewsService
import com.example.newsapp.data.model.NewsItem
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.data.repository.datasourceimpl.NewsRemoteDataSourceImpl
import com.example.newsapp.databinding.FragmentNewsHomeBinding
import com.example.newsapp.domain.usecase.NewsUseCase
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsHomeFragment : Fragment() {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var binding: FragmentNewsHomeBinding
    private lateinit var adapter: NewsAdapter
    private lateinit var newsList: List<NewsItem>

    fun provideRetrofitInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.cbc.ca/aggregate_api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
 val newsService = provideRetrofitInstance().create(NewsService::class.java)
    val newsRemoteDataSource = NewsRemoteDataSourceImpl(newsService)
    val newsRepository = NewsRepositoryImpl(newsRemoteDataSource)
    val newsUseCase = NewsUseCase(newsRepository)
    val viewModelFactory = NewsViewModelFactory(newsUseCase)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            newsViewModel = ViewModelProvider(it,viewModelFactory)[NewsViewModel::class.java]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel.fetchNewsData()
        initRecycleView()
        newsViewModel.newsLiveData.observe(viewLifecycleOwner) {
           newsList = it
            displayData(it)
        }
    }

    private fun initRecycleView(){
        binding.newsHomeRecyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = NewsAdapter{ position ->
            navigateToSection(newsList[position].type)
              }
        binding.newsHomeRecyclerView.adapter = adapter
    }

    private fun navigateToSection(section: String){
        val action = NewsHomeFragmentDirections.actionNewsHomeFragmentToNewsSectionFragment(section)
        findNavController().navigate(action)
    }

    private fun displayData(news: List<NewsItem>){
        adapter.setList(news)
        adapter.notifyDataSetChanged()
        binding.newsHomeProgressBar.visibility = View.GONE
    }

}