package br.com.concrete.yosef.api.property.color

import android.graphics.Color
import android.support.v7.widget.CardView
import android.view.View
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Command pattern for the background color property
 */
class BackgroundColorCommand : DynamicPropertyCommand {

    companion object {
        const val BACKGROUND_COLOR = "backgroundColor"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {

        val color: Int

        try {
            color = Color.parseColor(dynamicProperty.value)
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("The value (${dynamicProperty.value}) " +
                "cannot be parsed as a color")
        }

        if (view is CardView) {
            view.setCardBackgroundColor(color)
        } else {
            view.setBackgroundColor(color)
        }
    }
}