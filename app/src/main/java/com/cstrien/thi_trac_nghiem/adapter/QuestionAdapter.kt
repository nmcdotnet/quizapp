package com.cstrien.thi_trac_nghiem.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.cstrien.thi_trac_nghiem.R
import com.cstrien.thi_trac_nghiem.model.Category
import com.cstrien.thi_trac_nghiem.model.Question

class QuestionAdapter(
    val listQuestion: ArrayList<Question>,
    private val listCategories: ArrayList<Category>
) :
    BaseAdapter() {
    override fun getCount(): Int {
        return listQuestion.size
    }

    override fun getItem(position: Int): Any {
        return listQuestion[position]
    }

    override fun getItemId(position: Int): Long {
        return listQuestion[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewQuestion =
            convertView ?: View.inflate(parent.context, R.layout.question_view, null)

        //Bind dữ liệu phần tử vào View
        val question = getItem(position) as Question
        (viewQuestion.findViewById<View>(R.id.id_question) as TextView).text =
            String.format("%d", position + 1)
        (viewQuestion.findViewById<View>(R.id.name_question) as TextView).text =
            String.format("%s", question.question)
        (viewQuestion.findViewById<View>(R.id.name_category) as TextView).text =
            String.format("%s", getNameCategory(question.id_category))


        //        ((TextView) viewQuestion.findViewById(R.id.name_category)).setText(String.format("%s", getNameCategory(question.getId_category())));
        return viewQuestion
    }

    private fun getNameCategory(id: Int): String? {
        for (c in listCategories
        ) {
            if (c.id == id) return c.name
        }
        return ""
    }
}
