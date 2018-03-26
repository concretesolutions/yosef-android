package br.com.concrete.yosef.api.property.frame

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.FrameLayout.LayoutParams
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
         * This constant represents the possible value left for the gravity property
         */
        const val LEFT = "left"
        /**
         * This constant represents the possible value top for the gravity property
         */
        const val TOP = "top"
        /**
         * This constant represents the possible value right for the gravity property
         */
        const val RIGHT = "right"
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

        if (!(dynamicProperty.value == LEFT ||
                dynamicProperty.value == TOP ||
                dynamicProperty.value == RIGHT ||
                dynamicProperty.value == BOTTOM ||
                dynamicProperty.value == CENTER))
            throw IllegalArgumentException("The value (${dynamicProperty.value}) " +
                "cannot be applied to property $GRAVITY.")

        if (view is LinearLayout) {
            val layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            when (dynamicProperty.value) {
                LEFT -> layoutParams.gravity = Gravity.LEFT
                TOP -> layoutParams.gravity = Gravity.TOP
                RIGHT -> layoutParams.gravity = Gravity.RIGHT
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
