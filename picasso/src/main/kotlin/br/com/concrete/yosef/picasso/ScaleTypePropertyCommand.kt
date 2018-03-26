package br.com.concrete.yosef.picasso

import android.view.View
import android.widget.ImageView
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty

class ScaleTypePropertyCommand : DynamicPropertyCommand {

    companion object {
        const val SCALE_TYPE = "scaleType"
        const val SCALE_TO_FILL = "scaleToFill"
        const val SCALE_ASPECT_FIT = "scaleAspectFit"
        const val SCALE_ASPECT_FILL = "scaleAspectFill"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {
        if (view !is ImageView)
            throw IllegalArgumentException("Can't apply ${dynamicProperty.type} " +
                "to view of type ${view.javaClass.name}")

        when (dynamicProperty.value) {
            SCALE_TO_FILL -> view.scaleType = ImageView.ScaleType.CENTER_CROP
            SCALE_ASPECT_FIT -> view.adjustViewBounds = true
            SCALE_ASPECT_FILL -> {
                view.adjustViewBounds = true
                view.scaleType = ImageView.ScaleType.CENTER_CROP
            }

        }
    }
}