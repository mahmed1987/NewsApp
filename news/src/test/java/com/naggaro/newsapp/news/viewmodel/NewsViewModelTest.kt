package com.naggaro.newsapp.news.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.naggaro.common.error.Failure
import com.naggaro.common.newsapp.base.BaseUseCase
import com.naggaro.common.newsapp.functional.Either
import com.naggaro.dtos.news.NewsView
import com.naggaro.dtos.serverObjects.News
import com.naggaro.newsapp.business.news.usecases.GetMostViewedNews
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.given
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsViewModelTest
{

    private lateinit var newsViewModel: NewsViewModel

    @Mock private lateinit var getMostViewedNews: GetMostViewedNews
    @Mock private lateinit var application: Application
    @Before
    fun setUp() {
        newsViewModel = NewsViewModel(application, getMostViewedNews)
    }


    @Test fun `loading movie details should update live data`() {
        val news = listOf(NewsView.dummyNews())
        given { runBlocking { getMostViewedNews.run(eq(any())) } }.willReturn(Either.Right(news))

//        runBlocking { newsViewModel.fetchMostViewedNews(0) }
    }
}