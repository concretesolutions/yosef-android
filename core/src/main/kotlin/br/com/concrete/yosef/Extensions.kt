package br.com.concrete.yosef

import android.content.Context
import android.os.Build
import android.support.annotation.VisibleForTesting
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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

    Log.d("sdlkf width", layoutParams.width.toString())
    Log.d("sdlkf height", layoutParams.height.toString())
    layout(0, 0, layoutParams.width, layoutParams.height)
}
