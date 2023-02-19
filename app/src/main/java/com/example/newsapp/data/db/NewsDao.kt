package com.example.newsapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.model.NewsItem

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNewsData(news: List<NewsItem>)

    @Query("SELECT * FROM news_list_table")
    suspend fun getNewsData(): List<NewsItem>

    @Query("DELETE FROM news_list_table")
    suspend fun deleteAllNewsData()
}
