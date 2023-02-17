package com.example.newsapp.presentation

import android.widget.ImageView
import com.bumptech.glide.Glide

 fun ImageView.setImageUrl(url: String) {
        Glide.with(this)
            .load(url)
            .into(this)
    }