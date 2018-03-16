package br.com.concrete.yosef.api.component

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.api.property.color.BackgroundColorCommand
import br.com.concrete.yosef.api.property.color.BackgroundColorCommand.Companion.BACKGROUND_COLOR
import br.com.concrete.yosef.api.property.id.IdCommand
import br.com.concrete.yosef.api.property.id.IdCommand.Companion.ID
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
    }

    private val ACTION = "action"

    private val commands: Map<String, DynamicPropertyCommand> = mapOf(
            TEXT to TextCommand(),
            TEXT_COLOR to TextColorCommand(),
            BACKGROUND_COLOR to BackgroundColorCommand(),
            ID to IdCommand()
    )

    override fun applyProperties(view: View, dynamicProperties: List<DynamicProperty>, actionListener: OnActionListener?) {
        (view as Button).setOnClickListener {
            val dynamicWithAction = dynamicProperties.find { ACTION == it.name }
            dynamicWithAction?.value?.let { actionListener?.callAction(it) }
        }
        dynamicProperties.forEach {
            commands[it.name]?.apply(view, it)
        }
    }

    override fun createView(parent: ViewGroup): View {
        return Button(parent.context).apply {
            if (parent is LinearLayout && parent.tag == ElementGroupComponent.ELEMENT_GROUP) {
                this.layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f)
            } else {
                this.layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            }
        }
    }

}
