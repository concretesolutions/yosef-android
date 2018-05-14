package br.com.concrete.yosef.api.property.color

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.support.v7.widget.CardView
import android.util.Log
import android.view.View
import android.widget.ImageView
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Command class that implements the [DynamicPropertyCommand] applying
 * the colorFilter property to the view([ImageView])
 *
 * @see [ImageView.setColorFilter]
 */
class ColorFilterCommand : DynamicPropertyCommand {

    companion object {
        /**
         * This constant documents which name is associated with the property
         */
        const val COLOR_FILTER = "colorFilter"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {

        val color: Int

        try {
            color = Color.parseColor(dynamicProperty.value)
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("The value (${dynamicProperty.value}) " +
                    "cannot be parsed as a color")
        }


        if (view is ImageView) {
            view.setColorFilter(color)
        } else {
            throw IllegalArgumentException(
                "The value (${dynamicProperty.value}) for the $COLOR_FILTER" +
                        " property is not compatible with ${view.javaClass.name}"
            )
        }
    }
}
