package com.example.newsapp.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nullable

@Entity(tableName = "news_list_table")
data class NewsItem(

    @PrimaryKey
    val id: Int,
    val active: Boolean,
    val description: String,
    val draft: Boolean,
    @Nullable
    val embedTypes: String?,
    @Embedded
    val images: Images,
    val language: String,
    val publishedAt: Long,
    val readablePublishedAt: String,
    val readableUpdatedAt: String,
    val source: String,
    val sourceId: String,
    val title: String,
    val type: String,
    val updatedAt: Long,
    val version: String
)