package com.naggaro.newsapp.news

import com.naggaro.newsapp.news.viewmodel.NewsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val newsDependencies = module {
    viewModel {
        NewsViewModel(
            androidApplication(),
            get()
        )
    }
}