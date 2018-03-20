package br.com.concrete.yosef.api.property.size

import android.view.View
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Command class that implements the [DynamicPropertyCommand] applying
 * the width property to the view([View])
 */
class WidthCommand : DynamicPropertyCommand {

    companion object {
        /**
         * This constant documents which name is associated with the property
         */
        const val WIDTH_TYPE = "width"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {
        view.layoutParams.width = dynamicProperty.value.toInt()
    }
}
