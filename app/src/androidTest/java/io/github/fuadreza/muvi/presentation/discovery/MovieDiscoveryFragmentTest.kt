package io.github.fuadreza.muvi.presentation.discovery

import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentFactory
import androidx.test.filters.LargeTest
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.github.fuadreza.muvi.launchFragmentInHiltContainer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit

@HiltAndroidTest
@LargeTest
class MovieDiscoveryFragmentTest : TestCase() {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun launchTest() {
        run {
            step("Open Screen") {
                val fragmentClass = MovieDiscoveryFragment::class.java
                val fragment = FragmentFactory().instantiate(
                    fragmentClass.classLoader!!,
                    fragmentClass.name
                ) as MovieDiscoveryFragment
                launchFragmentInHiltContainer<MovieDiscoveryFragment>(
                    fragmentArg = fragment,
                    fragmentArgs = bundleOf(
                        MovieDiscoveryFragment.ARG_KEY_MOVIE_GENRE_ID to "28",
                        MovieDiscoveryFragment.ARG_KEY_MOVIE_GENRE_NAME to "Action",
                    )
                )
            }
            step("Sleep until close manually") {
                Thread.sleep(TimeUnit.DAYS.toMillis(1))
            }
        }
    }
}