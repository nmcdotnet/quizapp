package com.cstrien.thi_trac_nghiem

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cstrien.thi_trac_nghiem.model.User

class SignUpActivity : AppCompatActivity() {
    private var btnBack: TextView? = null
    private var btnDangKy: TextView? = null
    private var edtTaiKhoan: EditText? = null
    private var edtMatKhau: EditText? = null
    private var edtNhapLaiMatKhau: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        //
        anhXa()
        //
        btnDangKy!!.setOnClickListener {
            if (validateInput()) {
                val user = User()
                user.name = edtTaiKhoan!!.text.toString()
                user.password = edtMatKhau!!.text.toString()
                user.role = 0
                //                    Toast.makeText(SignUpActivity.this, "TK" + , Toast.LENGTH_SHORT).show();
                if (signUp(user)) {
                    Toast.makeText(
                        this@SignUpActivity,
                        "Đăng ký thành công!",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(
                        this@SignUpActivity,
                        LoginActivity::class.java
                    )
                    startActivity(intent)
                    // Đóng SignUpActivity để người dùng không thể quay lại từ LoginActivity
                    finish()
                } else Toast.makeText(
                    this@SignUpActivity,
                    "Đăng ký không thành công!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        btnBack!!.setOnClickListener {
            val intent = Intent(
                this@SignUpActivity,
                LoginActivity::class.java
            )
            startActivity(intent)
        }
    }

    private fun signUp(user: User): Boolean {
        val db = Database(this)
        return db.signUp(user)
    }

    private fun anhXa() {
        btnBack = findViewById(R.id.btnBack)
        btnDangKy = findViewById(R.id.btnDangKy)
        edtTaiKhoan = findViewById(R.id.edtTaiKhoan)
        edtMatKhau = findViewById(R.id.edtMatKhau)
        edtNhapLaiMatKhau = findViewById(R.id.edtNhapLaiMatKhau)
    }

    private fun validateInput(): Boolean {
        val taiKhoan = edtTaiKhoan!!.text.toString().trim { it <= ' ' }
        val matKhau = edtMatKhau!!.text.toString().trim { it <= ' ' }
        val nhapLaiMatKhau = edtNhapLaiMatKhau!!.text.toString().trim { it <= ' ' }

        if (taiKhoan.isEmpty()) {
            Toast.makeText(this@SignUpActivity, "Chưa nhập tài khoản!", Toast.LENGTH_SHORT).show()
            return false
        } else if (matKhau.isEmpty()) {
            setEditTextError(edtMatKhau!!, "Chưa nhập mật khẩu!")
            return false
        } else if (nhapLaiMatKhau.isEmpty()) {
            setEditTextError(edtNhapLaiMatKhau!!, "Chưa nhập lại mật khẩu!")
            return false
        } else if (matKhau.length < 3) {
            setEditTextError(edtMatKhau!!, "Mật khẩu phải có ít nhất 3 kí tự!")
            return false
        } else if (!nhapLaiMatKhau.equals(matKhau, ignoreCase = true)) {
            setEditTextError(edtNhapLaiMatKhau!!, "Mật khẩu nhập lại không trùng khớp!")
            return false
        } else if (!checkExistUser(taiKhoan)) {
            Toast.makeText(
                this@SignUpActivity,
                "Tên tài khoản đã được sử dụng!",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }


    private fun checkExistUser(name: String): Boolean {
        val db = Database(this)
        if (db.getUserByName(name).name != null) return false
        return true
    }

    private fun setEditTextError(editText: EditText, error: String) {
        editText.requestFocus()
        editText.error = error
    }
}