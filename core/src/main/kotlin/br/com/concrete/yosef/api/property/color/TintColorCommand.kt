package br.com.concrete.yosef.api.property.color

import android.R
import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.widget.CompoundButton
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty
import br.com.concrete.yosef.supportsLollipop

/**
 * Command pattern for the tint color property
 */
class TintColorCommand : DynamicPropertyCommand {

    companion object {
        const val TINT_COLOR = "buttonTintColor"
    }

    @SuppressLint("NewApi")
    override fun apply(view: View, dynamicProperty: DynamicProperty) {

        val color: Int

        try {
            color = Color.parseColor(dynamicProperty.value)
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("The value (${dynamicProperty.value}) " +
                    "cannot be parsed as a color")
        }

        if (view is CompoundButton) {

            val handled = supportsLollipop {
                val colorStateList = ColorStateList(arrayOf(intArrayOf(
                        R.attr.state_enabled)), intArrayOf(color))
                view.buttonTintList = colorStateList
            }

            if (!handled)
                view.highlightColor = color

            return
        }

        throw IllegalArgumentException("The value (${dynamicProperty.value}) " +
                "for the $TINT_COLOR property is not compatible with ${view.javaClass.name}")


    }

}
