package br.com.concrete.yosef.api.property.color

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.widget.ImageView
import android.widget.TextView
import br.com.concrete.yosef.api.property.color.ColorFilterCommand.Companion.COLOR_FILTER
import br.com.concrete.yosef.entity.DynamicProperty
import br.com.concrete.yosef.layoutAndAssert
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ColorFilterCommandTest {

    @Rule
    @JvmField
    val exceptionRule: ExpectedException = ExpectedException.none()

    private val context = InstrumentationRegistry.getTargetContext()!!

    private lateinit var colorFilterCommand: ColorFilterCommand

    @Before
    fun setUp() {
        colorFilterCommand = ColorFilterCommand()
    }

    @Test
    fun renderingImageViewShouldApplyColorFilter() {
        val dynamicProperty = DynamicProperty(COLOR_FILTER, "color", "#800000FF")
        val imageView = ImageView(context)
        colorFilterCommand.apply(imageView, dynamicProperty)

        imageView.layoutAndAssert {
            Assert.assertTrue(
                imageView.colorFilter == PorterDuffColorFilter(
                    Color.parseColor(dynamicProperty.value),
                    PorterDuff.Mode.SRC_ATOP
                )
            )
        }
    }

    @Test
    fun renderingInvalidColorShouldThrow() {
        val dynamicProperty = DynamicProperty(COLOR_FILTER, "color", "XYZ")
        val imageView = ImageView(context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("The value (${dynamicProperty.value}) " +
                "cannot be parsed as a color")

        colorFilterCommand.apply(imageView, dynamicProperty)
    }

    @Test
    fun renderingTextViewShouldThrow() {
        val dynamicProperty = DynamicProperty(COLOR_FILTER, "color", "#80000000")
        val textView = TextView(context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("The value (${dynamicProperty.value}) " +
                "for the $COLOR_FILTER property is not compatible with ${textView.javaClass.name}")

        colorFilterCommand.apply(textView, dynamicProperty)
    }

}