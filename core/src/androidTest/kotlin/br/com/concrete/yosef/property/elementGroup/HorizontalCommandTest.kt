package br.com.concrete.yosef.property.elementGroup

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import br.com.concrete.yosef.api.property.elementgroup.HorizontalMarginCommand
import br.com.concrete.yosef.api.property.elementgroup.HorizontalMarginCommand.Companion.HORIZONTAL_MARGIN
import br.com.concrete.yosef.api.property.elementgroup.SpacingCommand
import br.com.concrete.yosef.api.property.elementgroup.SpacingCommand.Companion.SPACING
import br.com.concrete.yosef.entity.DynamicProperty
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import kotlin.math.roundToInt

@RunWith(AndroidJUnit4::class)
class HorizontalCommandTest {

    @Rule
    @JvmField
    val exceptionRule: ExpectedException = ExpectedException.none()

    private val context = InstrumentationRegistry.getTargetContext()!!

    private lateinit var horizontalMarginCommand: HorizontalMarginCommand

    @Before
    fun setUp() {
        horizontalMarginCommand = HorizontalMarginCommand()
    }

    @Test
    fun whenTheParentIsLinearLayout_shouldSetTheCorrectMargin() {
        val dynamicProperty = DynamicProperty(HORIZONTAL_MARGIN, "dimen", "16")
        val parent = LinearLayout(context)
        val linearLayout = LinearLayout(context)
        parent.addView(linearLayout)
        val valueInPx: Int = (16 * context.resources.displayMetrics.density).roundToInt()

        horizontalMarginCommand.apply(linearLayout, dynamicProperty)

        assertTrue((linearLayout.layoutParams as LinearLayout.LayoutParams).leftMargin == valueInPx)
        assertTrue((linearLayout.layoutParams as LinearLayout.LayoutParams).rightMargin == valueInPx)
    }

    @Test
    fun whenTheParentIsFrameLayout_shouldSetTheCorrectMargin() {
        val dynamicProperty = DynamicProperty(HORIZONTAL_MARGIN, "dimen", "16")
        val parent = FrameLayout(context)
        val linearLayout = LinearLayout(context)
        parent.addView(linearLayout)
        val valueInPx: Int = (16 * context.resources.displayMetrics.density).roundToInt()

        horizontalMarginCommand.apply(linearLayout, dynamicProperty)

        assertTrue((linearLayout.layoutParams as FrameLayout.LayoutParams).leftMargin == valueInPx)
        assertTrue((linearLayout.layoutParams as FrameLayout.LayoutParams).rightMargin == valueInPx)
    }

    @Test
    fun renderingImageViewShouldThrow() {
        val dynamicProperty = DynamicProperty(HORIZONTAL_MARGIN, "dimen", "16")
        val imageView = ImageView(context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("The property does not support setting $HORIZONTAL_MARGIN for the type " +
            imageView.javaClass.name)

        horizontalMarginCommand.apply(imageView, dynamicProperty)
    }
}