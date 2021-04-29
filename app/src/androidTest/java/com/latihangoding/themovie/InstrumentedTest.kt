package com.latihangoding.themovie

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToHolder
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.latihangoding.themovie.ui.movie.MovieAdapter
import com.latihangoding.themovie.ui.tv.TvAdapter
import com.latihangoding.themovie.utils.EspressoIdlingResource
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InstrumentedTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun movieSuccessFetchingDataAndScrollMovieToTheUnholy() {
        onView(
            allOf(
                withId(R.id.rv_main),
                isDisplayed()
            )
        ).perform(
            scrollToHolder(
                withTitle<MovieAdapter.ViewHolder>(
                    "The Unholy",
                    R.id.tv_title
                )
            )
        )

        onView(withText("The Unholy")).check(matches(isDisplayed()))
    }

    @Test
    fun goToMovieUnholyDetailAndUpdateFavorite() {
        onView(
            allOf(
                withId(R.id.rv_main),
                isDisplayed()
            )
        ).perform(
            scrollToHolder(
                withTitle<MovieAdapter.ViewHolder>(
                    "The Unholy",
                    R.id.tv_title
                )
            )
        )

        onView(withText("The Unholy")).perform(click())
        onView(withId(R.id.toolbar_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite)).perform(click())
    }

    @Test
    fun tvSuccessFetchingDataAndScrollTvToTheGoodDoctor() {
        onView(withId(R.id.ib_tv)).perform(click())
        onView(
            allOf(
                withId(R.id.rv_main),
                isDisplayed()
            )
        ).perform(
            scrollToHolder(
                withTitle<TvAdapter.ViewHolder>(
                    "The Good Doctor",
                    R.id.tv_title
                )
            )
        )

        onView(withText("The Good Doctor")).check(matches(isDisplayed()))
    }

    @Test
    fun goToTvTheGoodDoctorDetailAndUpdateFavorite() {
        onView(withId(R.id.ib_tv)).perform(click())
        onView(
            allOf(
                withId(R.id.rv_main),
                isDisplayed()
            )
        ).perform(
            scrollToHolder(
                withTitle<TvAdapter.ViewHolder>(
                    "The Good Doctor",
                    R.id.tv_title
                )
            )
        )


        onView(withText("The Good Doctor")).perform(click())
        onView(withId(R.id.toolbar_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite)).perform(click())
    }

    @Test
    fun goToFavoriteList() {
        onView(withId(R.id.fab_favorite)).perform(click())
        onView(withId(R.id.rv_main)).check(matches(isDisplayed()))
    }

    private inline fun <reified T : RecyclerView.ViewHolder> withTitle(
        title: String,
        id: Int
    ): Matcher<RecyclerView.ViewHolder?> {
        return object : BoundedMatcher<RecyclerView.ViewHolder?, T>(
            T::class.java
        ) {
            override fun matchesSafely(item: T): Boolean {
                return item.itemView.findViewById<TextView>(id).text.toString() == title
            }

            override fun describeTo(description: Description) {
                description.appendText("view holder with title: $title")
            }
        }
    }
}