package com.naggaro.newsapp.news.ui

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
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsListTest
{
    @Mock
    private lateinit var getMostViewedNews: GetMostViewedNews

    @Test
    fun clickNewsListItem_shouldNavigateToDetail()
    {
        // Create a TestNavHostController
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        navController.setGraph(R.navigation.news_navigation)

        val newsListScenario = launchFragmentInContainer<NewsList>()
        newsListScenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }

        onView(ViewMatchers.withId(R.id.newsRv))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()));

        assertThat(navController.currentDestination?.id).isEqualTo(R.id.newsDetail)

    }
}