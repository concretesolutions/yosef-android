package br.com.concrete.yosef.api.property.size

import android.view.View
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Command pattern for the height property
 */
class HeightCommand : DynamicPropertyCommand {

    companion object {
        const val HEIGHT_TYPE = "height"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {
        view.layoutParams.height = dynamicProperty.value.toInt()
    }
}