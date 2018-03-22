package br.com.concrete.yosef.api.component

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.api.property.frame.GravityCommand
import br.com.concrete.yosef.api.property.frame.GravityCommand.Companion.GRAVITY
import br.com.concrete.yosef.api.property.elementgroup.OrientationCommand
import br.com.concrete.yosef.api.property.elementgroup.OrientationCommand.Companion.ORIENTATION
import br.com.concrete.yosef.api.property.id.IdCommand
import br.com.concrete.yosef.api.property.id.IdCommand.Companion.ID
import br.com.concrete.yosef.api.property.spacing.PaddingPropertyCommand
import br.com.concrete.yosef.api.property.spacing.PaddingPropertyCommand.Companion.PADDING
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Class that implements the [Component] interface and creates the component
 * ElementGroup([LinearLayout]), applying its properties
 */
class ElementGroupComponent : Component {

    companion object {
        /**
         * This constant documents which type is associated with the component
         */
        const val ELEMENT_GROUP = "elementGroup"
    }

    private val components: Map<String, DynamicPropertyCommand> = mapOf(
        ORIENTATION to OrientationCommand(),
        GRAVITY to GravityCommand(),
        PADDING to PaddingPropertyCommand(),
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
        return LinearLayout(context).apply {
            tag = ELEMENT_GROUP
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        }
    }
}
