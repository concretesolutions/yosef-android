package br.com.concrete.yosef.api.property.size

import android.view.View
import br.com.concrete.yosef.entity.DynamicProperty

interface DimenSpec {

    val dimen: String
        get() = "dimen"
    val dimenSpec: String
        get() = "dimenSpec"
    val match: String
        get() = "match"
    val wrap: String
        get() = "wrap"

    fun applyDimenSpec(view: View, dynamicProperty: DynamicProperty)
}
