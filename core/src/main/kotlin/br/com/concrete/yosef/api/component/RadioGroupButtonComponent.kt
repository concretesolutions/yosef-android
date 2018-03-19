package br.com.concrete.yosef.api.component

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioGroup
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.api.property.color.BackgroundColorCommand
import br.com.concrete.yosef.api.property.color.BackgroundColorCommand.Companion.BACKGROUND_COLOR
import br.com.concrete.yosef.api.property.id.IdCommand
import br.com.concrete.yosef.api.property.id.IdCommand.Companion.ID
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Class that implements the [Component] interface and creates the component
 * RadioGroup([RadioGroup]), applying its properties
 */
class RadioGroupButtonComponent : Component {

    companion object {
        /**
         * This constant documents which type is associated with the component
         */
        const val RADIO_GROUP_BUTTON = "radioGroupButton"
    }

    private val commands: Map<String, DynamicPropertyCommand> = mapOf(
            BACKGROUND_COLOR to BackgroundColorCommand(),
            ID to IdCommand()
    )

    override fun applyProperties(view: View, dynamicProperties: List<DynamicProperty>, actionListener: OnActionListener?) {
        dynamicProperties.forEach {
            commands[it.name]?.apply(view, it)
        }
    }

    override fun createView(parent: ViewGroup): View {
        return RadioGroup(parent.context).apply {
            layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

}
