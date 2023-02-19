package com.example.newsapp.presentation.news_type_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsSectionBinding
import com.example.newsapp.presentation.news_home_fragment.NewsAdapter
import com.example.newsapp.presentation.news_home_fragment.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class NewsTypeFragment : Fragment() {

    private var newsViewModel: NewsViewModel? = null
    private lateinit var binding: FragmentNewsSectionBinding
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            newsViewModel = ViewModelProvider(it)[NewsViewModel::class.java]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news_section, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel?.fetchNewsData()
        initRecycleView()
    }

    private fun initRecycleView() {
        val args = arguments?.let { NewsTypeFragmentArgs.fromBundle(it) }
        binding.newsSectionRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.customTypeToolbar.ivLogo.visibility = View.GONE
        binding.customTypeToolbar.tvNews.visibility = View.GONE
        binding.customTypeToolbar.tvCBC.text = args?.type?.uppercase(Locale.getDefault())
        val adapter = NewsAdapter(true)
        if (newsViewModel != null) {
            adapter.setList(newsViewModel!!.filterNewsData(args?.type))
        }
        binding.newsSectionRecyclerView.adapter = adapter
        binding.newsSectionProgressBar.visibility = View.GONE
    }
}