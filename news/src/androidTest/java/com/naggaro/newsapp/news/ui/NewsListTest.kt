package com.naggaro.newsapp.news.ui

import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.naggaro.newsapp.business.news.usecases.GetMostViewedNews
import com.naggaro.newsapp.news.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import com.google.common.truth.Truth.assertThat
import com.naggaro.dtos.news.NewsView
import com.naggaro.newsapp.news.newsDependencies
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.test.mock.declareMock

@RunWith(AndroidJUnit4::class)
class NewsListTest
{
    @Before
    fun before() {
        startKoin {
            modules(listOf(
                newsDependencies
            ))
        }
    }

    @Test
    fun clickNewsListItem_shouldNavigateToDetail()
    {
        // Create a TestNavHostController
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        navController.setGraph(R.navigation.news_navigation)
        val newsListScenario = launchFragmentInContainer <FakeNewsList>(themeResId = R.style.Theme_NewsApp)
        newsListScenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
            it.populateData(listOf(NewsView.dummyNews()))
        }

        onView(ViewMatchers.withId(R.id.newsRv))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()));

        assertThat(navController.currentDestination?.id).isEqualTo(R.id.newsDetail)

    }
}