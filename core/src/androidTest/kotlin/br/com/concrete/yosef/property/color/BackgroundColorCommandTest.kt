package br.com.concrete.yosef.property.color

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.CardView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import br.com.concrete.yosef.afterLayout
import br.com.concrete.yosef.api.property.color.BackgroundColorCommand
import br.com.concrete.yosef.api.property.color.BackgroundColorCommand.Companion.BACKGROUND_COLOR
import br.com.concrete.yosef.entity.DynamicProperty
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

    private lateinit var parent: ViewGroup

    @Before
    fun setUp() {
        parent = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }
        backgroundColorCommand = BackgroundColorCommand()
    }

    @Test
    fun renderingViewShouldApplyBackgroundColor() {
        val dynamicProperty = DynamicProperty(BACKGROUND_COLOR, "color", "#0000FF")

        val view = View(parent.context)
        backgroundColorCommand.apply(view, dynamicProperty)

        parent.addView(view)

        view.afterLayout {
            val viewBackgroundColor = view.background as ColorDrawable
            assertTrue(viewBackgroundColor.color == Color.parseColor(dynamicProperty.value))
        }
    }

    @Test
    fun renderingViewWithWrongBackgroundValueShouldThrow() {
        val dynamicProperty = DynamicProperty(BACKGROUND_COLOR, "color", "wrong")

        val view = View(parent.context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("The value (${dynamicProperty.value}) " +
            "cannot be parsed as a color")

        backgroundColorCommand.apply(view, dynamicProperty)
    }

    @Test
    fun renderingCardViewShouldApplyBackgroundColor() {
        val dynamicProperty = DynamicProperty(BACKGROUND_COLOR, "color", "#0000FF")

        val cardView = CardView(parent.context)
        backgroundColorCommand.apply(cardView, dynamicProperty)

        parent.addView(cardView)

        cardView.afterLayout {
            val viewBackgroundColor = cardView.background as ColorDrawable
            assertTrue(viewBackgroundColor.color == Color.parseColor(dynamicProperty.value))
        }
    }
}
