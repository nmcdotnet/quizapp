package com.cstrien.thi_trac_nghiem.admin

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cstrien.thi_trac_nghiem.ChangePassActivity
import com.cstrien.thi_trac_nghiem.LoginActivity
import com.cstrien.thi_trac_nghiem.R

class AdminActivity : AppCompatActivity() {
    private var txtUserName: TextView? = null
    private var btnTaiKhoan: TextView? = null
    private var btnDangXuat: TextView? = null
    private var btnCauHoi: TextView? = null
    private var btnChuDe: TextView? = null
    private var btnDoiMatKhau: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        //
        anhXa()
        //
        val intent = intent
        val user_name = intent.getStringExtra("user")
        txtUserName!!.text = "Xin chào $user_name"
        //
        btnDangXuat!!.setOnClickListener {
            val intent = Intent(
                this@AdminActivity,
                LoginActivity::class.java
            )
            startActivity(intent)
        }
        //
        btnCauHoi!!.setOnClickListener {
            val intent = Intent(
                this@AdminActivity,
                MenuQuestionActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }
        //
        btnChuDe!!.setOnClickListener {
            val intent = Intent(
                this@AdminActivity,
                MenuCategoryActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }
        //
        btnDoiMatKhau!!.setOnClickListener {
            val intent = Intent(
                this@AdminActivity,
                ChangePassActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }
        //
        btnTaiKhoan!!.setOnClickListener {
            val intent = Intent(
                this@AdminActivity,
                MenuUserActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }
    }

    private fun anhXa() {
        txtUserName = findViewById(R.id.txtUserName)
        btnCauHoi = findViewById(R.id.btnCauHoi)
        btnChuDe = findViewById(R.id.btnChuDe)
        btnDangXuat = findViewById(R.id.btnDangXuat)
        btnTaiKhoan = findViewById(R.id.btnTaiKhoan)
        btnDoiMatKhau = findViewById(R.id.btnDoiMatKhau)
    }
}