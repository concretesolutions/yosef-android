package br.com.concrete.yosef.glide

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.component.Component
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.api.property.id.IdCommand
import br.com.concrete.yosef.api.property.size.HeightCommand
import br.com.concrete.yosef.api.property.size.WidthCommand
import br.com.concrete.yosef.entity.DynamicProperty
import br.com.concrete.yosef.glide.ImageUrlCommand.Companion.IMAGE_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder

class GlideImageComponent(glide: Glide =
                          GlideBuilder().build(DummyContentProvider.appContext)) : Component {

    companion object {
        const val IMAGE_TYPE = "image"
    }

    private val commands: Map<String, DynamicPropertyCommand> = mapOf(
            WidthCommand.WIDTH_TYPE to WidthCommand(),
            HeightCommand.HEIGHT_TYPE to HeightCommand(),
            IMAGE_URL to ImageUrlCommand(glide),
            IdCommand.ID to IdCommand()
    )

    override fun applyProperties(view: View, dynamicProperties: List<DynamicProperty>, actionListener: OnActionListener?) {
        dynamicProperties.forEach {
            commands[it.name]?.apply(view, it)
        }
    }

    override fun createView(parent: ViewGroup): View {
        return ImageView(parent.context).apply {
            layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }
}
