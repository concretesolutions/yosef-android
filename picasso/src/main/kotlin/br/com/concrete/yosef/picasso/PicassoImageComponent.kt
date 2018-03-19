package br.com.concrete.yosef.picasso

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.component.Component
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.api.property.id.IdCommand
import br.com.concrete.yosef.api.property.id.IdCommand.Companion.ID
import br.com.concrete.yosef.api.property.size.HeightCommand
import br.com.concrete.yosef.api.property.size.HeightCommand.Companion.HEIGHT_TYPE
import br.com.concrete.yosef.api.property.size.WidthCommand
import br.com.concrete.yosef.api.property.size.WidthCommand.Companion.WIDTH_TYPE
import br.com.concrete.yosef.entity.DynamicProperty
import br.com.concrete.yosef.picasso.ImageUrlCommand.Companion.IMAGE_URL
import com.squareup.picasso.Picasso

class PicassoImageComponent(
    picasso: Picasso = Picasso.Builder(DummyContentProvider.appContext).build()
) : Component {

    companion object {
        const val IMAGE_TYPE = "image"
    }

    private val commands: Map<String, DynamicPropertyCommand> = mapOf(
        WIDTH_TYPE to WidthCommand(),
        HEIGHT_TYPE to HeightCommand(),
        IMAGE_URL to ImageUrlCommand(picasso),
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

    override fun createView(parent: ViewGroup): View {
        return ImageView(parent.context).apply {
            layoutParams = LinearLayout.LayoutParams(
                WRAP_CONTENT, WRAP_CONTENT)
        }
    }
}
