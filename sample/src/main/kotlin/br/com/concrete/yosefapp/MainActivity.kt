package br.com.concrete.yosefapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ScrollView
import android.widget.Toast
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.DynamicViewCreator
import br.com.concrete.yosef.glide.GlideImageComponent

class MainActivity : AppCompatActivity(), OnActionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val json = MainActivity::class.java
            .getResource("/assets/example_screen.json")
            .readText()

        val creator = DynamicViewCreator
            .Builder()
            .addComponentFor("image", GlideImageComponent())
            .build()

        val view = creator.createViewFromJson(this, json, this)
        setContentView(ScrollView(this).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            addView(view)
        })
    }

    override fun callAction(value: String) {
        Toast.makeText(this, value, Toast.LENGTH_LONG).show()
    }
}
