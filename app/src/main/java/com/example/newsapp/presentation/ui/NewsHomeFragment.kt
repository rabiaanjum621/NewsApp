package com.example.newsapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.data.model.NewsItem
import com.example.newsapp.data.repository.CustomResponse
import com.example.newsapp.databinding.FragmentNewsHomeBinding
import com.example.newsapp.utils.EspressoIdlingResource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsHomeFragment : Fragment() {

    @Inject
    lateinit var newsViewModelFactory: NewsViewModelFactory
    private val query = "news"

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var binding: FragmentNewsHomeBinding
    private lateinit var adapter: NewsAdapter
    private lateinit var newsList: List<NewsItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            newsViewModel = ViewModelProvider(it, newsViewModelFactory)[NewsViewModel::class.java]
        }
        newsViewModel.fetchNewsData(query)
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
        EspressoIdlingResource.increment()
        initRecycleView()
        observeNewsData()
    }

    private fun initRecycleView() {
        binding.newsHomeRecyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = NewsAdapter { position ->
            navigateToSection(newsList[position].type)
        }
        binding.newsHomeRecyclerView.adapter = adapter
    }

    private fun navigateToSection(section: String) {
        val action = NewsHomeFragmentDirections.actionNewsHomeFragmentToNewsSectionFragment(section)
        findNavController().navigate(action)
    }

    private fun observeNewsData() {
        newsViewModel.newsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is CustomResponse.Loading -> {
                    binding.newsHomeProgressBar.visibility = View.VISIBLE
                }
                is CustomResponse.Success -> {
                    binding.newsHomeProgressBar.visibility = View.GONE
                    if (it.data != null) {
                        newsList = it.data
                        displayData(it.data)
                        EspressoIdlingResource.decrement()
                    }
                }
                is CustomResponse.Error -> {
                    binding.newsHomeProgressBar.visibility = View.GONE
                    Toast.makeText(
                        context,
                        " error: ${it.errorMessage} code ${it.errorCode}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }
    }

    private fun displayData(news: List<NewsItem>) {
        adapter.setList(news)
        binding.newsHomeProgressBar.visibility = View.GONE
    }

}