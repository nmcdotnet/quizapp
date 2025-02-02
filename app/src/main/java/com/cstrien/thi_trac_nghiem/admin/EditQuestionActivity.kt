package com.cstrien.thi_trac_nghiem.admin

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cstrien.thi_trac_nghiem.Database
import com.cstrien.thi_trac_nghiem.R
import com.cstrien.thi_trac_nghiem.model.Category
import com.cstrien.thi_trac_nghiem.model.Question

class EditQuestionActivity : AppCompatActivity() {
    private var txtUserName: TextView? = null
    private var btnHome: TextView? = null
    private var spCategory: Spinner? = null
    private var btnEditQuestion: ImageButton? = null
    private var btnBackQuestion: ImageButton? = null
    private var edtNameQuestion: EditText? = null
    private var edtOption1: EditText? = null
    private var edtOption2: EditText? = null
    private var edtOption3: EditText? = null
    private var edtOption4: EditText? = null
    private var edtAnswer: EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_question)
        //
        anhXa()
        loadCategories()
        //load câu hỏi cần sửa và tên người đăng nhập
        val intent = intent
        val user_name = intent.getStringExtra("user")
        val id_question = intent.getIntExtra("id_question", 0)
        txtUserName!!.text = "Xin chào $user_name"
        val q = getQuestionById(id_question)
        //
        edtNameQuestion!!.setText(q.question)
        edtAnswer!!.setText(q.answer.toString())
        edtOption1!!.setText(removeLetter(q.option1!!))
        edtOption2!!.setText(removeLetter(q.option2!!))
        edtOption3!!.setText(removeLetter(q.option3!!))
        edtOption4!!.setText(removeLetter(q.option4!!))
        //
        val id_cate = q.id_category
        for (i in 0 until spCategory!!.count) {
            val category = spCategory!!.getItemAtPosition(i) as Category
            if (id_cate == category.id) {
                spCategory!!.setSelection(i)
            }
        }
        //
        btnBackQuestion!!.setOnClickListener {
            val intent = Intent(
                this@EditQuestionActivity,
                MenuQuestionActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }

        btnEditQuestion!!.setOnClickListener {
            if (validateInput()) {
                val cate = spCategory!!.selectedItem as Category
                val q = Question()
                q.id = id_question
                q.question = edtNameQuestion!!.text.toString().trim { it <= ' ' }
                q.option1 = "A. " + edtOption1!!.text.toString().trim { it <= ' ' }
                q.option2 = "B. " + edtOption2!!.text.toString().trim { it <= ' ' }
                q.option3 = "C. " + edtOption3!!.text.toString().trim { it <= ' ' }
                q.option4 = "D. " + edtOption4!!.text.toString().trim { it <= ' ' }
                q.answer = edtAnswer!!.text.toString().toInt()
                q.id_category = cate.id
                if (updateQuestion(q)) {
                    Toast.makeText(
                        this@EditQuestionActivity,
                        "Cập nhật câu hỏi thành công!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else Toast.makeText(
                    this@EditQuestionActivity,
                    "Cập nhật câu hỏi không thành công!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        btnHome!!.setOnClickListener {
            val intent = Intent(
                this@EditQuestionActivity,
                AdminActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }
    }

    private fun updateQuestion(q: Question): Boolean {
        val db = Database(this)
        return db.updateQuestion(q)
    }

    private fun getQuestionById(id: Int): Question {
        val db = Database(this)
        return db.getQuestionById(id)
    }

    private fun anhXa() {
        txtUserName = findViewById(R.id.txtUserName)
        spCategory = findViewById(R.id.spCategory)
        btnEditQuestion = findViewById(R.id.btnEditQuestion)
        btnBackQuestion = findViewById(R.id.btnBackQuestion)
        btnHome = findViewById(R.id.btnHome)
        edtNameQuestion = findViewById(R.id.edtNameQuestion)
        edtOption1 = findViewById(R.id.edtOption1)
        edtOption2 = findViewById(R.id.edtOption2)
        edtOption3 = findViewById(R.id.edtOption3)
        edtOption4 = findViewById(R.id.edtOption4)
        edtAnswer = findViewById(R.id.edtAnswer)
    }

    private fun loadCategories() {
        val db = Database(this)
        val listCategories: List<Category> = db.getListCategories(null)
        // tạo adapter
        val categoryArrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, listCategories)
        // bố cục
        categoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // gắn chủ đề lên spinner hiển thị
        spCategory!!.adapter = categoryArrayAdapter
    }

    private fun validateInput(): Boolean {
        if (edtNameQuestion!!.text.toString().trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(this@EditQuestionActivity, "Chưa nhập câu hỏi!", Toast.LENGTH_SHORT)
                .show()
            return false
        } else if ((edtOption1!!.text.toString().trim { it <= ' ' }
                .isEmpty() or edtOption2!!.text.toString().trim { it <= ' ' }.isEmpty()
                    or edtOption3!!.text.toString().trim { it <= ' ' }
                .isEmpty() or edtOption4!!.text.toString().trim { it <= ' ' }.isEmpty())
        ) {
            Toast.makeText(this@EditQuestionActivity, "Chưa nhập đủ đáp án!", Toast.LENGTH_SHORT)
                .show()
            return false
        } else if (edtAnswer!!.text.toString().trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(this@EditQuestionActivity, "Chưa nhập đáp án đúng!", Toast.LENGTH_SHORT)
                .show()
            return false
        } else if ((edtAnswer!!.text.toString().trim { it <= ' ' }
                .toInt() < 1) or (edtAnswer!!.text.toString().trim { it <= ' ' }.toInt() > 4)) {
            Toast.makeText(this@EditQuestionActivity, "Đáp án chỉ từ 1 đến 4!", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }

    private fun removeLetter(option: String): String {
        return option.substring(3, option.length)
    }
}
