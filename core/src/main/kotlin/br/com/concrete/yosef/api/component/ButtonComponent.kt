package br.com.concrete.yosef.api.component

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.api.property.color.BackgroundColorCommand
import br.com.concrete.yosef.api.property.color.BackgroundColorCommand.Companion.BACKGROUND_COLOR
import br.com.concrete.yosef.api.property.id.IdCommand
import br.com.concrete.yosef.api.property.id.IdCommand.Companion.ID
import br.com.concrete.yosef.api.property.spacing.PaddingPropertyCommand
import br.com.concrete.yosef.api.property.spacing.PaddingPropertyCommand.Companion.PADDING
import br.com.concrete.yosef.api.property.text.TextColorCommand
import br.com.concrete.yosef.api.property.text.TextColorCommand.Companion.TEXT_COLOR
import br.com.concrete.yosef.api.property.text.TextCommand
import br.com.concrete.yosef.api.property.text.TextCommand.Companion.TEXT
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Class that implements the [Component] interface and creates the component
 * Button([Button]), applying its properties
 */
class ButtonComponent : Component {

    companion object {
        /**
         * This constant documents which type is associated with the component
         */
        const val BUTTON_TYPE = "button"
        private const val ACTION = "action"
    }

    private val commands: Map<String, DynamicPropertyCommand> = mapOf(
        TEXT to TextCommand(),
        TEXT_COLOR to TextColorCommand(),
        BACKGROUND_COLOR to BackgroundColorCommand(),
        PADDING to PaddingPropertyCommand(),
        ID to IdCommand()
    )

    override fun applyProperties(
        view: View,
        dynamicProperties: List<DynamicProperty>,
        actionListener: OnActionListener?
    ) {
        (view as Button).setOnClickListener {
            val dynamicWithAction = dynamicProperties.find { ACTION == it.name }
            dynamicWithAction?.value?.let { actionListener?.callAction(it) }
        }
        dynamicProperties.forEach {
            commands[it.name]?.apply(view, it)
        }
    }

    override fun createView(context: Context): View {
        return Button(context).apply {
            this.layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        }
    }
}
