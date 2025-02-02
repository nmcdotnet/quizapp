package com.cstrien.thi_trac_nghiem

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cstrien.thi_trac_nghiem.admin.AdminActivity
import com.cstrien.thi_trac_nghiem.model.User
import com.cstrien.thi_trac_nghiem.user.UserActivity

class LoginActivity : AppCompatActivity() {
    private var btnDangNhap: TextView? = null
    private var btnDangKy: TextView? = null
    private var edtTaiKhoan: EditText? = null
    private var edtMatKhau: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //
        anhXa()
        //
        btnDangKy!!.setOnClickListener {
            val intent = Intent(
                this@LoginActivity,
                SignUpActivity::class.java
            )
            startActivity(intent)
        }
        btnDangNhap!!.setOnClickListener {
            if (validateInput()) {
                val name = edtTaiKhoan!!.text.toString()
                val pass = edtMatKhau!!.text.toString()
                val user = checkLogin(name, pass)
                if (user!!.name != null) {
                    if (user.role == 1) {
                        val intent = Intent(
                            this@LoginActivity,
                            AdminActivity::class.java
                        )
                        intent.putExtra("user", name)
                        startActivity(intent)
                    } else {
                        val intent = Intent(
                            this@LoginActivity,
                            UserActivity::class.java
                        )
                        intent.putExtra("user", name)
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Tài khoản hoặc mật khẩu không chính xác!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun checkLogin(name: String, pass: String): User? {
        val db = Database(this)
        val user = db.getUserByNameAndPass(name, pass)
        if (user != null) return user
        return null
    }

    private fun anhXa() {
        btnDangNhap = findViewById(R.id.btnDangNhap)
        btnDangKy = findViewById(R.id.btnDangKy)
        edtTaiKhoan = findViewById(R.id.edtTaiKhoan)
        edtMatKhau = findViewById(R.id.edtMatKhau)
    }

    private fun validateInput(): Boolean {
        if (edtTaiKhoan!!.text.toString().trim { it <= ' ' }.isEmpty()) {
            setEditTextError(edtTaiKhoan!!, "Chưa nhập tài khoản!")
            return false
        } else if (edtMatKhau!!.text.toString().trim { it <= ' ' }.isEmpty()) {
            setEditTextError(edtMatKhau!!, "Chưa nhập mật khẩu!")
            return false
        }
        return true
    }


    private fun setEditTextError(editText: EditText, error: String) {
        editText.requestFocus()
        editText.error = error
    }
}