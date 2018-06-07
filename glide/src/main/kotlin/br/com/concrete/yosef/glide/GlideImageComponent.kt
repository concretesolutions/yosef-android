package br.com.concrete.yosef.glide

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.component.Component
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.api.property.color.BackgroundColorCommand
import br.com.concrete.yosef.api.property.color.BackgroundColorCommand.Companion.BACKGROUND_COLOR
import br.com.concrete.yosef.api.property.color.ColorFilterCommand
import br.com.concrete.yosef.api.property.color.ColorFilterCommand.Companion.COLOR_FILTER
import br.com.concrete.yosef.api.property.id.IdCommand
import br.com.concrete.yosef.api.property.id.IdCommand.Companion.ID
import br.com.concrete.yosef.api.property.size.AspectRatioCommand
import br.com.concrete.yosef.api.property.size.AspectRatioCommand.Companion.ASPECT_RATIO
import br.com.concrete.yosef.api.property.size.HeightCommand
import br.com.concrete.yosef.api.property.size.HeightCommand.Companion.HEIGHT_TYPE
import br.com.concrete.yosef.api.property.size.WidthCommand
import br.com.concrete.yosef.api.property.size.WidthCommand.Companion.WIDTH_TYPE
import br.com.concrete.yosef.entity.DynamicProperty
import br.com.concrete.yosef.glide.ImageUrlCommand.Companion.IMAGE_URL
import br.com.concrete.yosef.glide.ScaleTypePropertyCommand.Companion.SCALE_TYPE
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder

class GlideImageComponent(
    glide: Glide = GlideBuilder().build(DummyContentProvider.appContext)
) : Component {

    companion object {
        const val IMAGE_TYPE = "image"
    }

    private val commands: Map<String, DynamicPropertyCommand> = mapOf(
        WIDTH_TYPE to WidthCommand(),
        HEIGHT_TYPE to HeightCommand(),
        SCALE_TYPE to ScaleTypePropertyCommand(),
        IMAGE_URL to ImageUrlCommand(glide),
        ASPECT_RATIO to AspectRatioCommand(),
        BACKGROUND_COLOR to BackgroundColorCommand(),
        COLOR_FILTER to ColorFilterCommand(),
        ID to IdCommand()
    )

    override fun applyProperties(
        view: View,
        dynamicProperties: List<DynamicProperty>,
        actionListener: OnActionListener?
    ) {
        dynamicProperties.forEach {
            commands[it.name]?.apply(view, it)
        }
    }

    override fun createView(context: Context): View {
        return ImageView(context).apply {
            layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            adjustViewBounds = true
        }
    }
}
