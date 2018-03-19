package br.com.concrete.yosef.api.property.elementgroup

import android.view.View
import android.widget.LinearLayout
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Command class that implements the [DynamicPropertyCommand] applying
 * the orientation property to the view([LinearLayout])
 *
 * @see [LinearLayout.setOrientation]
 */
class OrientationCommand : DynamicPropertyCommand {

    companion object {
        /**
         * This constant documents which name is associated with the property
         */
        const val ORIENTATION = "orientation"
        /**
         * This constant represents the possible value horizontal for the orientation property
         */
        const val HORIZONTAL = "horizontal"
        /**
         * This constant represents the possible value vertical for the orientation property
         */
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
