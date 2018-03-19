package br.com.concrete.yosef.api.property.size

import android.view.View
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Command class that implements the [DynamicPropertyCommand] applying
 * the height property to the view([View])
 */
class HeightCommand : DynamicPropertyCommand {

    companion object {
        /**
         * This constant documents which name is associated with the property
         */
        const val HEIGHT_TYPE = "height"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {
        view.layoutParams.height = dynamicProperty.value.toInt()
    }
}
