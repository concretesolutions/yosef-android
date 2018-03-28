package br.com.concrete.yosef.api.component

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.api.property.color.BackgroundColorCommand
import br.com.concrete.yosef.api.property.color.BackgroundColorCommand.Companion.BACKGROUND_COLOR
import br.com.concrete.yosef.api.property.id.IdCommand
import br.com.concrete.yosef.api.property.id.IdCommand.Companion.ID
import br.com.concrete.yosef.api.property.size.HeightCommand
import br.com.concrete.yosef.api.property.size.HeightCommand.Companion.HEIGHT_TYPE
import br.com.concrete.yosef.api.property.size.WidthCommand
import br.com.concrete.yosef.api.property.size.WidthCommand.Companion.WIDTH_TYPE
import br.com.concrete.yosef.api.property.spacing.MarginPropertyCommand
import br.com.concrete.yosef.api.property.spacing.MarginPropertyCommand.Companion.MARGIN
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Class that implements the [Component] interface and creates the component
 * View([View]) to be used as a separator, applying its properties
 */
class SeparatorComponent : Component {

    companion object {
        const val SEPARATOR: String = "separator"
    }

    private val commands: Map<String, DynamicPropertyCommand> = mapOf(
        WIDTH_TYPE to WidthCommand(),
        HEIGHT_TYPE to HeightCommand(),
        BACKGROUND_COLOR to BackgroundColorCommand(),
        MARGIN to MarginPropertyCommand(),
        ID to IdCommand()
    )

    override fun applyProperties(
        view: View,
        dynamicProperties: List<DynamicProperty>,
        actionListener: OnActionListener?
    ) {
        dynamicProperties.forEach {
            commands[it.name]?.apply(view, it)
        }
    }

    override fun createView(context: Context): View {
        return View(context).apply {
            layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        }
    }
}
