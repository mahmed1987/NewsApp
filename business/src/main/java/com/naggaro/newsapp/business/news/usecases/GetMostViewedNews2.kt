package com.naggaro.newsapp.business.news.usecases

import com.naggaro.common.newsapp.base.BaseUseCase
import com.naggaro.common.newsapp.base.CoroutineContextProvider
import com.naggaro.dtos.news.NewsView
import com.naggaro.newsapp.repositories.news.NewsRepository
import kotlinx.coroutines.CoroutineScope


class GetMostViewedNews2(private val newsRepository: NewsRepository) {
    operator fun invoke(param: Params) =newsRepository.news(param.sections, param.period)
    data class Params(val sections: String, val period: Int)
}