package br.com.concrete.yosef.property.elementGroup

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import br.com.concrete.yosef.api.property.elementgroup.SpacingCommand
import br.com.concrete.yosef.api.property.elementgroup.SpacingCommand.Companion.SPACING
import br.com.concrete.yosef.entity.DynamicProperty
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import kotlin.math.roundToInt

@RunWith(AndroidJUnit4::class)
class SpacingCommandTest {

    @Rule
    @JvmField
    val exceptionRule: ExpectedException = ExpectedException.none()

    private val context = InstrumentationRegistry.getTargetContext()!!

    private lateinit var spacingCommand: SpacingCommand

    @Before
    fun setUp() {
        spacingCommand = SpacingCommand()
    }

    @Test
    fun whenSpacingApplied_shouldNotApplyToTheFirstChildView() {
        val dynamicProperty = DynamicProperty(SPACING, "dimen", "16")
        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        val firstChildView = View(context)
        linearLayout.addView(firstChildView)

        spacingCommand.apply(linearLayout, dynamicProperty)

        assertTrue((firstChildView.layoutParams as LinearLayout.LayoutParams).leftMargin == 0)
        assertTrue((firstChildView.layoutParams as LinearLayout.LayoutParams).topMargin == 0)
        assertTrue((firstChildView.layoutParams as LinearLayout.LayoutParams).rightMargin == 0)
        assertTrue((firstChildView.layoutParams as LinearLayout.LayoutParams).bottomMargin == 0)
    }

    @Test
    fun whenSpacingApplied_shouldApplyToTheSecondChildViewAndSoOn() {
        val dynamicProperty = DynamicProperty(SPACING, "dimen", "16")
        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        val secondChildView = View(context)
        val thirdChildView = View(context)
        linearLayout.addView(View(context))
        linearLayout.addView(secondChildView)
        linearLayout.addView(thirdChildView)
        val valueInPx: Int = (16 * context.resources.displayMetrics.density).roundToInt()

        spacingCommand.apply(linearLayout, dynamicProperty)

        assertTrue((secondChildView.layoutParams as LinearLayout.LayoutParams).leftMargin == 0)
        assertTrue((secondChildView.layoutParams as LinearLayout.LayoutParams).topMargin == valueInPx)
        assertTrue((secondChildView.layoutParams as LinearLayout.LayoutParams).rightMargin == 0)
        assertTrue((secondChildView.layoutParams as LinearLayout.LayoutParams).bottomMargin == 0)

        assertTrue((thirdChildView.layoutParams as LinearLayout.LayoutParams).leftMargin == 0)
        assertTrue((secondChildView.layoutParams as LinearLayout.LayoutParams).topMargin == valueInPx)
        assertTrue((secondChildView.layoutParams as LinearLayout.LayoutParams).rightMargin == 0)
        assertTrue((secondChildView.layoutParams as LinearLayout.LayoutParams).bottomMargin == 0)
    }

    @Test
    fun renderingImageViewShouldThrow() {
        val dynamicProperty = DynamicProperty(SpacingCommand.SPACING, "dimen", "16")
        val imageView = ImageView(context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("The property does not support setting " +
            "${SpacingCommand.SPACING} for the type ${imageView.javaClass.name}")

        spacingCommand.apply(imageView, dynamicProperty)
    }
}