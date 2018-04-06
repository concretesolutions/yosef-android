package br.com.concrete.yosef.api.property.elementlist

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.LinearLayout
import android.widget.ListView
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Command class that implements the [DynamicPropertyCommand] applying
 * a color to the selector property of the view([ListView])
 *
 * @see [LinearLayout.setOrientation]
 */
class SelectorColorCommand : DynamicPropertyCommand {

    companion object {
        /**
         * This constant documents which name is associated with the property
         */
        const val SELECTOR_COLOR = "selectorColor"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {

        val color: Int

        try {
            color = Color.parseColor(dynamicProperty.value)
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("The value (${dynamicProperty.value}) " +
                "cannot be parsed as a color")
        }

        (view as ListView).selector = ColorDrawable(color)
    }
}
