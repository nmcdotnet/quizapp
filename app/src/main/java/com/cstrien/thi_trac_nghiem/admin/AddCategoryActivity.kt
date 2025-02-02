package com.cstrien.thi_trac_nghiem.admin

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cstrien.thi_trac_nghiem.Database
import com.cstrien.thi_trac_nghiem.R
import com.cstrien.thi_trac_nghiem.model.Category

class AddCategoryActivity : AppCompatActivity() {
    private var txtUserName: TextView? = null

    private var btnHome: TextView? = null
    private var btnCreateCategory: ImageButton? = null
    private var edtNameCategory: EditText? = null
    private var btnBackCategory: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)
        //
        anhXa()
        //
        val intent = intent
        val user_name = intent.getStringExtra("user")
        txtUserName!!.text = "Xin chào $user_name"

        //
        btnCreateCategory!!.setOnClickListener {
            if (validateInput()) {
                val category = Category()
                category.name = edtNameCategory!!.text.toString().trim { it <= ' ' }
                if (addCategory(category)) {
                    Toast.makeText(
                        this@AddCategoryActivity,
                        "Thêm chủ đề thành công!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else Toast.makeText(
                    this@AddCategoryActivity,
                    "Thêm chủ đề không thành công!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        //
        btnBackCategory!!.setOnClickListener {
            val intent = Intent(
                this@AddCategoryActivity,
                MenuCategoryActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }
        btnHome!!.setOnClickListener {
            val intent = Intent(
                this@AddCategoryActivity,
                AdminActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }
    }

    private fun addCategory(category: Category): Boolean {
        val db = Database(this)
        return db.addCategory(category)
    }

    private fun anhXa() {
        txtUserName = findViewById(R.id.txtUserName)
        btnBackCategory = findViewById(R.id.btnBackCategory)
        btnHome = findViewById(R.id.btnHome)
        btnCreateCategory = findViewById(R.id.btnCreateCategory)
        edtNameCategory = findViewById(R.id.edtNameCategory)
    }


    private fun validateInput(): Boolean {
        if (edtNameCategory!!.text.toString().trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(this@AddCategoryActivity, "Chưa nhập tên chủ đề!", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }
}
