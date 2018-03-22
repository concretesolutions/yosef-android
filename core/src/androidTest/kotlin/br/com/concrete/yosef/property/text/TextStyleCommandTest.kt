package br.com.concrete.yosef.property.text

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.widget.TextView
import br.com.concrete.yosef.api.property.text.TextStyleCommand
import br.com.concrete.yosef.api.property.text.TextStyleCommand.Companion.TEXT_STYLE
import br.com.concrete.yosef.entity.DynamicProperty
import br.com.concrete.yosef.layoutAndAssert
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

    @Before
    fun setUp() {
        textStyleCommand = TextStyleCommand()
    }

    @Test
    fun renderingTextShouldApplyTextStyle() {

        val dynamicProperty = DynamicProperty(TextStyleCommand.TEXT_STYLE, "string", "bold")

        val textView = TextView(context)
        textStyleCommand.apply(textView, dynamicProperty)

        textView.layoutAndAssert {
            assertTrue(textView.typeface.isBold)
        }
    }

    @Test
    fun renderingTextViewWithWrongTextStyleValueShouldThrow() {

        val dynamicProperty = DynamicProperty(TEXT_STYLE, "string", "wrong")

        val textView = TextView(context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("The value(${dynamicProperty.value}) " +
            "for the textStyle property does not exist or is not supported")

        textStyleCommand.apply(textView, dynamicProperty)
    }
}
