package com.naggaro.newsapp.news.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.naggaro.common.newsapp.functional.Either
import com.naggaro.dtos.news.NewsView
import com.naggaro.newsapp.business.news.usecases.GetMostViewedNews
import com.nhaarman.mockito_kotlin.given
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.google.common.truth.Truth.assertThat
import com.naggaro.newsapp.news.Subject
import com.naggaro.newsapp.news.TestContextProvider
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.mockito.Mockito

@RunWith(MockitoJUnitRunner::class)
class NewsViewModelTest {

    private lateinit var newsViewModel: NewsViewModel

    @get:Rule
    val instantTestExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockContext: Application
    @Mock
    private lateinit var getMostViewedNews: GetMostViewedNews
    @Mock
    private lateinit var params: GetMostViewedNews.Params

    private val testScope = TestCoroutineScope()
    private lateinit var subject: Subject

    @Before
    fun setUp() {
        newsViewModel = NewsViewModel(mockContext, getMostViewedNews)
        subject = Subject(testScope)
    }


    @Test
    fun testFoo() = testScope.runBlockingTest {
        // TestCoroutineScope.runBlockingTest uses the Dispatcher and exception handler provided by `testScope`
        subject.foo()
    }


    @Test
    fun `loading news details should update live data`()= runBlockingTest {
        val testContextProvider = TestContextProvider()
        getMostViewedNews.ioScope=testScope
        val news = listOf(NewsView.dummyNews())
        whenever(getMostViewedNews.run(params)).thenReturn(Either.Right(news))
        newsViewModel.fetchMostViewedNews("all-sections", 7)
        testContextProvider.testCoroutineDispatcher.advanceUntilIdle()

    }


}