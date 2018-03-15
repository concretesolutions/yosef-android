package br.com.concrete.yosef.canarinho.component

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.EditText
import br.com.concrete.yosef.canarinho.activities.TextFieldActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CanarinhoComponentText {

    @Rule
    @JvmField
    val activity = ActivityTestRule<TextFieldActivity>(TextFieldActivity::class.java)

    @Test
    fun wheAddTextWithMonetaryMaskThenShowValueFormatted() {
        Espresso.onView(Matchers.allOf(
                ViewMatchers.isAssignableFrom(EditText::class.java)))
                .perform(ViewActions.typeText("123123123"))

        Espresso.onView(ViewMatchers.withText("R$ 1.231.231,23"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}