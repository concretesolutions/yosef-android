package br.com.concrete.yosef.api.property.size

import android.view.View
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Command pattern for the width property
 */
class WidthCommand : DynamicPropertyCommand {

    companion object {
        const val WIDTH_TYPE = "width"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {
        view.layoutParams.width = dynamicProperty.value.toInt()
    }
}