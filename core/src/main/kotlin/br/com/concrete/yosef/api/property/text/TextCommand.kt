package br.com.concrete.yosef.api.property.text

import android.view.View
import android.widget.TextView
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty

/**
 *
 */
class TextCommand : DynamicPropertyCommand {

    companion object {
        const val TEXT: String = "text"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {
        if (view is TextView) {
            view.text = dynamicProperty.value
            return
        }
        throw IllegalArgumentException("The value (${dynamicProperty.value}) " +
                "for the $TEXT property is not compatible with ${view.javaClass.name}")
    }
}