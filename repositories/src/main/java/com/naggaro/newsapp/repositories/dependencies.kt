package com.naggaro.newsapp.repositories

import com.naggaro.newsapp.repositories.news.NewsRepository
import com.naggaro.newsapp.repositories.news.NewsWebService
import org.koin.dsl.module
import retrofit2.Retrofit

val repoDependencies = module {

    //retrofit
    single { get<Retrofit>().create(NewsWebService::class.java) }

    //repositories
    single { NewsRepository(get()) }


}

