package br.com.concrete.yosef.ui.property.color

import android.graphics.Color
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import br.com.concrete.yosef.afterLayout
import br.com.concrete.yosef.api.property.color.TintColorCommand
import br.com.concrete.yosef.api.property.color.TintColorCommand.Companion.TINT_COLOR
import br.com.concrete.yosef.entity.DynamicProperty
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TintColorCommandTest {

    @Rule
    @JvmField
    val exceptionRule: ExpectedException = ExpectedException.none()

    private val context = InstrumentationRegistry.getTargetContext()!!

    private lateinit var tintCommand: TintColorCommand

    private lateinit var parent: ViewGroup

    @Before
    fun setUp() {
        parent = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }
        tintCommand = TintColorCommand()
    }

    @Test
    fun renderingCompoundButtonShouldApplyButtonTintColor() {
        val dynamicProperty = DynamicProperty(TINT_COLOR, "color", "#0000FF")

        val radioButton = RadioButton(parent.context)
        tintCommand.apply(radioButton, dynamicProperty)

        parent.addView(radioButton)

        radioButton.afterLayout {
            assertTrue(radioButton.buttonTintList.defaultColor ==
                Color.parseColor(dynamicProperty.value))
        }
    }

    @Test
    fun renderingCompoundButtonWithWrongTintColorValueShouldThrow() {
        val dynamicProperty = DynamicProperty(TINT_COLOR, "color", "wrong")

        val radioButton = RadioButton(parent.context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("The value (${dynamicProperty.value}) " +
            "cannot be parsed as a color")

        tintCommand.apply(radioButton, dynamicProperty)
    }

    @Test
    fun renderingImageViewShouldThrow() {
        val dynamicProperty = DynamicProperty(TINT_COLOR, "color", "#0000FF")
        val imageView = ImageView(parent.context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("The value (${dynamicProperty.value}) " +
            "for the $TINT_COLOR property is not compatible with ${imageView.javaClass.name}")

        tintCommand.apply(imageView, dynamicProperty)
    }
}