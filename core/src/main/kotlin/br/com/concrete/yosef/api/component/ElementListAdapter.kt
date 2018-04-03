package br.com.concrete.yosef.api.component

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

internal class ElementListAdapter(private val views: List<View>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return views[position]
    }

    override fun getItem(position: Int): Any {
        return views[position]
    }

    override fun getItemId(position: Int): Long {
        return views[position].id.toLong()
    }

    override fun getCount(): Int {
        return views.size
    }
}
