package com.naggaro.newsapp.business

import com.naggaro.common.newsapp.base.CoroutineContextProvider
import com.naggaro.newsapp.business.news.usecases.GetMostViewedNews
import com.naggaro.newsapp.business.news.usecases.GetMostViewedNews2
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCasesDependencies= module {
    //waiter use cases
    single { GetMostViewedNews(get(),get()) }
    single { GetMostViewedNews2(get()) }

}