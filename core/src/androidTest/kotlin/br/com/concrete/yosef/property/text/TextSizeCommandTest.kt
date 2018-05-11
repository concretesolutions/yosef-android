package br.com.concrete.yosef.property.text

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.widget.ImageView
import android.widget.TextView
import br.com.concrete.yosef.api.property.text.TextSizeCommand
import br.com.concrete.yosef.api.property.text.TextSizeCommand.Companion.TEXT_SIZE
import br.com.concrete.yosef.dp
import br.com.concrete.yosef.entity.DynamicProperty
import br.com.concrete.yosef.layoutAndAssert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TextSizeCommandTest {

    @Rule
    @JvmField
    val exceptionRule: ExpectedException = ExpectedException.none()

    private val context = InstrumentationRegistry.getTargetContext()!!

    private lateinit var textSizeCommand: TextSizeCommand

    @Before
    fun setUp() {
        textSizeCommand = TextSizeCommand()
    }

    @Test
    fun renderingTextViewShouldApplyTextSize() {
        val dynamicProperty = DynamicProperty(TEXT_SIZE, "dimen", "25")

        val textView = TextView(context)
        textSizeCommand.apply(textView, dynamicProperty)

        textView.layoutAndAssert {
            assertEquals(25f.dp(context), textView.textSize)
        }
    }

    @Test
    fun renderingImageViewShouldThrow() {
        val dynamicProperty = DynamicProperty(TEXT_SIZE, "dimen", "25")

        val imageView = ImageView(context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("The property does not support setting text size" +
            " for the type ${imageView.javaClass.name}")

        textSizeCommand.apply(imageView, dynamicProperty)
    }
}
