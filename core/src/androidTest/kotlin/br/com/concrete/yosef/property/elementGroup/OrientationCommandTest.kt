package br.com.concrete.yosef.property.elementGroup

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.widget.ImageView
import android.widget.LinearLayout
import br.com.concrete.yosef.api.property.elementgroup.OrientationCommand
import br.com.concrete.yosef.api.property.elementgroup.OrientationCommand.Companion.HORIZONTAL
import br.com.concrete.yosef.api.property.elementgroup.OrientationCommand.Companion.ORIENTATION
import br.com.concrete.yosef.api.property.elementgroup.OrientationCommand.Companion.VERTICAL
import br.com.concrete.yosef.entity.DynamicProperty
import br.com.concrete.yosef.layoutAndAssert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OrientationCommandTest {

    @Rule
    @JvmField
    val exceptionRule: ExpectedException = ExpectedException.none()

    private val context = InstrumentationRegistry.getTargetContext()!!

    private lateinit var orientationCommand: OrientationCommand

    @Before
    fun setUp() {
        orientationCommand = OrientationCommand()
    }

    @Test
    fun renderingLinearLayoutShouldApplyHorizontalOrientation() {
        val dynamicProperty = DynamicProperty(ORIENTATION, "dimen", HORIZONTAL)

        val linearLayout = LinearLayout(context)
        orientationCommand.apply(linearLayout, dynamicProperty)

        linearLayout.layoutAndAssert {
            assertTrue(linearLayout.orientation == LinearLayout.HORIZONTAL)
        }
    }

    @Test
    fun renderingLinearLayoutShouldApplyVerticalOrientation() {
        val dynamicProperty = DynamicProperty(ORIENTATION, "dimen", VERTICAL)

        val linearLayout = LinearLayout(context)
        orientationCommand.apply(linearLayout, dynamicProperty)

        linearLayout.layoutAndAssert {
            assertTrue(linearLayout.orientation == LinearLayout.VERTICAL)
        }
    }

    @Test
    fun renderingLinearLayoutWithWrongOrientationValueShouldThrow() {
        val dynamicProperty = DynamicProperty(ORIENTATION, "dimen", "wrong")

        val linearLayout = LinearLayout(context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("The value (${dynamicProperty.value}) " +
            "cannot be applied to property $ORIENTATION.")

        orientationCommand.apply(linearLayout, dynamicProperty)
    }

    @Test
    fun renderingImageViewShouldThrow() {
        val dynamicProperty = DynamicProperty(ORIENTATION, "dimen", HORIZONTAL)

        val imageView = ImageView(context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("The $ORIENTATION property cannot be applied " +
            "to view ${imageView.javaClass.name}")

        orientationCommand.apply(imageView, dynamicProperty)
    }
}
