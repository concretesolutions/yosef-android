package br.com.concrete.yosef.gradient.property

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.widget.TextView
import br.com.concrete.yosef.entity.DynamicProperty
import br.com.concrete.yosef.gradient.property.ColorArrayCommand.Companion.COLORS
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ColorArrayCommandTest {

    @Rule
    @JvmField
    val exceptionRule: ExpectedException = ExpectedException.none()

    private val context = InstrumentationRegistry.getTargetContext()!!

    private lateinit var colorArrayCommand: ColorArrayCommand

    @Before
    fun setUp() {
        colorArrayCommand = ColorArrayCommand()
    }

    @Test
    fun whenOneColor_shouldSetCorrectGradientToTheView() {
        val dynamicProperty = DynamicProperty(COLORS, "colors", "0.0.0.0")
        val view = TextView(context)
        val colors = intArrayOf(Color.argb(0, 0, 0, 0))
        val correctGradient = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            colors
        )

        colorArrayCommand.apply(view, dynamicProperty)

        assertEquals((view.background as GradientDrawable).colors[0], correctGradient.colors[0])
        assertEquals((view.background as GradientDrawable).alpha, correctGradient.alpha)
    }

    @Test
    fun whenTwoColors_shouldSetCorrectGradientToTheView() {
        val dynamicProperty = DynamicProperty(COLORS, "colors", "0.0.0.255, 0.0.0.0")
        val view = TextView(context)
        val colors = intArrayOf(Color.argb(255, 0, 0, 0), Color.argb(0, 0, 0, 0))
        val correctGradient = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            colors
        )

        colorArrayCommand.apply(view, dynamicProperty)

        assertEquals((view.background as GradientDrawable).colors[0], correctGradient.colors[0])
        assertEquals((view.background as GradientDrawable).colors[1], correctGradient.colors[1])
        assertEquals((view.background as GradientDrawable).alpha, correctGradient.alpha)
    }

    @Test
    fun whenWrongType_shouldThrowCorrectException() {
        val dynamicProperty = DynamicProperty(COLORS, "WRONG", "0.0.0.255, 0.0.0.0")
        val view = View(context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("Can't apply colors with type 'WRONG', unknown type.")

        colorArrayCommand.apply(view, dynamicProperty)
    }

    @Test
    fun whenWrongArrayFormat_shouldThrowCorrectException() {
        val dynamicProperty = DynamicProperty(COLORS, "colors", "0.0.0.255-0.0.0.0")
        val view = View(context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("Can't apply colors with value '0.0.0.255-0.0.0.0', " +
            "it does not apply the property format('0.0.0.255, 0.0.0.0' for example).")

        colorArrayCommand.apply(view, dynamicProperty)
    }

    @Test
    fun whenWrongColorFormat_shouldThrowCorrectException() {
        val dynamicProperty = DynamicProperty(COLORS, "colors", "0-0-0-255, 0-0-0-0")
        val view = View(context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("Can't apply colors with value '0-0-0-255, 0-0-0-0', " +
            "it does not apply the property format('0.0.0.255, 0.0.0.0' for example).")

        colorArrayCommand.apply(view, dynamicProperty)
    }

    @Test
    fun whenMissingColorValue_shouldThrowCorrectException() {
        val dynamicProperty = DynamicProperty(COLORS, "colors", "0.0.0.255, 0.0.0")
        val view = View(context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("Can't apply colors with value '0.0.0.255, 0.0.0', " +
            "it does not apply the property format('0.0.0.255, 0.0.0.0' for example).")

        colorArrayCommand.apply(view, dynamicProperty)
    }

    @Test
    fun whenValueIsEmpty_shouldThrowCorrectException() {
        val dynamicProperty = DynamicProperty(COLORS, "colors", "")
        val view = View(context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("Can't apply colors with value '', it does not " +
            "apply the property format('0.0.0.255, 0.0.0.0' for example).")

        colorArrayCommand.apply(view, dynamicProperty)
    }

    @Test
    fun whenValueIsWrong_shouldThrowCorrectException() {
        val dynamicProperty = DynamicProperty(COLORS, "colors", ",")
        val view = View(context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("Can't apply colors with value ',', it does not " +
            "apply the property format('0.0.0.255, 0.0.0.0' for example).")

        colorArrayCommand.apply(view, dynamicProperty)
    }

    @Test
    fun whenColorNotInt_shouldThrowCorrectException() {
        val dynamicProperty = DynamicProperty(COLORS, "colors", "F.F.F.F, 0.0.0.0")
        val view = View(context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("Can't apply colors with value 'F.F.F.F, 0.0.0.0', it does not " +
            "apply the property format('0.0.0.255, 0.0.0.0' for example).")

        colorArrayCommand.apply(view, dynamicProperty)
    }
}
