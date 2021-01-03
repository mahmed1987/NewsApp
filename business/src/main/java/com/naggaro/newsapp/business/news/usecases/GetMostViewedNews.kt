package com.naggaro.newsapp.business.news.usecases

import com.naggaro.common.newsapp.base.BaseUseCase
import com.naggaro.dtos.news.NewsView
import com.naggaro.newsapp.repositories.news.NewsRepository
import kotlinx.coroutines.CoroutineScope

class GetMostViewedNews(var ioScope: CoroutineScope, private val newsRepository: NewsRepository) :
    BaseUseCase<List<NewsView>, GetMostViewedNews.Params>(ioScope) {
    override suspend fun run(param: Params) = newsRepository.news(param.sections, param.period)
    data class Params(val sections: String, val period: Int)
}