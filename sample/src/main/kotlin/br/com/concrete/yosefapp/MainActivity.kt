package br.com.concrete.yosefapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Toast
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.DynamicViewCreator
import br.com.concrete.yosef.glide.GlideImageComponent
import br.com.concrete.yosef.glide.GlideImageComponent.Companion.IMAGE_TYPE
import br.com.concrete.yosef.lottie.LottieAnimationComponent
import br.com.concrete.yosef.lottie.LottieAnimationComponent.Companion.ANIMATION_TYPE

class MainActivity : AppCompatActivity(), OnActionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val parent = ScrollView(this)
                .apply {
                    layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT)
                    isFillViewport = true
                }
                .run {
                    LinearLayout(this@MainActivity).apply {
                        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT)
                        orientation = LinearLayout.VERTICAL
                    }
                }

        val json1 = MainActivity::class.java.getResource("/assets/example_animation.json").readText()
        val json2 = MainActivity::class.java.getResource("/assets/example_image.json").readText()

        val creator = DynamicViewCreator.Builder()
                .addComponentFor(ANIMATION_TYPE, LottieAnimationComponent())
                .addComponentFor(IMAGE_TYPE, GlideImageComponent())
                .build()

        creator.createViewFromJson(parent, json1, this)
        creator.createViewFromJson(parent, json2, this)
        setContentView(parent)
    }

    override fun callAction(value: String) {
        Toast.makeText(this, value, Toast.LENGTH_LONG).show()
    }

}
