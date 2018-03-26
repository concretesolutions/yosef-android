package br.com.concrete.yosef.lottie

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.component.Component
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.api.property.id.IdCommand
import br.com.concrete.yosef.api.property.id.IdCommand.Companion.ID
import br.com.concrete.yosef.api.property.size.HeightCommand
import br.com.concrete.yosef.api.property.size.HeightCommand.Companion.HEIGHT_TYPE
import br.com.concrete.yosef.api.property.size.WidthCommand
import br.com.concrete.yosef.api.property.size.WidthCommand.Companion.WIDTH_TYPE
import br.com.concrete.yosef.entity.DynamicProperty
import br.com.concrete.yosef.lottie.AnimatePropertyCommand.Companion.ANIMATE_PROPERTY
import br.com.concrete.yosef.lottie.RepeatCountPropertyCommand.Companion.REPEAT_COUNT_PROPERTY
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable

/**
 * Command pattern for the Animation component
 */

class LottieAnimationComponent : Component {

    companion object {
        const val ANIMATION_TYPE = "animation"
    }

    private val commands: Map<String, DynamicPropertyCommand> = mapOf(
        ID to IdCommand(),
        WIDTH_TYPE to WidthCommand(),
        HEIGHT_TYPE to HeightCommand(),
        REPEAT_COUNT_PROPERTY to RepeatCountPropertyCommand(),
        ANIMATE_PROPERTY to AnimatePropertyCommand()
    )

    override fun applyProperties(
        view: View,
        dynamicProperties: List<DynamicProperty>,
        actionListener: OnActionListener?
    ) {
        checkMandatoryFields(dynamicProperties, ANIMATE_PROPERTY)

        dynamicProperties.forEach {

            if (commands[it.name] == null)
                throw IllegalArgumentException("Property ${it.name} (value = ${it.value}) " +
                    "cannot be applied to ${view.javaClass.name} ($ANIMATION_TYPE type)")

            commands[it.name]?.apply(view, it)
        }
    }

    override fun createView(context: Context): LottieAnimationView {
        return LottieAnimationView(context).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            repeatCount = LottieDrawable.INFINITE
        }
    }
}
