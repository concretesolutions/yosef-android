package br.com.concrete.yosef.picasso.component

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.ImageView
import br.com.concrete.yosef.picasso.activities.ImageActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test for PicassoImageComponent
 */
@RunWith(AndroidJUnit4::class)
class PicassoImageComponentTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<ImageActivity>(ImageActivity::class.java)

    @Test
    fun whenAddImageThenShowImageFromUrl() {
        onView(Matchers.allOf(ViewMatchers.isAssignableFrom(ImageView::class.java)))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}
