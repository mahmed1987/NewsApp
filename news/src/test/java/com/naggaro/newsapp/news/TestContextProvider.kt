package com.naggaro.newsapp.news

import com.naggaro.common.newsapp.base.CoroutineContextProvider
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlin.coroutines.CoroutineContext

class TestContextProvider: CoroutineContextProvider() {
    val testCoroutineDispatcher = TestCoroutineDispatcher()
    override val Main: CoroutineContext = testCoroutineDispatcher
    override val IO: CoroutineContext = testCoroutineDispatcher
}
