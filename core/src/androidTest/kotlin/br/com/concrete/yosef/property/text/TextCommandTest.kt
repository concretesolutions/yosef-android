package br.com.concrete.yosef.property.text

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.widget.ImageView
import android.widget.TextView
import br.com.concrete.yosef.api.property.text.TextCommand
import br.com.concrete.yosef.api.property.text.TextCommand.Companion.TEXT
import br.com.concrete.yosef.entity.DynamicProperty
import br.com.concrete.yosef.layoutAndAssert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TextCommandTest {

    @Rule
    @JvmField
    val exceptionRule: ExpectedException = ExpectedException.none()

    private val context = InstrumentationRegistry.getTargetContext()!!

    private lateinit var textCommand: TextCommand

    @Before
    fun setUp() {
        textCommand = TextCommand()
    }

    @Test
    fun renderingTextViewShouldApplyText() {
        val dynamicProperty = DynamicProperty(TEXT, "string", "Teste texto")

        val textView = TextView(context)
        textCommand.apply(textView, dynamicProperty)

        textView.layoutAndAssert {
            assertEquals(textView.text.toString(), dynamicProperty.value)
        }
    }

    @Test
    fun renderingImageViewShouldThrow() {
        val dynamicProperty = DynamicProperty(TEXT, "string", "Teste texto")

        val imageView = ImageView(context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("The value (${dynamicProperty.value}) for the $TEXT " +
            "property is not compatible with ${imageView.javaClass.name}")

        textCommand.apply(imageView, dynamicProperty)
    }
}
