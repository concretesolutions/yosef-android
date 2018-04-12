package br.com.concrete.yosef.property.elementList

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.widget.ImageView
import android.widget.ListView
import br.com.concrete.yosef.api.property.elementlist.SelectorColorCommand
import br.com.concrete.yosef.api.property.elementlist.SelectorColorCommand.Companion.SELECTOR_COLOR
import br.com.concrete.yosef.entity.DynamicProperty
import br.com.concrete.yosef.layoutAndAssert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SelectorColorCommandTest {

    @Rule
    @JvmField
    val exceptionRule: ExpectedException = ExpectedException.none()

    private val context = InstrumentationRegistry.getTargetContext()!!

    private lateinit var selectorColorCommand: SelectorColorCommand

    @Before
    fun setUp() {
        selectorColorCommand = SelectorColorCommand()
    }

    @Test
    fun whenSelectorColorPropertyApplied_shouldApplyTheCorrectColorToListView() {
        val dynamicProperty = DynamicProperty(SELECTOR_COLOR, "color", "#FF0000")

        val listView = ListView(context)
        selectorColorCommand.apply(listView, dynamicProperty)

        listView.layoutAndAssert {
            val viewSelectorColor = listView.selector as ColorDrawable
            assertTrue(viewSelectorColor.color == Color.parseColor("#FF0000"))
        }
    }

    @Test
    fun whenSelectorColorPropertyAppliedWithWrongValue_shouldThrowTheCorrectException() {
        val dynamicProperty = DynamicProperty(SELECTOR_COLOR, "color", "wrong")

        val listView = ListView(context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("The value (${dynamicProperty.value}) " +
            "cannot be parsed as a color")

        selectorColorCommand.apply(listView, dynamicProperty)
    }

    @Test
    fun whenSelectorColorPropertyAppliedToNotSupportedView_shouldThrowTheCorrectException() {
        val dynamicProperty = DynamicProperty(SELECTOR_COLOR, "color", "#FF0000")

        val imageView = ImageView(context)

        exceptionRule.expect(UnsupportedOperationException::class.java)
        exceptionRule.expectMessage("The $SELECTOR_COLOR property cannot be " +
            "applied to ImageView")

        selectorColorCommand.apply(imageView, dynamicProperty)
    }
}
