package br.com.concrete.yosef.ui.component

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.EditText
import br.com.concrete.yosef.activities.RadioGroupActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RadioGroupTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<RadioGroupActivity>(RadioGroupActivity::class.java)

    @Test
    fun whenAddRadioGroupThenClickButtonAndShowTextview(){
        onView(Matchers.allOf(
                ViewMatchers.isAssignableFrom(EditText::class.java)))
    }

}