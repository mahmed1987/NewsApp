package com.naggaro.newsapp

import androidx.multidex.MultiDexApplication
import com.naggaro.newsapp.business.useCasesDependencies
import com.naggaro.newsapp.network.networkDependencies
import com.naggaro.newsapp.news.newsDependencies
import com.naggaro.newsapp.repositories.repoDependencies
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class Application : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(
                listOf(
                    repoDependencies,
                    networkDependencies,
                    platformDependencies,
                    newsDependencies,
                    useCasesDependencies
                )
            )
        }
    }

}