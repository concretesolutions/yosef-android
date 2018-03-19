package br.com.concrete.yosef.api.component

import android.view.View
import android.view.ViewGroup
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Interface for components, so they can be created and the properties
 * @link [br.com.concrete.yosef.api.property.DynamicPropertyCommand] applied
 */
interface Component {

    /**
     * Method that returns which view should be created
     *
     * @param parent The parent that will hold the view as child
     * @return view that will be created from the parent ViewGroup
     */
    fun createView(parent: ViewGroup): View

    /**
     * Method that checks which properties will be applied to the view
     *
     * @param view View that should apply properties
     * @param dynamicProperties List of properties that the view should apply
     * @param actionListener Listener that receives actions from the components
     */
    fun applyProperties(
        view: View,
        dynamicProperties: List<DynamicProperty>,
        actionListener: OnActionListener?
    )
}
