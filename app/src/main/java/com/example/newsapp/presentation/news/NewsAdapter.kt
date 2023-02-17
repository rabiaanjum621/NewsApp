package com.example.newsapp.presentation.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.model.NewsItem
import com.example.newsapp.databinding.LayoutCardItemBinding
import com.example.newsapp.presentation.setImageUrl
import java.util.ArrayList

class NewsAdapter(
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val newsList = ArrayList<NewsItem>()

    fun setList(vehicle: List<NewsItem>) {
        newsList.clear()
        newsList.addAll(vehicle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : LayoutCardItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.layout_card_item, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    inner class NewsViewHolder(private val binding: LayoutCardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(newsList: NewsItem){
                binding.apply {
                    ivNewsImage.setImageUrl(newsList.images.square_140)
                    tvNewsTitle.text = newsList.title
                    tvNewsType.text = newsList.type
                    tvNewsPublishDate.text = newsList.publishedAt.toString()
                }
            }

    }

}