package br.com.concrete.yosef.api.property.size

import android.view.View
import br.com.concrete.yosef.entity.DynamicProperty

interface DimenSpec {

    fun applyDimenSpec(view: View, dynamicProperty: DynamicProperty)
}