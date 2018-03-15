package br.com.concrete.yosef.api.property

import android.view.View
import br.com.concrete.yosef.entity.DynamicProperty

/**
 *
 */
interface DynamicPropertyCommand {
    fun apply(view: View, dynamicProperty: DynamicProperty)
}