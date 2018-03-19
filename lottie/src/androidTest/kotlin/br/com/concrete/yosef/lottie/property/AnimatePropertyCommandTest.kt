package br.com.concrete.yosef.lottie

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.view.View
import br.com.concrete.yosef.entity.DynamicProperty
import com.airbnb.lottie.LottieAnimationView
import org.hamcrest.CoreMatchers.containsString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AnimatePropertyCommandTest {

    @Rule
    @JvmField
    val exceptionRule: ExpectedException = ExpectedException.none()

    private val context = InstrumentationRegistry.getTargetContext()
    private lateinit var command: AnimatePropertyCommand

    @Before
    fun setUp() {
        command = AnimatePropertyCommand()
    }

    @Test
    fun shouldThrowWhenReceivingWrongViewType() {

        val view = View(context)
        val property = DynamicProperty("animate", "string", "[]")

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage(containsString("Could not start " +
                "animation in view android.view.View"))
        exceptionRule.expectMessage(containsString("Valid json: true"))

        command.apply(view, property)
    }

    @Test
    fun shouldThrowWhenReceivingBadJson() {

        val view = LottieAnimationView(context)
        val property = DynamicProperty("animate", "string", "bad json")

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage(containsString("Valid json: false"))

        command.apply(view, property)


    }
}
