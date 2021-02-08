package com.nagarro.newsapp.features.viewmodels

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.naggaro.common.newsapp.functional.Either
import com.naggaro.dtos.news.NewsView
import com.naggaro.newsapp.business.news.usecases.GetMostViewedNews
import com.naggaro.newsapp.news.Subject
import com.naggaro.newsapp.news.viewmodel.NewsViewModel
import com.naggaro.newsapp.repositories.news.NewsRepository
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import org.junit.Before
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class NewsViewModelTest {

    private lateinit var newsViewModel: NewsViewModel
    @get:Rule
    val instantTestExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockContext: Application
    private lateinit var getMostViewedNews: GetMostViewedNews
    private lateinit var params: GetMostViewedNews.Params
    @Mock
    private lateinit var newsRepository: NewsRepository

    private lateinit var subject: Subject
    val testDispatcher = TestCoroutineDispatcher()
    val testScope = TestCoroutineScope()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        params = GetMostViewedNews.Params("all-sections", 7)
        getMostViewedNews = GetMostViewedNews(testScope,newsRepository)
        newsViewModel = NewsViewModel(mockContext, getMostViewedNews)
        subject = Subject(testScope)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }


    @Test
    fun testFoo() = testScope.runBlockingTest {
        // TestCoroutineScope.runBlockingTest uses the Dispatcher and exception handler provided by `testScope`
        subject.foo()
    }


    @Test
    fun `loading news details should update live data`()= testDispatcher.runBlockingTest {
        val news = listOf(NewsView.dummyNews(),NewsView.dummyNews())
        whenever(getMostViewedNews.run(params)).thenReturn(Either.Right(news))
        newsViewModel.fetchMostViewedNews(params)
        assertThat(newsViewModel.mostViewedNews.value).hasSize(2)
    }


}