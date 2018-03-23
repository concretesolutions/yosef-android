package br.com.concrete.yosef.api.property.elementgroup

import android.view.View
import android.widget.LinearLayout
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty
import kotlin.math.roundToInt


/**
 * Command class that implements the [DynamicPropertyCommand] applying
 * a space between all children of the view([LinearLayout])
 */
class SpacingCommand : DynamicPropertyCommand {

    companion object {
        /**
         * This constant documents which name is associated with the property
         */
        const val SPACING = "spacing"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {
        if (view is LinearLayout) {

            val value = dynamicProperty.value.toFloat()
            val valueInPx: Int = (value * view.getResources().displayMetrics.density).roundToInt()

            adjustMarginForAllChildren(view, valueInPx)

            return
        }
        throw IllegalArgumentException("The property does not support " +
            "setting $SPACING for the type ${view.javaClass.name}")
    }

    private fun adjustMarginForAllChildren(view: LinearLayout, valueInPx: Int) {
        for (i in 0 until view.childCount) {
            val child = view.getChildAt(i)
            val layoutParams = child.layoutParams
            if (layoutParams is LinearLayout.LayoutParams && i != 0) {
                applyCorrespondingSpacingToLayoutParams(view, layoutParams, valueInPx)
                child.layoutParams = layoutParams
            }
        }
    }

    private fun applyCorrespondingSpacingToLayoutParams(view: LinearLayout, layoutParams: LinearLayout.LayoutParams, valueInPx: Int) {
        if (view.orientation == LinearLayout.VERTICAL) {
            layoutParams.setMargins(0, valueInPx, 0, 0)
        } else if (view.orientation == LinearLayout.HORIZONTAL) {
            layoutParams.setMargins(valueInPx, 0, 0, 0)
        }
    }
}