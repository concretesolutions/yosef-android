package br.com.concrete.yosef.api.component

import android.content.Context
import android.view.View
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.entity.DynamicComponent
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Interface for components, so they can be created and the properties
 * @link [br.com.concrete.yosef.api.property.DynamicPropertyCommand] applied
 */
interface Component {

    /**
     * Method that returns which view should be created
     *
     * @param context A context in which the view will be created
     * @return the view created by this component
     */
    fun createView(context: Context): View

    /**
     * Method that checks which properties will be applied to the view
     *
     * @param view in which the properties should be applied to
     * @param dynamicProperties the list of properties to style the view
     * @param actionListener Listener that receives actions from the components
     */
    fun applyProperties(
        view: View,
        dynamicProperties: List<DynamicProperty>,
        actionListener: OnActionListener?
    )

    /**
     * Method that checks which properties will be applied to the view
     *
     * @param view in which the properties should be applied to
     * @param dynamicProperties the list of properties to style the view
     * @param actionListener Listener that receives actions from the components
     */
    fun addComponentsAsChildren(
        children: List<DynamicComponent>,
        view: View,
        components: Map<String, Component>,
        listener: OnActionListener?
    ) {
        throw UnsupportedOperationException("${this.javaClass.simpleName} doesn't support adding views as children.")
    }
}
