package br.com.concrete.yosef.api

import android.view.ViewGroup
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.component.*
import br.com.concrete.yosef.api.component.ButtonComponent.Companion.BUTTON_TYPE
import br.com.concrete.yosef.api.component.ElementGroupComponent.Companion.ELEMENT_GROUP
import br.com.concrete.yosef.api.component.RadioButtonComponent.Companion.RADIO_BUTTON
import br.com.concrete.yosef.api.component.RadioGroupButtonComponent.Companion.RADIO_GROUP_BUTTON
import br.com.concrete.yosef.api.component.TextFieldComponent.Companion.TEXT_FIELD
import br.com.concrete.yosef.api.component.TextViewComponent.Companion.TEXT_TYPE
import br.com.concrete.yosef.entity.DynamicComponent
import br.com.concrete.yosef.fromJson
import com.google.gson.Gson

/**
 * Main class for the Yosef library. This class has a [Builder] with default
 * [components] to be created and may receive custom [Component] classes.
 */
class DynamicViewCreator(private val components: Map<String, Component>, private val gson: Gson) {

    /**
     * This method creates a list of [DynamicComponent] by the passed json and calls
     * [addChildrenRecursively] method to add them to the parent view
     *
     * @param parent parent [ViewGroup] that will have the components as children
     * @param json formatted json according with the library protocol. This json will have
     * informations about which components will be created and its properties to be applied
     * @param listener is the responsible for calling events that
     * are related with components actions
     */
    fun createViewFromJson(parent: ViewGroup, json: String, listener: OnActionListener? = null) {
        val parentComponent = gson.fromJson<List<DynamicComponent>>(json)
        parentComponent.forEach { addChildrenRecursively(parent, it, listener) }
    }

    /**
     * Method that adds components to the parent, possibly also adding children
     * of the created components
     *
     * @param topLevelViewGroup the parent who will group the components
     * @param childComponent the component that should be created and added in the parent
     * @param listener is the responsible for calling events that
     * are related with components actions
     */
    private fun addChildrenRecursively(topLevelViewGroup: ViewGroup,
                                       childComponent: DynamicComponent?,
                                       listener: OnActionListener? = null) {

        if (childComponent == null) return

        if (components[childComponent.type] == null) {
            throw IllegalStateException("There are no components registered " +
                    "in this ViewCreator that can render ${childComponent.type}")
        }

        val component = components[childComponent.type]!!
        val view = component.createView(topLevelViewGroup).apply {
            component.applyProperties(this, childComponent.dynamicProperties, listener)
        }

        childComponent.children?.forEach { addChildrenRecursively(view as ViewGroup, it) }
        topLevelViewGroup.addView(view)
    }


    /**
     * A builder class that sets the default structural components for the ViewCreator to use.
     */
    class Builder {

        private val components: MutableMap<String, Component>
        private var gson: Gson = Gson()

        /**
         * @constructor
         */
        init {
            components = hashMapOf(TEXT_TYPE to TextViewComponent(),
                    BUTTON_TYPE to ButtonComponent(),
                    TEXT_FIELD to TextFieldComponent(),
                    ELEMENT_GROUP to ElementGroupComponent(),
                    RADIO_BUTTON to RadioButtonComponent(),
                    RADIO_GROUP_BUTTON to RadioGroupButtonComponent()
            )
        }

        /**
         * Method that lets custom [Component] to be added to the library
         *
         * @param type type that will be associated with the component in the json
         * @param component component that will create the views and call the properties
         * @return Builder with the custons components
         */
        @SuppressWarnings("unused")
        fun addComponentFor(type: String, component: Component): Builder {
            components[type] = component
            return this
        }

        /**
         * Method that creates a [DynamicViewCreator] according with its components
         *
         * @return DynamicViewCreator that will be created
         */
        fun build(): DynamicViewCreator {
            return DynamicViewCreator(components, gson)
        }
    }
}