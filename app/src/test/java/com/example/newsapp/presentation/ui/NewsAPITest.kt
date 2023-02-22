package com.example.newsapp.presentation.ui

import com.example.newsapp.data.api.NewsService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalCoroutinesApi::class)
class NewsAPITest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var newsService: NewsService
    private val query = "news"

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        newsService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsService::class.java)
    }

    @Test
    fun testGetNewsEmptyBody() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        val response = newsService.getNewsList(query)
        mockWebServer.takeRequest()

        Assert.assertEquals(true, response.body().isNullOrEmpty())
    }

    @Test
    fun testGetNewsReturn404() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockResponse.setBody("Error while fetching data from API")
        mockWebServer.enqueue(mockResponse)

        val response = newsService.getNewsList(query)
        mockWebServer.takeRequest()

        Assert.assertEquals(false, response.isSuccessful)
        Assert.assertEquals(404, response.code())

    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}