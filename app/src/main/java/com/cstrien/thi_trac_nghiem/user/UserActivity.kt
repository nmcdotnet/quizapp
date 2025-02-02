package com.cstrien.thi_trac_nghiem.user

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cstrien.thi_trac_nghiem.ChangePassActivity
import com.cstrien.thi_trac_nghiem.LoginActivity
import com.cstrien.thi_trac_nghiem.R

class UserActivity : AppCompatActivity() {
    private var btnReady: TextView? = null
    private var btnGuide: TextView? = null

    private var btnDoiMatKhau: TextView? = null
    private var txtUserName: TextView? = null
    private var btnDangXuat: TextView? = null


    private var user_name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        //
        anhXa()
        //
        btnReady!!.setOnClickListener {
            val intent = Intent(
                this@UserActivity,
                MainActivity::class.java
            )
            intent.putExtra("Xin chào " + "user", user_name)
            startActivity(intent)
        }
        btnDangXuat!!.setOnClickListener {
            val intent = Intent(
                this@UserActivity,
                LoginActivity::class.java
            )
            startActivity(intent)
        }
        btnDoiMatKhau!!.setOnClickListener {
            val intent = Intent(
                this@UserActivity,
                ChangePassActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }
        btnGuide!!.setOnClickListener {
            val intent = Intent(
                this@UserActivity,
                InstructActivity::class.java
            )
            startActivity(intent)
        }
    }

    private fun anhXa() {
        btnReady = findViewById(R.id.btnReady)
        btnDoiMatKhau = findViewById(R.id.btnDoiMatKhau)
        btnGuide = findViewById(R.id.btnGuide)
        btnDangXuat = findViewById(R.id.btnDangXuat)

        txtUserName = findViewById(R.id.txtUserName)

        val intent = intent
        user_name = intent.getStringExtra("user")
        txtUserName!!.setText(user_name)
    }
}
