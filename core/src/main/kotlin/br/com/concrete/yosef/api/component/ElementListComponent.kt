package br.com.concrete.yosef.api.component

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import android.widget.ListView
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.api.property.color.BackgroundColorCommand
import br.com.concrete.yosef.api.property.color.BackgroundColorCommand.Companion.BACKGROUND_COLOR
import br.com.concrete.yosef.api.property.id.IdCommand
import br.com.concrete.yosef.api.property.id.IdCommand.Companion.ID
import br.com.concrete.yosef.api.property.spacing.MarginPropertyCommand
import br.com.concrete.yosef.api.property.spacing.MarginPropertyCommand.Companion.MARGIN
import br.com.concrete.yosef.api.property.spacing.PaddingPropertyCommand
import br.com.concrete.yosef.api.property.spacing.PaddingPropertyCommand.Companion.PADDING
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Class that implements the [Component] interface and creates the component
 * ElementList([ListView]), applying its properties
 */
class ElementListComponent : Component {

    companion object {
        /**
         * This constant documents which type is associated with the component
         */
        const val ELEMENT_LIST = "elementList"
    }

    private val components: Map<String, DynamicPropertyCommand> = mapOf(
        PADDING to PaddingPropertyCommand(),
        MARGIN to MarginPropertyCommand(),
        BACKGROUND_COLOR to BackgroundColorCommand(),
        ID to IdCommand()
    )

    override fun applyProperties(
        view: View,
        dynamicProperties: List<DynamicProperty>,
        actionListener: OnActionListener?
    ) {
        dynamicProperties.forEach {
            components[it.name]?.apply(view, it)
        }
    }

    override fun createView(context: Context): View {
        return ListView(context).apply {
            tag = ELEMENT_LIST
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        }
    }
}
