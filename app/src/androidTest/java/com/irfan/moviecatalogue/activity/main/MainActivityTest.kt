package com.irfan.moviecatalogue.activity.main


import android.content.Context
import android.os.SystemClock
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.irfan.moviecatalogue.R
import com.irfan.moviecatalogue.utils.DrawableMatcher.hasDrawable
import com.irfan.moviecatalogue.utils.DrawableMatcher.withDrawable
import com.irfan.moviecatalogue.utils.MovieData
import com.irfan.moviecatalogue.utils.TabLayoutTestUtil.selectTabAtPosition
import com.irfan.moviecatalogue.utils.TvData
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val dummyMovieData = MovieData.listData
    private val dummyTvData = TvData.listData
    private lateinit var instrumentationContext: Context

    @Before
    fun setup(){
        ActivityScenario.launch(MainActivity::class.java)
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
    }

    @Test
    fun testMovieRecyclerView() {
        onView(withId(R.id.rv_movie))
                .check(matches(isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovieData.size - 1)
        )
    }

    @Test
    fun testTvRecyclerView() {
        onView(withId(R.id.tabLayout))
                .check(matches(isDisplayed()))
                .perform(selectTabAtPosition(1))

        SystemClock.sleep(800)

        onView(withId(R.id.rv_tv))
                .check(matches(isCompletelyDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvData.size - 1)
        )
    }

    @Test
    fun testIntentToDetailActivityWithMovieData() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        SystemClock.sleep(800)

        onView(withId(R.id.tv_title))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(dummyMovieData[0].title)))

        onView(withId(R.id.tv_release))
                .check(matches(isDisplayed()))
                .check(matches(withText(dummyMovieData[0].release)))

        onView(withId(R.id.tv_overview))
                .check(matches(isDisplayed()))
                .check(matches(withText(dummyMovieData[0].overview)))

        onView(withId(R.id.tv_duration))
                .check(matches(isDisplayed()))
                .check(matches(withText(dummyMovieData[0].duration)))

        onView(withId(R.id.image_poster))
                .check(matches(isDisplayed()))
                .check(matches(hasDrawable()))
                .check(matches(withDrawable(R.drawable.movie_poster_a_start_is_born)))

        onView(withId(R.id.image_bg))
                .check(matches(isDisplayed()))
                .check(matches(hasDrawable()))
                .check(matches(withDrawable(R.drawable.movie_poster_a_start_is_born)))

        onView(isRoot()).perform(ViewActions.pressBack())

    }

    @Test
    fun testIntentToDetailActivityWithTvData() {
        onView(withId(R.id.tabLayout)).perform(selectTabAtPosition(1))

        SystemClock.sleep(800)

        onView(withId(R.id.rv_tv))
                .check(matches(isCompletelyDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvData.size - 1))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        dummyTvData.size -1,
                        click()
                )
        )

        SystemClock.sleep(800)

        onView(withId(R.id.tv_title))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(dummyTvData[dummyTvData.size-1].title)))

        onView(withId(R.id.tv_release))
                .check(matches(isDisplayed()))
                .check(matches(withText(dummyTvData[dummyTvData.size-1].release)))

        onView(withId(R.id.tv_overview))
                .check(matches(isDisplayed()))
                .check(matches(withText(dummyTvData[dummyTvData.size-1].overview)))

        onView(withId(R.id.tv_duration))
                .check(matches(isDisplayed()))
                .check(matches(withText(dummyTvData[dummyTvData.size-1].duration)))

        onView(withId(R.id.image_poster))
                .check(matches(isDisplayed()))
                .check(matches(hasDrawable()))
                .check(matches(withDrawable(R.drawable.tv_poster_hanna)))

        onView(withId(R.id.image_bg))
                .check(matches(isDisplayed()))
                .check(matches(hasDrawable()))
                .check(matches(withDrawable(R.drawable.tv_poster_hanna)))

        onView(isRoot()).perform(ViewActions.pressBack())
    }

}