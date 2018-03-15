package br.com.concrete.yosef.canarinho.api.property

import android.view.View
import android.widget.EditText
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty
import br.com.concretesolutions.canarinho.watcher.ValorMonetarioWatcher

/**
 * Command pattern for the mask textfield property
 */
class MaskPropertyCommand : DynamicPropertyCommand {

    companion object {
        const val MASK = "mask"
        const val MASK_MONETARY = "monetary"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {
        if (view is EditText) {
            if (MASK == dynamicProperty.name) {
                if (MASK_MONETARY == dynamicProperty.value) {
                    view.hint = "R$0,00"
                    view.addTextChangedListener(ValorMonetarioWatcher.Builder().comSimboloReal().build())
                }
            }
        }
    }
}