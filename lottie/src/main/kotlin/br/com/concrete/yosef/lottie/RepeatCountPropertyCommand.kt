package br.com.concrete.yosef.lottie

import android.view.View
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty
import com.airbnb.lottie.LottieAnimationView

/**
 * Command pattern for the animate property
 */
class RepeatCountPropertyCommand : DynamicPropertyCommand {

    companion object {
        const val REPEAT_COUNT_PROPERTY = "repeatCount"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {
        if (view is LottieAnimationView) {
            view.repeatCount = dynamicProperty.value.toInt()
        }
    }
}