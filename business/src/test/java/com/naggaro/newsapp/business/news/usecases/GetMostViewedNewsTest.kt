package com.naggaro.newsapp.business.news.usecases

import com.naggaro.common.newsapp.functional.Either
import com.naggaro.dtos.news.NewsView
import com.naggaro.newsapp.repositories.news.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import com.google.common.truth.Truth.assertThat
import com.naggaro.common.error.Failure

@RunWith(MockitoJUnitRunner::class)
class GetMostViewedNewsTest {
    @Mock
    private lateinit var newsRepository: NewsRepository

    @Mock
    private lateinit var params: GetMostViewedNews.Params

    @Test
    fun `usecase returns data when given by repo`() = runBlocking {
        val actualResult: List<NewsView> = mock()
        val result: Either.Right<List<NewsView>> = mock()
        Mockito.`when`(result.b).thenReturn(actualResult)

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
        val actualResult: Failure = mock()
        val result: Either.Left<Failure> = mock()
        Mockito.`when`(result.a).thenReturn(actualResult)

        Mockito.`when`(newsRepository.news(ArgumentMatchers.anyString(),ArgumentMatchers.anyInt())).thenReturn(result)
        val getNews = GetMostViewedNews(CoroutineScope(Dispatchers.IO + Job()), newsRepository)
        Mockito.`when`(getNews.run(params)).thenReturn(result)

        getNews.invoke(params){
            assertThat(it).isEqualTo(result)
            assertThat(result.a).isNotNull()
        }

    }
}
inline fun <reified T : Any> mock() = Mockito.mock(T::class.java)