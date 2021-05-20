package com.irfan.moviecatalogue.ui.activity.main


import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
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

    @get:Rule
    var idlingResourceRule = IdlingResourceRule()

    @Before
    fun setUp(){
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun testLoadMovieList() {
        onView(allOf(withId(R.id.rv_movie), isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8))
            .check(matches(not(atPosition(8, hasDescendant(withText(""))))))
    }

    @Test
    fun testLoadTvShowList() {
        onView(withId(R.id.tabLayout))
            .check(matches(isDisplayed()))
            .perform(selectTabAtPosition(1))

        onView(allOf(withId(R.id.rv_tv), isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8))
            .check(matches(not(atPosition(8, hasDescendant(withText(""))))))
    }

    @Test
    fun testIntentToDetailActivityWithMovieData() {
        onView(allOf(withId(R.id.rv_movie), isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(6))
            .check(matches(not(atPosition(6, hasDescendant(withText(""))))))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(6, click()))

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
    }

    @Test
    fun testIntentToDetailActivityWithTvData() {
        onView(withId(R.id.tabLayout)).perform(selectTabAtPosition(1))

        onView(allOf(withId(R.id.rv_tv), isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(6))
            .check(matches(not(atPosition(6, hasDescendant(withText(""))))))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(6, click()))

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
    }

    @Test
    fun testAddAndThenRemoveMovieItemFromFavourite() {
        onView(allOf(withId(R.id.rv_movie), isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
                .check(matches(not(atPosition(1, hasDescendant(withText(""))))))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))

        onView(allOf(withId(R.id.tv_title), isDisplayed()))
                .check(matches(isDisplayed()))
                .check(matches(not((withText("")))))

        onView(allOf(withId(R.id.btn_favorite), isDisplayed()))
                .perform(click())

        onView(isRoot()).perform(ViewActions.pressBack())

        onView(allOf(withId(R.id.menu_favourite), isDisplayed()))
                .check(matches(isDisplayed()))
                .perform(click())

        onView(allOf(withId(R.id.rv_favourite), isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
                .check(matches(not(atPosition(0, hasDescendant(withText(""))))))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(allOf(withId(R.id.tv_title), isDisplayed()))
                .check(matches(isDisplayed()))
                .check(matches(not((withText("")))))

        onView(allOf(withId(R.id.btn_favorite), isDisplayed()))
                .check(matches(isDisplayed()))
                .perform(click())

        onView(isRoot()).perform(ViewActions.pressBack())

        onView(isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun testAddAndThenRemoveTvItemFromFavourite() {
        onView(withId(R.id.tabLayout)).perform(selectTabAtPosition(1))

        onView(allOf(withId(R.id.rv_tv), isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
                .check(matches(not(atPosition(1, hasDescendant(withText(""))))))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))

        onView(allOf(withId(R.id.tv_title), isDisplayed()))
                .check(matches(isDisplayed()))
                .check(matches(not((withText("")))))

        onView(allOf(withId(R.id.btn_favorite), isDisplayed()))
                .perform(click())

        onView(isRoot()).perform(ViewActions.pressBack())

        onView(allOf(withId(R.id.menu_favourite), isDisplayed()))
                .check(matches(isDisplayed()))
                .perform(click())

        onView(allOf(withId(R.id.tabLayout), isDisplayed()))
                .perform(selectTabAtPosition(1))

        Thread.sleep(1000)

        onView(allOf(withId(R.id.rv_favourite), isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
                .check(matches(not(atPosition(0, hasDescendant(withText(""))))))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(allOf(withId(R.id.tv_title), isDisplayed()))
                .check(matches(isDisplayed()))
                .check(matches(not((withText("")))))

        onView(allOf(withId(R.id.btn_favorite), isDisplayed()))
                .check(matches(isDisplayed()))
                .perform(click())

        onView(isRoot()).perform(ViewActions.pressBack())

        onView(isRoot()).perform(ViewActions.pressBack())
    }

}