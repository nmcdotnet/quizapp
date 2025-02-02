package com.cstrien.thi_trac_nghiem.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.cstrien.thi_trac_nghiem.R
import com.cstrien.thi_trac_nghiem.model.Category

class CategoryAdapter(val lisCategory: ArrayList<Category>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return lisCategory.size
    }

    override fun getItem(position: Int): Any {
        return lisCategory[position]
    }

    override fun getItemId(position: Int): Long {
        return lisCategory[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewCategory =
            convertView ?: View.inflate(parent.context, R.layout.category_view, null)

        //Bind dữ liệu phần tử vào View
        val category = getItem(position) as Category
        (viewCategory.findViewById<View>(R.id.id_category) as TextView).text =
            String.format("%d", category.id)
        (viewCategory.findViewById<View>(R.id.name_category) as TextView).text =
            String.format("%s", category.name)


        return viewCategory
    }
}
