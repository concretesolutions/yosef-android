package br.com.concrete.yosef.lottie

import android.view.View
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty
import br.com.concrete.yosef.isValidJson
import com.airbnb.lottie.LottieAnimationView

/**
 * Command pattern for the animate property
 */
class AnimatePropertyCommand : DynamicPropertyCommand {

    companion object {
        const val ANIMATE_PROPERTY = "animate"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {

        if (view is LottieAnimationView && dynamicProperty.value.isValidJson()) {
            view.setAnimationFromJson(dynamicProperty.value)
            view.playAnimation()
            return
        }

        throw IllegalArgumentException("Could not start animation in view " +
                "${view.javaClass.name}. Valid json: ${dynamicProperty.value.isValidJson()}.")
    }
}
