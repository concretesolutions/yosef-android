package br.com.concrete.yosef.property.spacing

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.widget.LinearLayout
import android.widget.TextView
import br.com.concrete.yosef.api.property.spacing.MarginPropertyCommand
import br.com.concrete.yosef.api.property.spacing.MarginPropertyCommand.Companion.MARGIN
import br.com.concrete.yosef.entity.DynamicProperty
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import kotlin.math.roundToInt

@RunWith(AndroidJUnit4::class)
class MarginCommandTest {

    @Rule
    @JvmField
    val exceptionRule: ExpectedException = ExpectedException.none()

    private val context = InstrumentationRegistry.getTargetContext()!!

    private lateinit var marginCommand: MarginPropertyCommand

    @Before
    fun setUp() {
        marginCommand = MarginPropertyCommand()
    }

    @Test
    fun whenMarginIsSetToView_shouldSetTheCorrectValues() {
        val dynamicProperty = DynamicProperty(MARGIN, "dimen", "16, 0, 16, 0")
        val parent = LinearLayout(context)
        val view = TextView(context)
        parent.addView(view)
        val valueInPx: Int = (16 * context.resources.displayMetrics.density).roundToInt()

        marginCommand.apply(view, dynamicProperty)

        val layoutParams = view.layoutParams as LinearLayout.LayoutParams
        assertEquals(valueInPx, layoutParams.leftMargin)
        assertEquals(0, layoutParams.topMargin)
        assertEquals(valueInPx, layoutParams.rightMargin)
        assertEquals(0, layoutParams.bottomMargin)
    }

    @Test
    fun whenMarginIsSetPassingOneValue_shouldSetTheSameValueToAllMargins() {
        val dynamicProperty = DynamicProperty(MARGIN, "dimen", "16")
        val parent = LinearLayout(context)
        val view = TextView(context)
        parent.addView(view)
        val valueInPx: Int = (16 * context.resources.displayMetrics.density).roundToInt()

        marginCommand.apply(view, dynamicProperty)

        val layoutParams = view.layoutParams as LinearLayout.LayoutParams
        assertEquals(valueInPx, layoutParams.leftMargin)
        assertEquals(valueInPx, layoutParams.topMargin)
        assertEquals(valueInPx, layoutParams.rightMargin)
        assertEquals(valueInPx, layoutParams.bottomMargin)
    }
}
