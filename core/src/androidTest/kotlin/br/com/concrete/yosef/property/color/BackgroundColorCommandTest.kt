package br.com.concrete.yosef.property.color

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.CardView
import android.view.View
import br.com.concrete.yosef.api.property.color.BackgroundColorCommand
import br.com.concrete.yosef.api.property.color.BackgroundColorCommand.Companion.BACKGROUND_COLOR
import br.com.concrete.yosef.entity.DynamicProperty
import br.com.concrete.yosef.layoutAndAssert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BackgroundColorCommandTest {

    @Rule
    @JvmField
    val exceptionRule: ExpectedException = ExpectedException.none()

    private val context = InstrumentationRegistry.getTargetContext()!!

    private lateinit var backgroundColorCommand: BackgroundColorCommand

    @Before
    fun setUp() {
        backgroundColorCommand = BackgroundColorCommand()
    }

    @Test
    fun renderingViewShouldApplyBackgroundColor() {
        val dynamicProperty = DynamicProperty(BACKGROUND_COLOR, "color", "#0000FF")

        val view = View(context)
        backgroundColorCommand.apply(view, dynamicProperty)

        view.layoutAndAssert {
            val viewBackgroundColor = view.background as ColorDrawable
            assertTrue(viewBackgroundColor.color == Color.parseColor(dynamicProperty.value))
        }
    }

    @Test
    fun renderingViewWithWrongBackgroundValueShouldThrow() {
        val dynamicProperty = DynamicProperty(BACKGROUND_COLOR, "color", "wrong")

        val view = View(context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("The value (${dynamicProperty.value}) " +
            "cannot be parsed as a color")

        backgroundColorCommand.apply(view, dynamicProperty)
    }

    @Test
    fun renderingCardViewShouldApplyBackgroundColor() {
        val dynamicProperty = DynamicProperty(BACKGROUND_COLOR, "color", "#0000FF")

        val cardView = CardView(context)
        backgroundColorCommand.apply(cardView, dynamicProperty)

        cardView.layoutAndAssert {
            val viewBackgroundColor = cardView.cardBackgroundColor.defaultColor
            assertTrue(viewBackgroundColor == Color.parseColor(dynamicProperty.value))
        }
    }
}
