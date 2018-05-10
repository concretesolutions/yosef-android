package br.com.concrete.yosef.gradient.component

import android.content.Context
import android.view.View
import android.view.ViewGroup
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.component.Component
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.api.property.id.IdCommand
import br.com.concrete.yosef.api.property.size.HeightCommand
import br.com.concrete.yosef.api.property.size.WidthCommand
import br.com.concrete.yosef.api.property.spacing.MarginPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty
import br.com.concrete.yosef.gradient.property.ColorArrayCommand
import br.com.concrete.yosef.gradient.property.ColorArrayCommand.Companion.COLORS

class GradientComponent : Component {

    companion object {
        const val GRADIENT: String = "gradient"
    }

    private val commands: Map<String, DynamicPropertyCommand> = mapOf(
        WidthCommand.WIDTH_TYPE to WidthCommand(),
        HeightCommand.HEIGHT_TYPE to HeightCommand(),
        MarginPropertyCommand.MARGIN to MarginPropertyCommand(),
        COLORS to ColorArrayCommand(),
        IdCommand.ID to IdCommand()
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
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }
}