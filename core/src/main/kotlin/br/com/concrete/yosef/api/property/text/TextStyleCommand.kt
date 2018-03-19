package br.com.concrete.yosef.api.property.text

import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Command pattern for the text style property
 */
class TextStyleCommand : DynamicPropertyCommand {

    companion object {
        const val TEXT_STYLE = "textStyle"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {
        if (view is TextView) {
            when (dynamicProperty.value) {
                "bold" -> view.setTypeface(view.typeface, Typeface.BOLD)
                "italic" -> view.setTypeface(view.typeface, Typeface.ITALIC)
                "boldItalic" -> view.setTypeface(view.typeface, Typeface.BOLD_ITALIC)
                else -> {
                    throw IllegalArgumentException("The value(${dynamicProperty.value}) " +
                        "for the $TEXT_STYLE property does not exist or is not supported")
                }
            }
        }
    }
}