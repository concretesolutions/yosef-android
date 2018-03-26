package br.com.concrete.yosef.property.frame

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import br.com.concrete.yosef.api.property.frame.GravityCommand
import br.com.concrete.yosef.api.property.frame.GravityCommand.Companion.BOTTOM
import br.com.concrete.yosef.api.property.frame.GravityCommand.Companion.CENTER
import br.com.concrete.yosef.api.property.frame.GravityCommand.Companion.GRAVITY
import br.com.concrete.yosef.api.property.frame.GravityCommand.Companion.TOP
import br.com.concrete.yosef.entity.DynamicProperty
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GravityCommandTest {

    @Rule
    @JvmField
    val exceptionRule: ExpectedException = ExpectedException.none()

    private val context = InstrumentationRegistry.getTargetContext()!!

    private lateinit var gravityCommand: GravityCommand

    private lateinit var parent: FrameLayout

    @Before
    fun setUp() {
        parent = FrameLayout(context)
        gravityCommand = GravityCommand()
    }

    @After
    fun clean() {
        parent.removeAllViews()
    }

    @Test
    fun whenSetBottomGravityProperty_shouldApplyItToTheView() {
        val dynamicProperty = DynamicProperty(GRAVITY, "dimen", BOTTOM)
        val linearLayout = LinearLayout(parent.context)

        parent.addView(linearLayout)
        gravityCommand.apply(linearLayout, dynamicProperty)

        assertTrue(linearLayout.layoutParams is FrameLayout.LayoutParams)
        assertTrue((linearLayout.layoutParams as FrameLayout.LayoutParams).gravity == Gravity.BOTTOM)
    }

    @Test
    fun whenSetTopGravityProperty_shouldApplyItToTheView() {
        val dynamicProperty = DynamicProperty(GRAVITY, "dimen", TOP)
        val linearLayout = LinearLayout(parent.context)

        parent.addView(linearLayout)
        gravityCommand.apply(linearLayout, dynamicProperty)

        assertTrue(linearLayout.layoutParams is FrameLayout.LayoutParams)
        assertTrue((linearLayout.layoutParams as FrameLayout.LayoutParams).gravity == Gravity.TOP)
    }

    @Test
    fun whenSetCenterGravityProperty_shouldApplyItToTheView() {
        val dynamicProperty = DynamicProperty(GRAVITY, "dimen", CENTER)
        val linearLayout = LinearLayout(parent.context)

        parent.addView(linearLayout)
        gravityCommand.apply(linearLayout, dynamicProperty)

        assertTrue(linearLayout.layoutParams is FrameLayout.LayoutParams)
        assertTrue((linearLayout.layoutParams as FrameLayout.LayoutParams).gravity == Gravity.CENTER)
    }

    @Test
    fun renderingLinearLayoutWithWrongOrientationValueShouldThrow() {
        val dynamicProperty = DynamicProperty(GRAVITY, "dimen", "42")
        val linearLayout = LinearLayout(parent.context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("The value (42) " +
                "cannot be applied to property ${GravityCommand.GRAVITY}.")

        gravityCommand.apply(linearLayout, dynamicProperty)
    }

    @Test
    fun renderingImageViewShouldThrow() {
        val dynamicProperty = DynamicProperty(GRAVITY, "dimen", CENTER)
        val imageView = ImageView(parent.context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("The ${GravityCommand.GRAVITY} property cannot be applied " +
                "to view ${imageView.javaClass.name}")

        gravityCommand.apply(imageView, dynamicProperty)
    }
}
