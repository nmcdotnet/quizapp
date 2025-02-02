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

class EditCategoryActivity : AppCompatActivity() {
    private var txtUserName: TextView? = null

    private var btnHome: TextView? = null
    private var edtNameCategory: EditText? = null
    private var btnEditCategory: ImageButton? = null
    private var btnBackCategory: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_category)
        //
        anhXa()
        //load câu hỏi cần sửa và tên người đăng nhập
        val intent = intent
        val user_name = intent.getStringExtra("user")
        val id_category = intent.getIntExtra("id_category", 0)
        txtUserName!!.text = "Xin chào $user_name"
        //
        val category = getCategoryById(id_category)
        edtNameCategory!!.setText(category.name)
        //
        btnBackCategory!!.setOnClickListener {
            val intent = Intent(
                this@EditCategoryActivity,
                MenuCategoryActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }

        btnEditCategory!!.setOnClickListener {
            if (validateInput()) {
                val category = Category()
                category.id = id_category
                category.name = edtNameCategory!!.text.toString().trim { it <= ' ' }
                if (updateCategory(category)) {
                    Toast.makeText(
                        this@EditCategoryActivity,
                        "Cập nhật chủ đề thành công!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else Toast.makeText(
                    this@EditCategoryActivity,
                    "Cập nhật chủ đề không thành công!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        btnHome!!.setOnClickListener {
            val intent = Intent(
                this@EditCategoryActivity,
                AdminActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }
    }

    private fun updateCategory(category: Category): Boolean {
        val db = Database(this)
        return db.updateCategory(category)
    }

    private fun getCategoryById(id: Int): Category {
        val db = Database(this)
        return db.getCategoryById(id)
    }

    private fun anhXa() {
        txtUserName = findViewById(R.id.txtUserName)
        btnEditCategory = findViewById(R.id.btnEditCategory)
        edtNameCategory = findViewById(R.id.edtNameCategory)
        btnBackCategory = findViewById(R.id.btnBackCategory)
        btnHome = findViewById(R.id.btnHome)
    }

    private fun validateInput(): Boolean {
        if (edtNameCategory!!.text.toString().trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(this@EditCategoryActivity, "Chưa nhập tên chủ đề!", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }
}
