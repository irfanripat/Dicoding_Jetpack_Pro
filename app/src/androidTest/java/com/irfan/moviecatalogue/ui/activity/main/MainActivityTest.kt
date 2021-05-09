package com.irfan.moviecatalogue.ui.activity.main


import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.irfan.moviecatalogue.R
import com.irfan.moviecatalogue.utils.DrawableMatcher.hasDrawable
import com.irfan.moviecatalogue.utils.IdlingResourceRule
import com.irfan.moviecatalogue.utils.RecyclerViewItemMatcher.atPosition
import com.irfan.moviecatalogue.utils.TabLayoutTestUtil.selectTabAtPosition
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var instrumentationContext: Context

    @get:Rule
    var idlingResourceRule = IdlingResourceRule()

    @Before
    fun setUp(){
        ActivityScenario.launch(MainActivity::class.java)
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
    }

    @Test
    fun testMovieRecyclerView() {
        try {
            onView(allOf(withId(R.id.rv_movie), isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8) )
                .check(matches(not(atPosition(8, hasDescendant(withText(""))))))
        } catch (e: NoMatchingViewException) {
            onView(allOf(withId(R.id.no_connection), isDisplayed()))
                .check(matches(isDisplayed()))
        }
    }

    @Test
    fun testTvRecyclerView() {
        onView(withId(R.id.tabLayout))
                .check(matches(isDisplayed()))
                .perform(selectTabAtPosition(1))
        try {
            onView(allOf(withId(R.id.rv_tv), isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8) )
                .check(matches(not(atPosition(8, hasDescendant(withText(""))))))
        } catch (e: NoMatchingViewException) {
            onView(allOf(withId(R.id.no_connection), isDisplayed()))
                .check(matches(isDisplayed()))
        }
    }

    @Test
    fun testIntentToDetailActivityWithMovieData() {

        try {
            onView(allOf(withId(R.id.rv_movie), isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5) )
                .check(matches(not(atPosition(5, hasDescendant(withText(""))))))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))

            try {
                onView(allOf(withId(R.id.tv_title), isDisplayed()))
                    .check(matches(isDisplayed()))
                    .check(matches(not((withText("")))))

                onView(allOf(withId(R.id.tv_release), isDisplayed()))
                    .check(matches(isDisplayed()))
                    .check(matches(not((withText("")))))

                onView(allOf(withId(R.id.tv_overview), isDisplayed()))
                    .check(matches(isDisplayed()))
                    .check(matches(not((withText("")))))

                onView(allOf(withId(R.id.tv_duration), isDisplayed()))
                    .check(matches(isDisplayed()))
                    .check(matches(not((withText("")))))

                onView(allOf(withId(R.id.image_poster), isDisplayed()))
                    .check(matches(isDisplayed()))
                    .check(matches(hasDrawable()))

                onView(allOf(withId(R.id.image_bg), isDisplayed()))
                    .check(matches(isDisplayed()))
                    .check(matches(hasDrawable()))

                onView(isRoot()).perform(ViewActions.pressBack())
            } catch (e: NoMatchingViewException) {
                onView(allOf(withId(R.id.no_connection), isDisplayed()))
                    .check(matches(isDisplayed()))
            }
        } catch (e: NoMatchingViewException) {
            onView(allOf(withId(R.id.no_connection), isDisplayed()))
                .check(matches(isDisplayed()))
        }
    }

    @Test
    fun testIntentToDetailActivityWithTvData() {
        onView(withId(R.id.tabLayout)).perform(selectTabAtPosition(1))

        try {
            onView(allOf(withId(R.id.rv_tv), isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
                .check(matches(not(atPosition(5, hasDescendant(withText(""))))))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))

            try {
                onView(allOf(withId(R.id.tv_title), isDisplayed()))
                    .check(matches(isDisplayed()))
                    .check(matches(not((withText("")))))

                onView(allOf(withId(R.id.tv_release), isDisplayed()))
                    .check(matches(isDisplayed()))
                    .check(matches(not((withText("")))))

                onView(allOf(withId(R.id.tv_overview), isDisplayed()))
                    .check(matches(isDisplayed()))
                    .check(matches(not((withText("")))))

                onView(allOf(withId(R.id.tv_duration), isDisplayed()))
                    .check(matches(isDisplayed()))
                    .check(matches(not((withText("")))))

                onView(allOf(withId(R.id.image_poster), isDisplayed()))
                    .check(matches(isDisplayed()))
                    .check(matches(hasDrawable()))

                onView(allOf(withId(R.id.image_bg), isDisplayed()))
                    .check(matches(isDisplayed()))
                    .check(matches(hasDrawable()))

                onView(isRoot()).perform(ViewActions.pressBack())
            } catch (e: NoMatchingViewException) {
                onView(allOf(withId(R.id.no_connection), isDisplayed()))
                    .check(matches(isDisplayed()))
            }
        } catch (e: NoMatchingViewException) {
            onView(allOf(withId(R.id.no_connection), isDisplayed()))
                .check(matches(isDisplayed()))
        }
    }
}