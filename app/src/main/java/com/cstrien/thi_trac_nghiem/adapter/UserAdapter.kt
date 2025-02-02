package com.cstrien.thi_trac_nghiem.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.cstrien.thi_trac_nghiem.R
import com.cstrien.thi_trac_nghiem.model.User
import java.util.Locale

class UserAdapter(private val listUser: ArrayList<User>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return listUser.size
    }

    override fun getItem(position: Int): Any {
        return listUser[position]
    }

    override fun getItemId(position: Int): Long {
        return listUser[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewUser =
            convertView ?: View.inflate(parent.context, R.layout.user_view, null)

        //Bind dữ liệu phần tử vào View
        val user = getItem(position) as User
        (viewUser.findViewById<View>(R.id.img_user) as TextView).text =
            String.format(
                "%s", getFirstLetterName(
                    user.name!!
                )
            )
        (viewUser.findViewById<View>(R.id.user_name) as TextView).text =
            String.format("%s", user.name)
        (viewUser.findViewById<View>(R.id.password) as TextView).text =
            String.format("%s", user.password)
        (viewUser.findViewById<View>(R.id.role) as TextView).text =
            String.format("%s", getNameRole(user.role))

        return viewUser
    }

    private fun getNameRole(role: Int): String {
        if (role == 1) return "Admin"
        return "Người dùng"
    }

    private fun getFirstLetterName(user_name: String): String {
        val rs = user_name.split("".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return rs[0].uppercase(Locale.getDefault())
    }
}
