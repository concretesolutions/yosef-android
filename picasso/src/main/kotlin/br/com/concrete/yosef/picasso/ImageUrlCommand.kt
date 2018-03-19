package br.com.concrete.yosef.picasso

import android.view.View
import android.widget.ImageView
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty
import com.squareup.picasso.Picasso

/**
 * Command pattern for the image url property
 */
class ImageUrlCommand(private val picasso: Picasso) : DynamicPropertyCommand {

    companion object {
        const val IMAGE_URL = "url"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {
        if (view is ImageView) {
            picasso.load(dynamicProperty.value).into(view)
        }
    }

}
