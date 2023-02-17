package com.example.newsapp.presentation.di

import com.example.newsapp.data.api.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class InternetModule {
    val BASE_URL = "https://www.cbc.ca/aggregate_api/v1/"

    @Provides
    fun provideRetrofitInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideNewsService() : NewsService{
        return provideRetrofitInstance().create(NewsService::class.java)
    }
}