package br.com.concrete.yosef.api.component

import android.view.View
import android.view.ViewGroup
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.entity.DynamicProperty

interface Component {

    fun applyProperties(
        view: View,
        dynamicProperties: List<DynamicProperty>,
        actionListener: OnActionListener?
    )

    fun createView(parent: ViewGroup): View
}