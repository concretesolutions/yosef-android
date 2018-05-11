package br.com.concrete.yosef.gradient.property

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.text.TextUtils
import android.view.View
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.canBeConvertedToInt
import br.com.concrete.yosef.entity.DynamicProperty

class ColorArrayCommand : DynamicPropertyCommand {

    companion object {
        const val COLORS = "colors"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {

        if (dynamicProperty.type == "colors") {
            val value = dynamicProperty.value

            if (valueCanBeParsed(value)) {
                val parsedColors = ArrayList<Int>()
                val colors = value.split(",")
                colors.forEach {
                    val (red, green, blue, alpha) = it.trim().split(".")
                    val parsedColor = Color.argb(
                        alpha.toInt(),
                        red.toInt(),
                        green.toInt(),
                        blue.toInt()
                    )
                    parsedColors.add(parsedColor)
                }
                val gradientDrawable = GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    parsedColors.toIntArray()
                )

                view.background = gradientDrawable
            } else {
                throw IllegalArgumentException("Can't apply ${dynamicProperty.name}" +
                    " with value '${dynamicProperty.value}', it does not apply the property " +
                    "format('0.0.0.255, 0.0.0.0' for example).")
            }
        } else {
            throw IllegalArgumentException("Can't apply ${dynamicProperty.name}" +
                " with type '${dynamicProperty.type}', unknown type.")
        }
    }

    private fun valueCanBeParsed(value: String): Boolean {
        if (TextUtils.isEmpty(value)) {
            return false
        }

        val splicedColors = value.split(",")
        if (splicedColors.isEmpty()) {
            return false
        }

        splicedColors.forEach {
            if (TextUtils.isEmpty(it) || !it.contains(".")) {
                return false
            }

            val splicedARBGs = it.split(".")
            if (splicedARBGs.size != 4) {
                return false
            }

            splicedARBGs.forEach {
                if (!it.trim().canBeConvertedToInt()) {
                    return false
                }
            }
        }
        return true
    }
}
