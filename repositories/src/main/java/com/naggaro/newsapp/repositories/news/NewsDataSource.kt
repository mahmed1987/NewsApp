package com.naggaro.newsapp.repositories.news

import com.naggaro.common.error.Failure
import com.naggaro.common.newsapp.functional.Either
import com.naggaro.dtos.news.NewsView

interface NewsDataSource {
    fun news(sections:String,period:Int) : Either<Failure, List<NewsView>>
}