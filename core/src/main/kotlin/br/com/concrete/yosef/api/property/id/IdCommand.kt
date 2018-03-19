package br.com.concrete.yosef.api.property.id

import android.view.View
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Command class that implements the [DynamicPropertyCommand] applying
 * the id property to the view([View])
 *
 * @see [View.setId]
 */
class IdCommand : DynamicPropertyCommand {

    companion object {
        /**
         * This constant documents which name is associated with the property
         */
        const val ID = "id"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {
        view.id = dynamicProperty.value.hashCode()
    }
}