package com.example.newsapp.presentation.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.newsapp.R
import com.example.newsapp.presentation.common.MainActivity
import com.example.newsapp.utils.EspressoIdlingResource

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewsHomeFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun test_isListFragment_visible() {
        onView(withId(R.id.newsHomeRecyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun selectedNews_inTypeFrag_visible() {

        onView(withId(R.id.newsHomeRecyclerView))
            .perform(actionOnItemAtPosition<NewsAdapter.NewsViewHolder>(0, click()))
//        testDispatcher.scheduler.advanceUntilIdle()
//        // Confirm nav to DetailFragment and display title

//        onView(withId(R.id.tvNewsTitle)).check(matches(withText(newsTestList.title)))
    }
}

@After
fun tearDown() {
    IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
}
