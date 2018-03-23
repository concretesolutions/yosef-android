package br.com.concrete.yosef.api.property.elementgroup

import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty
import kotlin.math.roundToInt


/**
 * Command class that implements the [DynamicPropertyCommand] applying
 * a horizontal margin to the viewGroup([LinearLayout])
 */
class HorizontalMarginCommand : DynamicPropertyCommand {

    companion object {
        /**
         * This constant documents which name is associated with the property
         */
        const val HORIZONTAL_MARGIN = "horizontalMargin"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {
        if (view is LinearLayout) {

            val value = dynamicProperty.value.toFloat()
            val valueInPx: Int = (value * view.getResources().displayMetrics.density).roundToInt()

            val layoutParams = view.layoutParams

            if (layoutParams is LinearLayout.LayoutParams) {
                layoutParams.leftMargin = valueInPx
                layoutParams.rightMargin = valueInPx
            } else if (layoutParams is FrameLayout.LayoutParams) {
                layoutParams.leftMargin = valueInPx
                layoutParams.rightMargin = valueInPx
            }

            view.layoutParams = layoutParams

            return
        }
        throw IllegalArgumentException("The property does not support " +
            "setting $HORIZONTAL_MARGIN for the type ${view.javaClass.name}")
    }
}