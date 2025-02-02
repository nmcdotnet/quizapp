package com.cstrien.thi_trac_nghiem.user

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cstrien.thi_trac_nghiem.Database
import com.cstrien.thi_trac_nghiem.R
import com.cstrien.thi_trac_nghiem.model.Category

class MainActivity : AppCompatActivity() {
    private var btnHome: TextView? = null
    private var txtUserName: TextView? = null
    private var spCategory: Spinner? = null
    private val btnGuide: TextView? = null
    private var btnStart: TextView? = null

    private var btnBack: TextView? = null


    private val listCategories: ArrayList<Category>? = null

    private var user_name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //
        addControls()

        //
        btnHome!!.setOnClickListener {
            val intent = Intent(
                this@MainActivity,
                UserActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }
        btnStart!!.setOnClickListener { startQuestion() }
        btnBack!!.setOnClickListener {
            val intent = Intent(
                this@MainActivity,
                UserActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }
    }

    private fun addControls() {
        btnHome = findViewById(R.id.btnHome)
        txtUserName = findViewById(R.id.txtUserName)
        spCategory = findViewById(R.id.spCategory)
        btnStart = findViewById(R.id.btnStart)
        btnBack = findViewById(R.id.btnBack)

        val intent = intent
        user_name = intent.getStringExtra("user")
        txtUserName!!.setText(user_name)

        loadCategories()
    }

    private fun startQuestion() {
        val category = spCategory!!.selectedItem as Category
        val intent = Intent(
            this@MainActivity,
            QuestionActivity::class.java
        )
        val categoryName = category.name
        val categoryID = category.id
        intent.putExtra("user", user_name)
        intent.putExtra("categoryName", categoryName)
        intent.putExtra("categoryID", categoryID)
        startActivity(intent)
    }

    private fun loadCategories() {
        val db = Database(this)
        val listCategories = db.getListCategories(null)
        // tạo adapter
        val categoryArrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, listCategories)
        // bố cục
        categoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // gắn chủ đề lên spinner hiển thị
        spCategory!!.adapter = categoryArrayAdapter
    }
}
