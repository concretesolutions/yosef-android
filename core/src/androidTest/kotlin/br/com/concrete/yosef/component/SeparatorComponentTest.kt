package br.com.concrete.yosef.component

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.LinearLayout
import br.com.concrete.yosef.activities.MainActivity
import br.com.concrete.yosef.api.component.SeparatorComponent
import br.com.concrete.yosef.api.property.color.BackgroundColorCommand.Companion.BACKGROUND_COLOR
import br.com.concrete.yosef.api.property.size.HeightCommand.Companion.HEIGHT_TYPE
import br.com.concrete.yosef.api.property.size.WidthCommand.Companion.WIDTH_TYPE
import br.com.concrete.yosef.entity.DynamicProperty
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.math.roundToInt


@RunWith(AndroidJUnit4::class)
class SeparatorComponentTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<MainActivity>(MainActivity::class.java)

    private val context = InstrumentationRegistry.getTargetContext()!!

    private lateinit var separator: SeparatorComponent

    @Before
    fun setUp() {
        separator = SeparatorComponent()
    }

    @Test
    fun whenAddSeparator_separatorShouldHaveTheCorrectProperties() {
        val widthProperty = DynamicProperty(WIDTH_TYPE, "dimen", "100")
        val heightProperty = DynamicProperty(HEIGHT_TYPE, "dimen", "1")
        val colorProperty = DynamicProperty(BACKGROUND_COLOR, "color", "#FFFFFF")
        val properties = listOf(widthProperty, heightProperty, colorProperty)
        val separateView = separator.createView(context)
        val expectedColor = Color.parseColor("#FFFFFF")
        val expectedHeightInPx: Int = (1 * context.resources.displayMetrics.density).roundToInt()
        val expectedWidthInPx: Int = (100 * context.resources.displayMetrics.density).roundToInt()

        separator.applyProperties(separateView, properties, null)

        assertEquals(expectedWidthInPx, separateView.layoutParams.width)
        assertEquals(expectedHeightInPx, separateView.layoutParams.height)
        assertEquals(expectedColor, (separateView.background as ColorDrawable).color)
    }
}
