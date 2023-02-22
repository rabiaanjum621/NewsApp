package com.example.newsapp.presentation.ui


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.data.model.Images
import com.example.newsapp.data.model.NewsItem
import com.example.newsapp.data.repository.CustomResponse
import com.example.newsapp.domain.usecase.NewsUseCase
import com.example.newsapp.getOrAwaitValue
import junit.framework.Assert.assertNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*


import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class NewsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private var _testLiveData = MutableLiveData<CustomResponse<List<NewsItem>?>>()
    private val testLiveData
        get() = _testLiveData
    private val query = "news"

    @Mock
    lateinit var newsUseCase: NewsUseCase


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun getNewsLiveData() {
        _testLiveData = MutableLiveData<CustomResponse<List<NewsItem>?>>()
        _testLiveData.value = null
        assertNull(testLiveData.getOrAwaitValue())
    }

    @Test
    fun testEmptyResponse() = runTest {
        Mockito.`when`(newsUseCase.execute(query)).thenReturn(CustomResponse.Success(emptyList()))

        val response = NewsViewModel(newsUseCase)
        response.fetchNewsData(query)
        testDispatcher.scheduler.advanceUntilIdle()
        val result = response.newsLiveData.getOrAwaitValue()
        Assert.assertEquals(0, result.data?.size)
    }

    @Test
    fun testResponseWithData() = runTest {
        val newsItemList = mutableListOf<NewsItem>(
            NewsItem(12, Images(""),12L, "",""),
            NewsItem(12, Images(""),12L, "",""),
            NewsItem(12, Images(""),12L, "","")
        )
        Mockito.`when`(newsUseCase.execute(query)).thenReturn(CustomResponse.Success(newsItemList))

        val response = NewsViewModel(newsUseCase)
        response.fetchNewsData(query)
        testDispatcher.scheduler.advanceUntilIdle()
        val result: CustomResponse<List<NewsItem>?> = response.newsLiveData.getOrAwaitValue(2)
        Assert.assertEquals(3, result.data?.size)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}