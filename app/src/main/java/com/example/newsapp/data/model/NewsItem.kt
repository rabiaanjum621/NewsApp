package com.example.newsapp.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nullable

@Entity(tableName = "news_list_table")
data class NewsItem(
    @PrimaryKey
    val id: Int,
    @Embedded
    val images: Images,
    val publishedAt: Long,
    val title: String,
    val type: String
)