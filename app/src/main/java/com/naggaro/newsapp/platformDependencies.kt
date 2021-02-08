package com.naggaro.newsapp

import com.google.gson.Gson
import com.naggaro.common.newsapp.base.CoroutineContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.dsl.module

val platformDependencies = module {

    single { CoroutineScope(Dispatchers.IO + Job()) }
//    single<CoroutineContextProvider> { CoroutineContextProvider().IO }
    single { Gson() }

}