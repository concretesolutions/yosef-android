package br.com.concrete.yosef.ui.property.elementGroup

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import br.com.concrete.yosef.afterLayout
import br.com.concrete.yosef.api.property.elementgroup.OrientationCommand
import br.com.concrete.yosef.api.property.elementgroup.OrientationCommand.Companion.ORIENTATION
import br.com.concrete.yosef.api.property.elementgroup.OrientationCommand.Companion.HORIZONTAL
import br.com.concrete.yosef.entity.DynamicProperty
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

    private lateinit var parent: ViewGroup

    @Before
    fun setUp() {
        parent = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }
        orientationCommand = OrientationCommand()
    }

    @Test
    fun renderingLinearLayoutShouldApplyOrientation() {
        val dynamicProperty = DynamicProperty(ORIENTATION, "dimen", HORIZONTAL)

        val linearLayout = LinearLayout(parent.context)
        orientationCommand.apply(linearLayout, dynamicProperty)

        parent.addView(linearLayout)

        linearLayout.afterLayout {
            assertTrue(linearLayout.orientation == LinearLayout.HORIZONTAL)
        }

    }

    @Test
    fun renderingLinearLayoutWithWrongOrientationValueShouldThrow() {
        val dynamicProperty = DynamicProperty(ORIENTATION, "dimen", "wrong")

        val linearLayout = LinearLayout(parent.context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("The value (${dynamicProperty.value}) " +
                "cannot be applied to property $ORIENTATION.")

        orientationCommand.apply(linearLayout, dynamicProperty)

    }

    @Test
    fun renderingImageViewShouldThrow() {
        val dynamicProperty = DynamicProperty(ORIENTATION, "dimen", HORIZONTAL)

        val imageView = ImageView(parent.context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("The $ORIENTATION property cannot be applied " +
                "to view ${imageView.javaClass.name}")

        orientationCommand.apply(imageView, dynamicProperty)
    }

}