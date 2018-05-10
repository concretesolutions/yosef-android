package br.com.concrete.yosef.api.property.size

import android.view.View
import android.view.ViewGroup
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.api.property.size.WidthCommand.Companion.MATCH
import br.com.concrete.yosef.api.property.size.WidthCommand.Companion.WRAP
import br.com.concrete.yosef.dp
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
        if (dynamicProperty.type == "dimenSpec") {
            when (dynamicProperty.value) {
                MATCH -> view.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
                WRAP -> view.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                else -> throw IllegalArgumentException("Can't apply ${dynamicProperty.name}" +
                    " with value ${dynamicProperty.value}.")
            }
        } else if (dynamicProperty.type == "dimen") {
            view.layoutParams.height = dynamicProperty
                .value
                .toInt()
                .dp(view.context)
        } else throw IllegalArgumentException("Can't apply ${dynamicProperty.name}" +
            " with type ${dynamicProperty.type} and value ${dynamicProperty.value}.")
    }
}
