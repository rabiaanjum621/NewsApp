package com.example.newsapp.presentation.di

import android.content.Context
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
   abstract fun bindContext(@ApplicationContext context: Context): Context

    @Binds
    abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl) : NewsRepository
}