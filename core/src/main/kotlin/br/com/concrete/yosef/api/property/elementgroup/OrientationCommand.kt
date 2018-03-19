package br.com.concrete.yosef.api.property.elementgroup

import android.view.View
import android.widget.LinearLayout
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Command pattern for the orientation color property
 */
class OrientationCommand : DynamicPropertyCommand {

    companion object {
        const val ORIENTATION = "orientation"
        const val HORIZONTAL = "horizontal"
        const val VERTICAL = "vertical"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {

        if (!(dynamicProperty.value == HORIZONTAL ||
                dynamicProperty.value == VERTICAL))
            throw IllegalArgumentException("The value (${dynamicProperty.value}) " +
                "cannot be applied to property $ORIENTATION.")

        if (view is LinearLayout) {

            if (HORIZONTAL == dynamicProperty.value) {
                view.orientation = LinearLayout.HORIZONTAL
            } else {
                view.orientation = LinearLayout.VERTICAL
            }
            return
        }

        throw IllegalArgumentException("The $ORIENTATION property cannot be " +
            "applied to view ${view.javaClass.name}")
    }
}