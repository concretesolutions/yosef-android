package br.com.concrete.yosef.component

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.KeyEvent
import android.widget.EditText
import br.com.concrete.yosef.activities.MainActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TextFieldComponentTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun whenAddTextFieldReplaceTextThenShowTextView() {

        onView(allOf(
            isAssignableFrom(EditText::class.java)))
            .perform(typeText("Teste de textfield"), ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))

        onView(ViewMatchers.withId("textView".hashCode()))
            .check(ViewAssertions.matches(ViewMatchers.withText("Teste de textfield")))
    }
}
