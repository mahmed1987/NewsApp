package com.naggaro.newsapp.news

import com.naggaro.newsapp.business.news.usecases.GetMostViewedNews
import junit.framework.TestCase
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class SubjectTest
{
    private val testScope = TestCoroutineScope()
    private lateinit var subject: Subject
    private lateinit var getMostViewedNews: GetMostViewedNews

    @Before
    fun setup() {
        // provide the scope explicitly, in this example using a constructor parameter
        subject = Subject(testScope)
    }

    @After
    fun cleanUp() {
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun testFoo() = testScope.runBlockingTest {
        // TestCoroutineScope.runBlockingTest uses the Dispatcher and exception handler provided by `testScope`
        subject.foo()
    }
}