package br.com.concrete.yosef.component

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.concrete.yosef.activities.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ButtonComponentTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun whenAddButtonThenShowTextView(){
        Espresso.onView(ViewMatchers.withText("Não")).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withText("Button Não"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}
