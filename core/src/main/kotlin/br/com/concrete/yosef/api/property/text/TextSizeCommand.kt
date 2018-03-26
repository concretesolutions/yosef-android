package br.com.concrete.yosef.api.property.text

import android.view.View
import android.widget.TextView
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Command class that implements the [DynamicPropertyCommand] applying
 * the textSize property to the view([TextView])
 *
 * @see [TextView.setTextSize]
 */
class TextSizeCommand : DynamicPropertyCommand {

    companion object {
        /**
         * This constant documents which name is associated with the property
         */
        const val TEXT_SIZE = "textSize"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {
        if (view is TextView) {
            view.textSize = dynamicProperty.value.toFloat()
            return
        }
        throw IllegalArgumentException("The property does not support " +
            "setting text size for the type ${view.javaClass.name}")
    }
}
