package br.com.concrete.yosef.property.text

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import br.com.concrete.yosef.afterLayout
import br.com.concrete.yosef.api.property.text.TextStyleCommand
import br.com.concrete.yosef.api.property.text.TextStyleCommand.Companion.TEXT_STYLE
import br.com.concrete.yosef.entity.DynamicProperty
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TextStyleCommandTest {

    @Rule
    @JvmField
    val exceptionRule: ExpectedException = ExpectedException.none()

    private val context = InstrumentationRegistry.getTargetContext()!!

    private lateinit var textStyleCommand: TextStyleCommand

    private lateinit var parent: ViewGroup

    @Before
    fun setUp() {

        parent = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }
        textStyleCommand = TextStyleCommand()

    }

    @Test
    fun renderingTextShouldApplyTextStyle() {

        val dynamicProperty = DynamicProperty(TextStyleCommand.TEXT_STYLE, "string", "bold")

        val textView = TextView(parent.context)
        textStyleCommand.apply(textView, dynamicProperty)

        parent.addView(textView)

        textView.afterLayout {
            assertTrue(textView.typeface.isBold)
        }

    }

    @Test
    fun renderingTextViewWithWrongTextStyleValueShouldThrow() {

        val dynamicProperty = DynamicProperty(TEXT_STYLE, "string", "wrong")

        val textView = TextView(parent.context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("The value(${dynamicProperty.value}) " +
                "for the textStyle property does not exist or is not supported")

        textStyleCommand.apply(textView, dynamicProperty)

    }

}
