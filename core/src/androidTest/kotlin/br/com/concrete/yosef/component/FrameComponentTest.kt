package br.com.concrete.yosef.component

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.concrete.yosef.activities.FrameActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FrameComponentTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<FrameActivity>(FrameActivity::class.java)

    @Test
    fun whenAddElementGroupThenShowViews() {
        onView(withText("BACKGROUND")).check(matches(isDisplayed()))
        onView(withText("FOREGROUND")).check(matches(isDisplayed()))
    }
}
