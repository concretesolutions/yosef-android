package br.com.concrete.yosef

import android.content.Context
import android.os.Build
import android.support.annotation.VisibleForTesting
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.math.roundToInt

inline fun <reified T> Gson.fromJson(json: String) =
    this.fromJson<T>(json, object : TypeToken<T>() {}.type)!!

inline fun supportsLollipop(code: () -> Unit): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        code()
        return true
    }
    return false
}

fun Float.dp(context: Context): Float {
    val resources = context.resources
    val metrics = resources.displayMetrics
    return this * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

fun Int.dp(context: Context): Int {
    return this.toFloat().dp(context).roundToInt()
}

@VisibleForTesting
fun View.layoutAndAssert(action: (view: View) -> Unit) {
    addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
        override fun onLayoutChange(
            view: View,
            left: Int,
            top: Int,
            right: Int,
            bottom: Int,
            oldLeft: Int,
            oldTop: Int,
            oldRight: Int,
            oldBottom: Int
        ) {
            view.removeOnLayoutChangeListener(this)
            action(view)
        }
    })
    layout(0, 0, layoutParams?.width ?: MATCH_PARENT, layoutParams?.height ?: WRAP_CONTENT)
}
