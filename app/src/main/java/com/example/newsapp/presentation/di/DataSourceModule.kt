package com.example.newsapp.presentation.di

import com.example.newsapp.data.repository.datasource.NewsLocalDataSource
import com.example.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.example.newsapp.data.repository.datasourceimpl.NewsLocalDataSourceImpl
import com.example.newsapp.data.repository.datasourceimpl.NewsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindRemoteDataSource(newsRemoteDataSourceImpl: NewsRemoteDataSourceImpl): NewsRemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(newsLocalDataSourceImpl: NewsLocalDataSourceImpl): NewsLocalDataSource

}