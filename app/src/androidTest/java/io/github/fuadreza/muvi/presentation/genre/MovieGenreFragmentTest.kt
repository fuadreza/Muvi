package io.github.fuadreza.muvi.presentation.genre

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
class MovieGenreFragmentTest : TestCase() {
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
                val fragmentClass = MovieGenreFragment::class.java
                val fragment = FragmentFactory().instantiate(
                    fragmentClass.classLoader!!,
                    fragmentClass.name
                ) as MovieGenreFragment
                launchFragmentInHiltContainer<MovieGenreFragment>(
                    fragmentArg = fragment
                )
            }
            step("Sleep until close manually") {
                Thread.sleep(TimeUnit.DAYS.toMillis(1))
            }
        }
    }
}