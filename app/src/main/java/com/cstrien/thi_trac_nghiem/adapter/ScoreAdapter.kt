package com.cstrien.thi_trac_nghiem.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.cstrien.thi_trac_nghiem.R
import com.cstrien.thi_trac_nghiem.model.Category
import com.cstrien.thi_trac_nghiem.model.Score
import com.cstrien.thi_trac_nghiem.model.User
import java.util.Locale

class ScoreAdapter //    private ArrayList<Question> listQuestions;
    (
    private val listScore: ArrayList<Score>,
    private val listCategories: ArrayList<Category>,
    private val listUsers: ArrayList<User>
) :
    BaseAdapter() {
    override fun getCount(): Int {
        return listScore.size
    }

    override fun getItem(position: Int): Any {
        return listScore[position]
    }

    override fun getItemId(position: Int): Long {
        return listScore[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewScore =
            convertView ?: View.inflate(parent.context, R.layout.rank_view, null)

        //Bind dữ liệu phần tử vào View
        val score = getItem(position) as Score
        (viewScore.findViewById<View>(R.id.img_user) as TextView).text =
            String.format(
                "%s", getFirstLetterName(
                    getNameUser(score.id_user)!!
                )
            )
        (viewScore.findViewById<View>(R.id.rank) as TextView).text =
            String.format("%d", position + 1)
        (viewScore.findViewById<View>(R.id.score) as TextView).text =
            String.format("%d", score.score)
        (viewScore.findViewById<View>(R.id.name_category) as TextView).text =
            String.format("%s", getNameCategory(score.id_category))
        (viewScore.findViewById<View>(R.id.user_name) as TextView).text =
            String.format("%s", getNameUser(score.id_user))


        return viewScore
    }

    private fun getNameCategory(id: Int): String? {
        for (c in listCategories
        ) {
            if (c.id == id) return c.name
        }
        return ""
    }

    private fun getNameUser(id: Int): String? {
        for (u in listUsers
        ) {
            if (u.id == id) return u.name
        }
        return ""
    }

    private fun getFirstLetterName(user_name: String): String {
        val rs = user_name.split("".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return rs[0].uppercase(Locale.getDefault())
    }
}
