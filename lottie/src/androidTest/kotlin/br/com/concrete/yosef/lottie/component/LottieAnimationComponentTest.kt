package br.com.concrete.yosef.lottie

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import br.com.concrete.yosef.afterLayout
import br.com.concrete.yosef.api.DynamicViewCreator
import com.airbnb.lottie.LottieAnimationView
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LottieAnimationComponentTest {

    @Rule
    @JvmField
    val exceptionRule: ExpectedException = ExpectedException.none()

    private val context = InstrumentationRegistry.getTargetContext()!!
    private lateinit var parent: ViewGroup
    private lateinit var creator: DynamicViewCreator

    @Before
    fun setUp() {

        parent = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }

        creator = DynamicViewCreator
            .Builder()
            .addComponentFor(LottieAnimationComponent.ANIMATION_TYPE, LottieAnimationComponent())
            .build()
    }

    @Test
    fun renderingShouldAssignRightViewId() {

        val json = context.assets.open("example_animation.json")
            .bufferedReader()
            .use { it.readText() }

        creator.createViewFromJson(parent, json, null)

        val animationView = parent.findViewById<LottieAnimationView>("animationView".hashCode())

        animationView.afterLayout {
            assertEquals("animationView".hashCode(), animationView.id)
        }
    }

    @Test
    fun renderingShouldPlayAnimationAutomatically() {

        val json = context.assets.open("example_animation.json")
            .bufferedReader()
            .use { it.readText() }

        creator.createViewFromJson(parent, json, null)

        val animationView = parent.findViewById<LottieAnimationView>("animationView".hashCode())

        animationView.afterLayout {
            assertEquals(View.VISIBLE, animationView.visibility)
            assertTrue("animation view is not animating", animationView.isAnimating)
        }
    }

    @Test
    fun renderingShouldSetRightRepeatCount() {

        val json = context.assets.open("example_animation_with_count.json")
            .bufferedReader()
            .use { it.readText() }

        creator.createViewFromJson(parent, json, null)

        val animationView = parent.findViewById<LottieAnimationView>("animationView".hashCode())

        animationView.afterLayout {
            assertEquals(2, animationView.repeatCount)
        }
    }

    @Test
    fun renderingShouldHaveRightViewSize() {

        val json = context.assets.open("example_animation_with_count.json")
            .bufferedReader()
            .use { it.readText() }

        creator.createViewFromJson(parent, json, null)

        val animationView = parent.findViewById<LottieAnimationView>("animationView".hashCode())

        animationView.afterLayout {
            assertEquals(300, animationView.width)
            assertEquals(300, animationView.height)
        }
    }

    @Test
    fun shouldCrashIfCannotHandleProperty() {

        val json = context.assets.open("example_unknown_field.json")
            .bufferedReader()
            .use { it.readText() }

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage(allOf(containsString("Property wrong_width"),
            containsString("value = 300"),
            containsString("cannot be applied")))

        creator.createViewFromJson(parent, json, null)
    }

    @Test
    fun shouldCrashIfMandatoryFieldIsMissing() {

        val json = context.assets.open("example_missing_field.json")
            .bufferedReader()
            .use { it.readText() }

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage(allOf(containsString("Mandatory field 'animate'"),
            containsString("not found")))

        creator.createViewFromJson(parent, json, null)
    }
}
