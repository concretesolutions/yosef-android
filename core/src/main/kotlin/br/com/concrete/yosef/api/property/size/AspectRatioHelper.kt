package br.com.concrete.yosef.api.property.size

import android.text.TextUtils
import br.com.concrete.yosef.canBeConvertedToInt

class AspectRatioHelper {

    private val split = ":"

    fun valueCanBeParsed(value: String): Boolean {
        if (TextUtils.isEmpty(value) ||
            !value.contains(split) ||
            value.split(split).size != 2 ||
            !value.split(split)[0].canBeConvertedToInt() ||
            !value.split(split)[1].canBeConvertedToInt()) {
            return false
        }

        return true
    }

    fun generateHeightGivenAspectRatio(value: String, width: Int): Int {
        val values = value.split(split)
        val widthAspectRatio = values[0].toInt()
        val heightAspectRatio = values[1].toInt()

        return (heightAspectRatio * width) / widthAspectRatio
    }
}
