package br.com.concrete.yosef.api.component

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.api.property.elementgroup.OrientationCommand
import br.com.concrete.yosef.api.property.elementgroup.OrientationCommand.Companion.ORIENTATION
import br.com.concrete.yosef.api.property.id.IdCommand
import br.com.concrete.yosef.api.property.id.IdCommand.Companion.ID
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Command pattern for the Element Group
 */
class ElementGroupComponent : Component {

    companion object {
        const val ELEMENT_GROUP = "elementGroup"
    }

    private val components: Map<String, DynamicPropertyCommand> = mapOf(
            ORIENTATION to OrientationCommand(),
            ID to IdCommand()
    )

    override fun applyProperties(view: View, dynamicProperties: List<DynamicProperty>, actionListener: OnActionListener?) {
        dynamicProperties.forEach {
            components[it.name]?.apply(view, it)
        }
    }

    override fun createView(parent: ViewGroup): View {
        return LinearLayout(parent.context).apply {
            tag = ELEMENT_GROUP
            layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

}
