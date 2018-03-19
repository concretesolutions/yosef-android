package br.com.concrete.yosef.canarinho.api.component

import android.view.View
import android.view.ViewGroup
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.component.Component
import br.com.concrete.yosef.api.component.TextFieldComponent
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.canarinho.api.property.MaskPropertyCommand
import br.com.concrete.yosef.canarinho.api.property.MaskPropertyCommand.Companion.MASK
import br.com.concrete.yosef.entity.DynamicProperty

class CanarinhoTextFieldComponent : Component {

    companion object {
        const val TEXT_FIELD = "textField"
    }

    private val textFieldComponent = TextFieldComponent()

    private val commands: Map<String, DynamicPropertyCommand> = mapOf(
            MASK to MaskPropertyCommand()
    )

    override fun applyProperties(view: View, dynamicProperties: List<DynamicProperty>, actionListener: OnActionListener?) {
        textFieldComponent.applyProperties(view, dynamicProperties, actionListener)
        dynamicProperties.forEach {
            commands[it.name]?.apply(view, it)
        }
    }

    override fun createView(parent: ViewGroup): View {
       return textFieldComponent.createView(parent)
    }
}
