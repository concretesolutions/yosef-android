package br.com.concrete.yosef.api.property.id

import android.view.View
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Command pattern for the id property
 */
class IdCommand : DynamicPropertyCommand {

    companion object {
        const val ID = "id"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {
        view.id = dynamicProperty.value.hashCode()
    }
}