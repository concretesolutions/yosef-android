package br.com.concrete.yosef.api.property.size

import android.text.TextUtils
import br.com.concrete.yosef.canBeConvertedToInt

/**
 * Helper class that handle [AspectRatioCommand] related logic.
 */
class AspectRatioHelper {

    private val split = ":"

    /**
     * Method that validates if given value can be parsed to an aspect ratio.
     *
     * @param value The value that will be validated;
     * @return true if the value can be parsed to an aspect ration, false otherwise;
     */
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

    /**
     * Method that generate a height given a width and an aspect ratio.
     *
     * @param aspectRatio The aspect ratio that will be used;
     * @param width The width that the aspect ration will be based on;
     * @return the generated height for the aspect ratio;
     */
    fun generateHeightGivenAspectRatio(aspectRatio: String, width: Int): Int {
        val values = aspectRatio.split(split)
        val widthAspectRatio = values[0].toInt()
        val heightAspectRatio = values[1].toInt()

        return (heightAspectRatio * width) / widthAspectRatio
    }
}
