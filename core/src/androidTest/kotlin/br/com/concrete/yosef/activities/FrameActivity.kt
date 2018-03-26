package br.com.concrete.yosef.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.DynamicViewCreator
import br.com.concrete.yosef.test.R

class FrameActivity : AppCompatActivity(), OnActionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val json = FrameActivity::class.java.getResource(
            "/assets/example_frame_component.json").readText()

        val dynamicView = DynamicViewCreator.Builder().build()

        setContentView(dynamicView.createViewFromJson(this, json, this))
    }

    override fun callAction(value: String) {
        findViewById<TextView>(R.id.textview).text = value
    }
}
