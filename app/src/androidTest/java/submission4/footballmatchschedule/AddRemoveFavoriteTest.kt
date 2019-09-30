package submission4.footballmatchschedule

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView.ViewHolder
import org.hamcrest.Matchers.anything
import org.hamcrest.core.IsNot.not
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@LargeTest
@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class AddRemoveFavoriteTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java, true, true)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.gettingIdlingResource())
    }

    @Test
    fun testAddFavoriteFromLastMatch() {
        onView(withContentDescription("Last Match")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(withContentDescription("Last Match")).perform(click())

        onView(withId(R.id.spinnerLeaguesLastMatch)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(withId(R.id.spinnerLeaguesLastMatch)).perform(click())

        onData(anything()).atPosition(3).perform(click())

        onView(withId(R.id.recyclerviewLastMatch)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(withId(R.id.recyclerviewLastMatch)).perform(actionOnItemAtPosition<ViewHolder>(5, click()))

        onView(withId(R.id.add_to_favorite)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(withId(R.id.add_to_favorite)).perform(click())

        onView(withText("Added to favorite")).inRoot(withDecorView(not(mActivityTestRule.activity.window.decorView)))
            .check(matches(isDisplayed()))

        Espresso.pressBack()

        onView(withContentDescription("Favorite")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(withContentDescription("Favorite")).perform(click())

        onView(withId(R.id.recyclerviewFavorite)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(withId(R.id.recyclerviewFavorite)).perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        Espresso.pressBack()
    }

    @Test
    fun testAddFavoriteFromNextMatch() {

        onView(withContentDescription("Next Match")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(withContentDescription("Next Match")).perform(click())

        onView(withId(R.id.spinnerLeaguesNextMatch)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(withId(R.id.spinnerLeaguesNextMatch)).perform(click())

        onData(anything()).atPosition(5).perform(click())

        onView(withId(R.id.recyclerviewNextMatch)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(withId(R.id.recyclerviewNextMatch)).perform(actionOnItemAtPosition<ViewHolder>(5, click()))

        onView(withId(R.id.add_to_favorite)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(withId(R.id.add_to_favorite)).perform(click())

        onView(withText("Added to favorite")).inRoot(withDecorView(not(mActivityTestRule.activity.window.decorView)))
            .check(matches(isDisplayed()))

        Espresso.pressBack()

        onView(withContentDescription("Favorite")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(withContentDescription("Favorite")).perform(click())

        onView(withId(R.id.recyclerviewFavorite)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(withId(R.id.recyclerviewFavorite)).perform(actionOnItemAtPosition<ViewHolder>(1, click()))

        Espresso.pressBack()
    }

    @Test
    fun testRemoveLastMatchFavorite() {
        onView(withContentDescription("Favorite")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(withContentDescription("Favorite")).perform(click())

        onView(withId(R.id.recyclerviewFavorite)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(withId(R.id.recyclerviewFavorite)).perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        onView(withId(R.id.add_to_favorite)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(withId(R.id.add_to_favorite)).perform(click())

        onView(withText("Removed from favorite")).inRoot(withDecorView(not(mActivityTestRule.activity.window.decorView)))
            .check(matches(isDisplayed()))

        Espresso.pressBack()
    }

    @Test
    fun testRemoveNextMatchFavorite() {
        onView(withContentDescription("Favorite")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(withContentDescription("Favorite")).perform(click())

        onView(withId(R.id.recyclerviewFavorite)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(withId(R.id.recyclerviewFavorite)).perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        onView(withId(R.id.add_to_favorite)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(withId(R.id.add_to_favorite)).perform(click())

        onView(withText("Removed from favorite")).inRoot(withDecorView(not(mActivityTestRule.activity.window.decorView)))
            .check(matches(isDisplayed()))

        Espresso.pressBack()
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.gettingIdlingResource())
    }
}
