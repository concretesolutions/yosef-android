package br.com.concrete.yosef

import android.os.Build
import android.view.View
import android.view.ViewTreeObserver

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)!!

inline fun supportsLollipop(code: () -> Unit): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        code()
        return true
    }
    return false
}

fun View.afterLayout(callback: (it: ViewTreeObserver.OnGlobalLayoutListener) -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            callback.invoke(this)
            viewTreeObserver.removeOnGlobalLayoutListener(this)
        }

    })
}
