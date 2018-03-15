package br.com.concrete.yosef.ui.property.text

import android.graphics.Color
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import br.com.concrete.yosef.afterLayout
import br.com.concrete.yosef.api.property.text.TextColorCommand
import br.com.concrete.yosef.api.property.text.TextColorCommand.Companion.TEXT_COLOR
import br.com.concrete.yosef.entity.DynamicProperty
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TextColorCommandTest {

    @Rule
    @JvmField
    val exceptionRule: ExpectedException = ExpectedException.none()

    private val context = InstrumentationRegistry.getTargetContext()!!

    private lateinit var textColorCommand: TextColorCommand

    private lateinit var parent: ViewGroup

    @Before
    fun setUp() {
        parent = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }
        textColorCommand = TextColorCommand()
    }

    @Test
    fun renderingTextViewShouldApplyTextColor() {
        val dynamicProperty = DynamicProperty(TEXT_COLOR, "color", "#0000FF")

        val textView = TextView(parent.context)
        textColorCommand.apply(textView, dynamicProperty)

        parent.addView(textView)

        textView.afterLayout {
            assertTrue(textView.currentTextColor == Color.parseColor(dynamicProperty.value))
        }
    }

    @Test
    fun renderingTextViewWithWrongTextColorValueShouldThrow() {
        val dynamicProperty = DynamicProperty(TEXT_COLOR, "color", "wrong")

        val textView = TextView(parent.context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("The value (${dynamicProperty.value}) " +
                "cannot be parsed as a color")

        textColorCommand.apply(textView, dynamicProperty)

    }

    @Test
    fun renderingImageViewShouldThrow() {
        val dynamicProperty = DynamicProperty(TEXT_COLOR, "color", "#0000FF")

        val imageView = ImageView(parent.context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("The value (${dynamicProperty.value}) " +
                "for the $TEXT_COLOR property is not compatible with ${imageView.javaClass.name}")

        textColorCommand.apply(imageView, dynamicProperty)
    }

}