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
import com.cstrien.thi_trac_nghiem.R
import com.cstrien.thi_trac_nghiem.adapter.CategoryAdapter
import com.cstrien.thi_trac_nghiem.model.Category

class MenuCategoryActivity : AppCompatActivity() {
    private var txtUserName: TextView? = null

    private var btnHome: TextView? = null
    private var btnAddCategory: ImageButton? = null
    private var btnClear: TextView? = null
    private var edtSearch: EditText? = null

    // khai báo listView
    private var lvCategory: ListView? = null
    private var listCategories: ArrayList<Category>? = null
    private var categoryAdapter: CategoryAdapter? = null
    private var user_name: String? = null

    private var mLastClickTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_category)
        //
        anhXa()
        loadCategories(null)
        //
        val intent = intent
        user_name = intent.getStringExtra("user")
        txtUserName!!.text = "Xin chào $user_name"


        //
        btnAddCategory!!.setOnClickListener {
            val intent = Intent(
                this@MenuCategoryActivity,
                AddCategoryActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }
        //sự kiện nhấn vào item listView
        lvCategory!!.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val currTime = System.currentTimeMillis()
                if (currTime - mLastClickTime < ViewConfiguration.getDoubleTapTimeout()) {
                    onItemDoubleClick(parent, view, position, id)
                }
                mLastClickTime = currTime
            }

            fun onItemDoubleClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val id_category = listCategories!![position].id
                val intent =
                    Intent(
                        this@MenuCategoryActivity,
                        EditCategoryActivity::class.java
                    )
                intent.putExtra("user", user_name)
                intent.putExtra("id_category", id_category)
                startActivity(intent)
            }
        }
        lvCategory!!.onItemLongClickListener =
            OnItemLongClickListener { parent, view, position, id ->
                DialogDelete(position)
                false
            }
        btnHome!!.setOnClickListener {
            val intent = Intent(
                this@MenuCategoryActivity,
                AdminActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }
        edtSearch!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val search = edtSearch!!.text.toString().trim { it <= ' ' }
                if (!search.equals("", ignoreCase = true)) {
                    loadCategories(search)
                } else {
                    loadCategories(null)
                }
                if (edtSearch!!.text.toString().equals("", ignoreCase = true)) {
                    btnClear!!.setBackgroundResource(R.drawable.search)
                } else {
                    btnClear!!.setBackgroundResource(R.drawable.clear)
                    btnClear!!.setOnClickListener {
                        edtSearch!!.setText(
                            ""
                        )
                    }
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
            val id_category = listCategories!![position].id
            //xóa dữ liệu
            if (deleteCategory(id_category)) {
                loadCategories(null)
                dialog.cancel()
                Toast.makeText(
                    this@MenuCategoryActivity,
                    "Xóa thành công",
                    Toast.LENGTH_SHORT
                ).show()
            } else Toast.makeText(
                this@MenuCategoryActivity,
                "Xóa không thành công",
                Toast.LENGTH_SHORT
            ).show()
        }

        //set sự kiện click NO
        btnNo.setOnClickListener { dialog.cancel() }
        //thực hiện dialog
        dialog.show()
    }


    private fun deleteCategory(id_category: Int): Boolean {
        val db = Database(this)
        return db.deleteCategory(id_category)
    }

    private fun anhXa() {
        txtUserName = findViewById(R.id.txtUserName)
        lvCategory = findViewById(R.id.lvCategory)

        btnAddCategory = findViewById(R.id.btnAddCategory)
        btnHome = findViewById(R.id.btnHome)
        btnClear = findViewById(R.id.btnClear)

        edtSearch = findViewById(R.id.edtSearch)
    }

    private fun loadCategories(key: String?) {
        val db = Database(this)
        listCategories = db.getListCategories(key)

        categoryAdapter = CategoryAdapter(listCategories!!)

        lvCategory!!.adapter = categoryAdapter
    }
}
