package br.com.concrete.yosef.canarinho.component

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.EditText
import br.com.concrete.yosef.canarinho.activities.TextFieldActivity
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CanarinhoComponentTextField {

    @Rule
    @JvmField
    val activity = ActivityTestRule<TextFieldActivity>(TextFieldActivity::class.java)

    @Test
    fun wheAddTextWithMonetaryMaskThenShowValueFormatted() {
        onView(allOf(
                isAssignableFrom(EditText::class.java)))
                .perform(typeText("123123123"))

        onView(withText("R$ 1.231.231,23"))
                .check(matches(isDisplayed()))
    }

}
