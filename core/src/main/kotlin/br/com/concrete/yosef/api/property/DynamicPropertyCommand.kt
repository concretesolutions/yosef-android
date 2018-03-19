package br.com.concrete.yosef.api.property

import android.view.View
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Interface for properties that will be applied to the views
 */
interface DynamicPropertyCommand {

    /**
     * Method that apply property to view
     *
     * @param view                  View that should receive the property
     * @param dynamicProperty       property with the values that should be applied to the view
     */
    fun apply(view: View, dynamicProperty: DynamicProperty)
}
