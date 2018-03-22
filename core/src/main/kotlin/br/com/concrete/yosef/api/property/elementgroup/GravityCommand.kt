package br.com.concrete.yosef.api.property.elementgroup

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Command class that implements the [DynamicPropertyCommand] applying
 * the gravity property to the view([LinearLayout])
 *
 * @see [FrameLayout.LayoutParams.gravity]
 */
internal class GravityCommand : DynamicPropertyCommand {

    companion object {
        /**
         * This constant documents which name is associated with the property
         */
        const val GRAVITY = "gravity"
        /**
         * This constant represents the possible value top for the gravity property
         */
        const val TOP = "top"
        /**
         * This constant represents the possible value bottom for the gravity property
         */
        const val BOTTOM = "bottom"
        /**
         * This constant represents the possible value center for the gravity property
         */
        const val CENTER = "center"
    }

    @SuppressLint("RtlHardcoded")
    override fun apply(view: View, dynamicProperty: DynamicProperty) {

        if (!(dynamicProperty.value == TOP ||
                        dynamicProperty.value == BOTTOM ||
                        dynamicProperty.value == CENTER))
            throw IllegalArgumentException("The value (${dynamicProperty.value}) " +
                    "cannot be applied to property $GRAVITY.")

        if (view is LinearLayout) {
            val layoutParams = FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            when (dynamicProperty.value) {
                TOP -> layoutParams.gravity = Gravity.TOP
                BOTTOM -> layoutParams.gravity = Gravity.BOTTOM
                CENTER -> layoutParams.gravity = Gravity.CENTER
            }
            view.layoutParams = layoutParams

            return
        }

        throw IllegalArgumentException("The $GRAVITY property cannot be " +
                "applied to view ${view.javaClass.name}")
    }
}
