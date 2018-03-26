package br.com.concrete.yosef.property.elementGroup

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import br.com.concrete.yosef.api.property.elementgroup.HorizontalMarginCommand.Companion.HORIZONTAL_MARGIN
import br.com.concrete.yosef.api.property.elementgroup.VerticalMarginCommand
import br.com.concrete.yosef.api.property.elementgroup.VerticalMarginCommand.Companion.VERTICAL_MARGIN
import br.com.concrete.yosef.entity.DynamicProperty
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import kotlin.math.roundToInt

@RunWith(AndroidJUnit4::class)
class VerticalCommandTest {

    @Rule
    @JvmField
    val exceptionRule: ExpectedException = ExpectedException.none()

    private val context = InstrumentationRegistry.getTargetContext()!!

    private lateinit var verticalMarginCommand: VerticalMarginCommand

    @Before
    fun setUp() {
        verticalMarginCommand = VerticalMarginCommand()
    }

    @Test
    fun whenTheParentIsLinearLayout_shouldSetTheCorrectMargin() {
        val dynamicProperty = DynamicProperty(VERTICAL_MARGIN, "dimen", "16")
        val parent = LinearLayout(context)
        val linearLayout = LinearLayout(context)
        parent.addView(linearLayout)
        val valueInPx: Int = (16 * context.resources.displayMetrics.density).roundToInt()

        verticalMarginCommand.apply(linearLayout, dynamicProperty)

        assertTrue((linearLayout.layoutParams as LinearLayout.LayoutParams).topMargin == valueInPx)
        assertTrue((linearLayout.layoutParams as LinearLayout.LayoutParams).bottomMargin == valueInPx)
    }

    @Test
    fun whenTheParentIsFrameLayout_shouldSetTheCorrectMargin() {
        val dynamicProperty = DynamicProperty(VERTICAL_MARGIN, "dimen", "16")
        val parent = FrameLayout(context)
        val linearLayout = LinearLayout(context)
        parent.addView(linearLayout)
        val valueInPx: Int = (16 * context.resources.displayMetrics.density).roundToInt()

        verticalMarginCommand.apply(linearLayout, dynamicProperty)

        assertTrue((linearLayout.layoutParams as FrameLayout.LayoutParams).topMargin == valueInPx)
        assertTrue((linearLayout.layoutParams as FrameLayout.LayoutParams).bottomMargin == valueInPx)
    }

    @Test
    fun renderingImageViewShouldThrow() {
        val dynamicProperty = DynamicProperty(VERTICAL_MARGIN, "dimen", "16")
        val imageView = ImageView(context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("The property does not support setting $VERTICAL_MARGIN for the type " +
            imageView.javaClass.name)

        verticalMarginCommand.apply(imageView, dynamicProperty)
    }
}