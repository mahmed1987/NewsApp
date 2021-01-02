package com.naggaro.newsapp.business

import com.naggaro.newsapp.business.news.usecases.GetMostViewedNews
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCasesDependencies= module {
    //waiter use cases
    single { GetMostViewedNews(get(),get()) }

}