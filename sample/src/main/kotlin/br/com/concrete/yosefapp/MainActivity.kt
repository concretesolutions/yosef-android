package br.com.concrete.yosefapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import android.widget.Toast
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.DynamicViewCreator

class MainActivity : AppCompatActivity(), OnActionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val parent = findViewById<FrameLayout>(R.id.parent)

        val json = MainActivity::class.java
            .getResource("/assets/example_simple.json")
            .readText()

        val creator = DynamicViewCreator.Builder()
            .build()

        creator.createViewFromJson(parent, json, this)
    }

    override fun callAction(value: String) {
        Toast.makeText(this, value, Toast.LENGTH_LONG).show()
    }
}
