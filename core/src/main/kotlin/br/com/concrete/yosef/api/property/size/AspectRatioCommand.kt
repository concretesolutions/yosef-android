package br.com.concrete.yosef.api.property.size

import android.view.View
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Command class that implements the [DynamicPropertyCommand] applying
 * the aspect ration property to the view([View]) adjusting the height of the view and
 * maintaining the original width
 */
class AspectRatioCommand : DynamicPropertyCommand {

    companion object {
        /**
         * This constant documents which name is associated with the property
         */
        const val ASPECT_RATIO = "aspectRatio"
    }

    val aspectRatioHelper = AspectRatioHelper()

    override fun apply(view: View, dynamicProperty: DynamicProperty) {

        if (dynamicProperty.type == "aspectRatio") {
            if (aspectRatioHelper.valueCanBeParsed(dynamicProperty.value)) {
                applyAspectRationWhenPossible(dynamicProperty, view)
            } else {
                throw IllegalArgumentException("Can't apply ${dynamicProperty.name}" +
                    " with value ${dynamicProperty.value}, it does not apply the property " +
                    "format('16:9' for example).")
            }
        } else {
            throw IllegalArgumentException("Can't apply ${dynamicProperty.name}" +
                " with type ${dynamicProperty.type}, unknown type.")
        }
    }

    private fun applyAspectRationWhenPossible(dynamicProperty: DynamicProperty, view: View) {
        view.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int,
                                        oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
                if (view.width == 0) return

                view.removeOnLayoutChangeListener(this)

                val layoutParams = view.layoutParams
                val correctedHeight = aspectRatioHelper.generateHeightGivenAspectRatio(dynamicProperty.value, view.width)
                layoutParams.height = correctedHeight

                view.layoutParams = layoutParams
            }
        })
    }
}
