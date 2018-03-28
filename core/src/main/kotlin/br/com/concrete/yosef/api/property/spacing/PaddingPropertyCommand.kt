package br.com.concrete.yosef.api.property.spacing

import android.content.Context
import android.view.View
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.dp
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Command class that implements the [DynamicPropertyCommand] applying
 * padding to a view view
 *
 * @see [View.setPadding]
 */
class PaddingPropertyCommand : DynamicPropertyCommand {

    companion object {
        /**
         * Name of the property that can be used in the json
         */
        const val PADDING = "padding"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {

        val (left, top, right, bottom) = generatePaddingList(dynamicProperty, view)

        view.setPadding(left, top, right, bottom)
    }

    private fun generatePaddingList(dynamicProperty: DynamicProperty, view: View): List<Int> {
        return if (dynamicProperty.value.contains(",")) {
            val split = dynamicProperty.value.split(",")

            if (split.size != 4) {
                throw IllegalArgumentException("The padding value must be an array of 4 items or " +
                    "a single number representing the values of all corners")
            }

            split.map {
                convertValueToDp(it, view.context)
            }
        } else {
            val value = convertValueToDp(dynamicProperty.value, view.context)
            MutableList(4) { value }
        }
    }

    private fun convertValueToDp(valueInString: String, context: Context): Int {
        try {
            return valueInString.trim().toInt().dp(context)
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("The value $valueInString is not a valid padding" +
                " value, it needs to be a number")
        }
    }
}
