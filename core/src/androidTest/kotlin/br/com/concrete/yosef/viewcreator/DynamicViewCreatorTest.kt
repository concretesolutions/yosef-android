package br.com.concrete.yosef.viewcreator

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.view.ViewGroup
import android.widget.LinearLayout
import br.com.concrete.yosef.api.DynamicViewCreator
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DynamicViewCreatorTest {

    @Rule
    @JvmField
    val exceptionRule: ExpectedException = ExpectedException.none()

    private val context = InstrumentationRegistry.getTargetContext()!!

    private lateinit var parent: ViewGroup

    private lateinit var dynamicViewCreator: DynamicViewCreator

    @Before
    fun setUp() {
        parent = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }
        dynamicViewCreator = DynamicViewCreator.Builder().build()
    }

    @Test
    fun creatingViewWithUnknownTypeShouldThrowException() {
        val json = context.assets.open("example_unknown_type.json")
            .bufferedReader()
            .use { it.readText() }

        exceptionRule.expect(IllegalStateException::class.java)
        exceptionRule.expectMessage("There are no components registered " +
            "in this ViewCreator that can render unknown")

        dynamicViewCreator.createViewFromJson(parent, json, null)
    }
}
