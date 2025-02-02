package com.cstrien.thi_trac_nghiem.admin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewConfiguration
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cstrien.thi_trac_nghiem.Database
import com.cstrien.thi_trac_nghiem.LoginActivity
import com.cstrien.thi_trac_nghiem.R
import com.cstrien.thi_trac_nghiem.adapter.UserAdapter
import com.cstrien.thi_trac_nghiem.model.User

class MenuUserActivity : AppCompatActivity() {
    private var txtUserName: TextView? = null

    private var btnHome: TextView? = null
    private var btnClear: TextView? = null
    private var edtSearch: EditText? = null

    private var lvUser: ListView? = null

    private var btnAddUser: ImageButton? = null

    private var user_name: String? = null
    private var mLastClickTime: Long = 0

    //
    private var listUsers: ArrayList<User>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_user)
        //
        anhXa()
        loadUsers(null)
        //
        val intent = intent
        user_name = intent.getStringExtra("user")
        txtUserName!!.text = "Xin chào $user_name"

        //
        btnAddUser!!.setOnClickListener {
            val intent = Intent(
                this@MenuUserActivity,
                AddUserActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }
        btnHome!!.setOnClickListener {
            val intent = Intent(
                this@MenuUserActivity,
                AdminActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }
        //
        lvUser!!.onItemLongClickListener =
            OnItemLongClickListener { parent, view, position, id ->
                DialogDelete(position)
                false
            }
        lvUser!!.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                val currTime = System.currentTimeMillis()
                if (currTime - mLastClickTime < ViewConfiguration.getDoubleTapTimeout()) {
                    onItemDoubleClick(parent, view, position, id)
                }
                mLastClickTime = currTime
            }

            fun onItemDoubleClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val intent = Intent(
                    this@MenuUserActivity,
                    EditUserActivity::class.java
                )
                val id_user = listUsers!![position].id
                intent.putExtra("user", user_name)
                intent.putExtra("id_user", id_user)
                startActivity(intent)
            }
        }
        edtSearch!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val search = edtSearch!!.text.toString().trim { it <= ' ' }
                if (!search.equals("", ignoreCase = true)) {
                    loadUsers(search)
                } else {
                    loadUsers(null)
                }
                if (edtSearch!!.text.toString().equals("", ignoreCase = true)) {
                    btnClear!!.setBackgroundResource(R.drawable.search)
                } else {
                    btnClear!!.setBackgroundResource(R.drawable.clear)
                    btnClear!!.setOnClickListener { edtSearch!!.setText("") }
                }
            }

            override fun afterTextChanged(s: Editable) {
            }
        })
    }

    private fun DialogDelete(position: Int) {
        //tạo đối tượng dialog
        val dialog = Dialog(this)

        //nạp layout vào dialog
        dialog.setContentView(R.layout.dialog_delete)

        //Tăt click ra ngoài chỉ click vào NO là đóng
        dialog.setCanceledOnTouchOutside(false)

        val btnYes = dialog.findViewById<TextView>(R.id.btnYes)
        val btnNo = dialog.findViewById<TextView>(R.id.btnNo)

        //set sự kiện click vào yes
        btnYes.setOnClickListener {
            val id_user = listUsers!![position].id
            val user = listUsers!![position]
            //xóa dữ liệu
            if (deleteUser(id_user)) {
                loadUsers(null)
                dialog.cancel()
                Toast.makeText(
                    this@MenuUserActivity,
                    "Xóa thành công",
                    Toast.LENGTH_SHORT
                ).show()
                if (user.name.equals(user_name, ignoreCase = true)) {
                    val intent = Intent(
                        this@MenuUserActivity,
                        LoginActivity::class.java
                    )
                    startActivity(intent)
                }
            } else Toast.makeText(
                this@MenuUserActivity,
                "Xóa không thành công",
                Toast.LENGTH_SHORT
            ).show()
        }

        //set sự kiện click NO
        btnNo.setOnClickListener { dialog.cancel() }
        //thực hiện dialog
        dialog.show()
    }

    private fun deleteUser(id_user: Int): Boolean {
        val db = Database(this)
        return db.deleteUser(id_user)
    }

    private fun anhXa() {
        txtUserName = findViewById(R.id.txtUserName)
        btnAddUser = findViewById(R.id.btnAddUser)
        txtUserName = findViewById(R.id.txtUserName)
        btnHome = findViewById(R.id.btnHome)
        lvUser = findViewById(R.id.lvUser)
        edtSearch = findViewById(R.id.edtSearch)
        btnClear = findViewById(R.id.btnClear)
    }

    private fun loadUsers(key: String?) {
        val db = Database(this)
        listUsers = db.getListUsers(key)

        val userAdapter = UserAdapter(listUsers!!)

        lvUser!!.adapter = userAdapter
    }
}


