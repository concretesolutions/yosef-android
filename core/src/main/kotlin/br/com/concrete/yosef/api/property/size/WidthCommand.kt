package br.com.concrete.yosef.api.property.size

import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.dp
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
        const val MATCH = "match"
        const val WRAP = "wrap"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {

        if (dynamicProperty.type == "string") {
            when (dynamicProperty.type) {
                MATCH -> view.layoutParams.width = MATCH_PARENT
                WRAP -> view.layoutParams.width = WRAP_CONTENT
                else -> throw IllegalArgumentException("Can't apply ${dynamicProperty.name}" +
                    " with value ${dynamicProperty.value}.")
            }
        } else if (dynamicProperty.type == "dimen") {
            view.layoutParams.width = dynamicProperty
                .value
                .toInt()
                .dp(view.context)
        }

        throw IllegalArgumentException("Can't apply ${dynamicProperty.name}" +
            " with type ${dynamicProperty.type} and value ${dynamicProperty.value}.")
    }
}
