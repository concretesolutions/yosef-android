package br.com.concrete.yosef.api.property.spacing

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.dp
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Command class that implements the [DynamicPropertyCommand] applying
 * margin to a view
 *
 * @see [LinearLayout.LayoutParams.setMargins] or [FrameLayout.LayoutParams.setMargins]
 */
class MarginPropertyCommand : DynamicPropertyCommand {

    companion object {
        /**
         * Name of the property that can be used in the json
         */
        const val MARGIN = "margin"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {
        val layoutParams = view.layoutParams

        val (left, top, right, bottom) = generateMarginList(dynamicProperty, view)

        if (layoutParams is LinearLayout.LayoutParams) {
            layoutParams.setMargins(left, top, right, bottom)
        } else if (layoutParams is FrameLayout.LayoutParams) {
            layoutParams.setMargins(left, top, right, bottom)
        }
    }

    private fun generateMarginList(dynamicProperty: DynamicProperty, view: View): List<Int> {
        return if (dynamicProperty.value.contains(",")) {
            dynamicProperty.value
                .split(",")
                .map {
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
            throw IllegalArgumentException("The value $valueInString is not a valid margin" +
                " value, it need to be a number")
        }
    }
}
