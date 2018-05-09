package br.com.concrete.yosef.property.size

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.widget.TextView
import br.com.concrete.yosef.api.property.size.AspectRatioCommand
import br.com.concrete.yosef.api.property.size.AspectRatioCommand.Companion.ASPECT_RATIO
import br.com.concrete.yosef.entity.DynamicProperty
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AspectRatioCommandTest {

    @Rule
    @JvmField
    val exceptionRule: ExpectedException = ExpectedException.none()

    private val context = InstrumentationRegistry.getTargetContext()!!

    private lateinit var aspectRatioCommand: AspectRatioCommand

    @Before
    fun setUp() {
        aspectRatioCommand = AspectRatioCommand()
    }

    @Test
    fun whenPropertyTypeIsWrong_shouldThrowCorrectException() {
        val dynamicProperty = DynamicProperty(ASPECT_RATIO, "WRONG", "16:9")
        val view = TextView(context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("Can't apply aspectRatio with type WRONG, unknown type.")

        aspectRatioCommand.apply(view, dynamicProperty)
    }

    @Test
    fun whenPropertyValueIsInvalid_shouldThrowCorrectException() {
        val dynamicProperty = DynamicProperty(ASPECT_RATIO, "aspectRatio", "INVALID")
        val view = TextView(context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("Can't apply aspectRatio with value INVALID, it does not apply the property format('16:9' for example).")

        aspectRatioCommand.apply(view, dynamicProperty)
    }
}
