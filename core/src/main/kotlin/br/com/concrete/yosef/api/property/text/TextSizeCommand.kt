package br.com.concrete.yosef.api.property.text

import android.view.View
import android.widget.TextView
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Command pattern for the text size property
 */
class TextSizeCommand : DynamicPropertyCommand {

    companion object {
        const val TEXT_SIZE = "textSize"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {
        if (view is TextView) {
            view.textSize = dynamicProperty.value.toFloat()
            return
        }
        throw IllegalArgumentException("The property does not support setting text size for the type ${view.javaClass.name}")
    }

}