package com.example.newsapp.data.api

import com.example.newsapp.di.NewsApp
import javax.inject.Inject

class TestRepo @Inject constructor(private val newsService: NewsService) {

    suspend fun getAll() = newsService.getHeadlines()
}