package com.example.newsapp

import com.example.newsapp.data.api.NewsService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsAPITest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var newsService: NewsService

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
    fun testGetNews() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        val response = newsService.getNewsList()
        mockWebServer.takeRequest()

        Assert.assertEquals(true, response.body().isNullOrEmpty())
    }

    @Test
    fun testGetNewsReturnError() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockResponse.setBody("Error while fetching data from API")
        mockWebServer.enqueue(mockResponse)

        val response = newsService.getNewsList()
        mockWebServer.takeRequest()

        Assert.assertEquals(false, response.isSuccessful)
        Assert.assertEquals(404, response.code())

    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}