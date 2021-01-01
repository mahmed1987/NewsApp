package com.naggaro.newsapp.network

import com.seed.roundrobin.views.system.RequestHeaders
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


val networkDependencies = module {

    single { OkHttpClient.Builder() }
    single { RequestHeaders(null, "en", "application/json") }
    single { RequestInterceptor(get()) }
    single<Retrofit> {
        val httpClient = get<OkHttpClient.Builder>()

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val logger =
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        httpClient.addInterceptor(get<RequestInterceptor>())
        httpClient.addInterceptor(logger)

        val retroBuilder =
            Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
        retroBuilder.client(
            httpClient.readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS).build()
        )
        retroBuilder.build()
    }

}

