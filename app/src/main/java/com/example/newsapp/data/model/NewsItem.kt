package com.example.newsapp.data.model

data class NewsItem(
    val active: Boolean,
    val description: String,
    val draft: Boolean,
    val embedTypes: String,
    val id: Int,
    val images: Images,
    val language: String,
    val publishedAt: Long,
    val readablePublishedAt: String,
    val readableUpdatedAt: String,
    val source: String,
    val sourceId: String,
    val title: String,
    val type: String,
    val typeAttributes: TypeAttributes,
    val updatedAt: Long,
    val version: String
)