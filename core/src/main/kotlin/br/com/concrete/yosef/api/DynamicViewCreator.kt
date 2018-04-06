package br.com.concrete.yosef.api

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ListView
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.DynamicViewCreator.Builder
import br.com.concrete.yosef.api.component.*
import br.com.concrete.yosef.api.component.ButtonComponent.Companion.BUTTON_TYPE
import br.com.concrete.yosef.api.component.ElementGroupComponent.Companion.ELEMENT_GROUP
import br.com.concrete.yosef.api.component.ElementListComponent.Companion.ELEMENT_LIST
import br.com.concrete.yosef.api.component.FrameComponent.Companion.FRAME
import br.com.concrete.yosef.api.component.RadioButtonComponent.Companion.RADIO_BUTTON
import br.com.concrete.yosef.api.component.RadioGroupButtonComponent.Companion.RADIO_GROUP_BUTTON
import br.com.concrete.yosef.api.component.SeparatorComponent.Companion.SEPARATOR
import br.com.concrete.yosef.api.component.TextFieldComponent.Companion.TEXT_FIELD
import br.com.concrete.yosef.api.component.TextViewComponent.Companion.TEXT_TYPE
import br.com.concrete.yosef.entity.DynamicComponent
import br.com.concrete.yosef.fromJson
import com.google.gson.Gson

/**
 * Main class for the Yosef library. This class has a [Builder] with default
 * [components] to be created and may receive custom [Component] classes.
 */
class DynamicViewCreator(
    private val components: Map<String, Component>,
    private val gson: Gson
) {

    /**
     * This method creates a view from a list of [DynamicComponent] based on the [json]
     *
     * @param context [Context] needed to create views
     * @param json formatted json according with the library protocol. This json will have
     * information about which components will be created and its properties to be applied
     * @param listener is the responsible for calling events that
     * are related with components actions
     */
    fun createViewFromJson(
        context: Context,
        json: String,
        listener: OnActionListener? = null
    ): View {

        val componentSpec = gson.fromJson<DynamicComponent>(json)
        val component = components[componentSpec.type]
            ?: throw IllegalStateException("There are no components registered " +
                "in this ViewCreator that can render ${componentSpec.type}")

        val parentView = component.createView(context)

        componentSpec.children?.let {
            component.addComponentsAsChildren(it, parentView, components, listener)
        }

        component.applyProperties(parentView, componentSpec.dynamicProperties, listener)

        return parentView
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
                RADIO_GROUP_BUTTON to RadioGroupButtonComponent(),
                FRAME to FrameComponent(),
                SEPARATOR to SeparatorComponent(),
                ELEMENT_LIST to ElementListComponent()
            )
        }

        /**
         * Method that lets custom [Component] to be added to the library
         *
         * @param type that will be associated with the component in the json
         * @param component that will create the views and call the properties
         * @return Builder with the custom components
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

    companion object {
        internal fun getComponentByType(dynamicComponent: DynamicComponent, components: Map<String, Component>): Component {
            if (components[dynamicComponent.type] == null) {
                throw IllegalStateException("There are no components registered " +
                    "in this ViewCreator that can render ${dynamicComponent.type}")
            }

            return components[dynamicComponent.type]!!
        }
    }
}
