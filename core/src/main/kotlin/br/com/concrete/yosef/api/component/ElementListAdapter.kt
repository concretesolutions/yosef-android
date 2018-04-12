package br.com.concrete.yosef.api.component

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.FrameLayout
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.DynamicViewCreator
import br.com.concrete.yosef.entity.DynamicComponent

internal class ElementListAdapter(
    private val list: List<DynamicComponent>,
    private val components: Map<String, Component>,
    private val listener: OnActionListener?
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        parent?.let {
            val childComponent = DynamicViewCreator.getComponentByType(list[position], components)
            val childView = childComponent.createView(parent.context)

            list[position].children?.let {
                childComponent.addComponentsAsChildren(it, childView, components, listener)
            }

            val frameLayout = FrameLayout(parent.context)
            frameLayout.addView(childView)
            childComponent.applyProperties(childView, list[position].dynamicProperties, listener)

            return frameLayout
        }

        return View(parent?.context)
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}
