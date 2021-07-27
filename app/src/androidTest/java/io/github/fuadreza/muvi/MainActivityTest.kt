package io.github.fuadreza.muvi

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.github.fuadreza.muvi.presentation.MainActivity
import io.github.fuadreza.muvi.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainActivityTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun launchTest() {
        onView(withId(R.id.rv_genres)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_genres)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.rv_movies_discovery)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_movies_discovery)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.text_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.text_overview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.text_release_date)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.text_vote_avarage)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.youtube_player)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}