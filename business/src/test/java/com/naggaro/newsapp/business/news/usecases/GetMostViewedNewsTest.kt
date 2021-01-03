package com.naggaro.newsapp.business.news.usecases

import com.naggaro.common.functional.Either
import com.naggaro.common.newsapp.base.BaseUseCase
import com.naggaro.dtos.news.NewsView
import com.naggaro.newsapp.repositories.news.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import com.google.common.truth.Truth.assertThat

@RunWith(MockitoJUnitRunner::class)
class GetMostViewedNewsTest {
    @Mock
    private lateinit var newsRepository: NewsRepository

    @Mock
    private lateinit var params: GetMostViewedNews.Params

    @Test
    fun `usecase returns data when given by repo`() = runBlocking {
        val list: List<NewsView> = mock()
        val result: Either.Right<List<NewsView>> = mock()
        Mockito.`when`(result.b).thenReturn(list)

        Mockito.`when`(newsRepository.news(ArgumentMatchers.anyString(),ArgumentMatchers.anyInt())).thenReturn(result)
        val getNews = GetMostViewedNews(CoroutineScope(Dispatchers.IO + Job()), newsRepository)
        Mockito.`when`(getNews.run(params)).thenReturn(result)

        getNews.invoke(params){
            assertThat(it).isEqualTo(result)
            assertThat(result.b).isNotNull()
        }

    }

    @Test
    fun `usecase returns error when reported by repo`() = runBlocking {
        val list: List<NewsView> = mock()
        val result: Either.Right<List<NewsView>> = mock()
        Mockito.`when`(result.b).thenReturn(list)

        Mockito.`when`(newsRepository.news(ArgumentMatchers.anyString(),ArgumentMatchers.anyInt())).thenReturn(result)
        val getNews = GetMostViewedNews(CoroutineScope(Dispatchers.IO + Job()), newsRepository)
        Mockito.`when`(getNews.run(params)).thenReturn(result)

        getNews.invoke(params){
            assertThat(it).isEqualTo(result)
            assertThat(result.b).isNotNull()
        }

    }
}
inline fun <reified T : Any> mock() = Mockito.mock(T::class.java)