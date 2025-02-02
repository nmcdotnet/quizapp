package com.cstrien.thi_trac_nghiem.user

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cstrien.thi_trac_nghiem.Database
import com.cstrien.thi_trac_nghiem.LoginActivity
import com.cstrien.thi_trac_nghiem.R
import com.cstrien.thi_trac_nghiem.model.Category
import com.cstrien.thi_trac_nghiem.model.Score
import com.cstrien.thi_trac_nghiem.model.User

class HistoryActivity : AppCompatActivity() {
    private var btnDangXuat: TextView? = null
    private var btnHome: TextView? = null
    private var txtUserName: TextView? = null
    private var spCategory: Spinner? = null
    private var lvHistory: ListView? = null

    private var listScore: ArrayList<Score>? = null
    private var listCategories: ArrayList<Category>? = null
    private var listUsers: ArrayList<User>? = null


    private var user_name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        //
        anhXa()
        //
        btnDangXuat!!.setOnClickListener {
            val intent = Intent(
                this@HistoryActivity,
                LoginActivity::class.java
            )
            startActivity(intent)
        }
        spCategory!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                loadHistory()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        btnHome!!.setOnClickListener {
            val intent = Intent(
                this@HistoryActivity,
                UserActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }
    }

    private fun anhXa() {
        btnDangXuat = findViewById(R.id.btnDangXuat)
        btnHome = findViewById(R.id.btnHome)
        txtUserName = findViewById(R.id.txtUserName)
        spCategory = findViewById(R.id.spCategory)
        lvHistory = findViewById(R.id.lvHistory)

        val intent = intent
        user_name = intent.getStringExtra("user")
        txtUserName!!.setText(user_name)

        loadCategories()
        loadHistory()
    }

    private fun loadHistory() {
        val category = spCategory!!.selectedItem as Category

        val db = Database(this)
        listScore = db.getListScore(category.id, user_name)
        listCategories = db.getListCategories(null)
        listUsers = db.getListUsers(null)
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
