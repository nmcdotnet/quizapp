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

class ChangePassActivity : AppCompatActivity() {
    private var btnBack: TextView? = null
    private var btnDoiMatKhau: TextView? = null

    private var edtTaiKhoan: EditText? = null
    private var edtMatKhau: EditText? = null
    private var edtNhapLaiMatKhauMoi: EditText? = null
    private var edtMatKhauMoi: EditText? = null

    private var user_name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pass)
        //
        anhXa()
        //
        val intent = intent
        user_name = intent.getStringExtra("user")
        edtTaiKhoan!!.setText(user_name)
        //
        btnDoiMatKhau!!.setOnClickListener {
            if (validateInput()) {
                val pass = edtMatKhauMoi!!.text.toString().trim { it <= ' ' }

                if (changePass(user_name!!, pass)) {
                    Toast.makeText(
                        this@ChangePassActivity,
                        "Đổi mật khẩu thành công!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else Toast.makeText(
                    this@ChangePassActivity,
                    "Đổi mật khẩu không thành công!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        btnBack!!.setOnClickListener {
            if (getUserByName(user_name!!).role == 1) {
                val intent = Intent(
                    this@ChangePassActivity,
                    AdminActivity::class.java
                )
                intent.putExtra("user", user_name)
                startActivity(intent)
            } else {
                val intent = Intent(
                    this@ChangePassActivity,
                    UserActivity::class.java
                )
                intent.putExtra("user", user_name)
                startActivity(intent)
            }
        }
    }

    private fun anhXa() {
        btnBack = findViewById(R.id.btnBack)
        btnDoiMatKhau = findViewById(R.id.btnDoiMatKhau)
        edtTaiKhoan = findViewById(R.id.edtTaiKhoan)
        edtMatKhau = findViewById(R.id.edtMatKhau)
        edtMatKhauMoi = findViewById(R.id.edtMatKhauMoi)
        edtNhapLaiMatKhauMoi = findViewById(R.id.edtNhapLaiMatKhauMoi)
    }

    private fun getUserByName(user_name: String): User {
        val db = Database(this)
        return db.getUserByName(user_name)
    }

    private fun changePass(user_name: String, pass: String): Boolean {
        val db = Database(this)
        return db.changePass(user_name, pass)
    }

    private fun validateInput(): Boolean {
        val db = Database(this)
        val user = db.getUserByNameAndPass(edtTaiKhoan!!.text.toString().trim { it <= ' ' },
            edtMatKhau!!.text.toString().trim { it <= ' ' })
        if (edtTaiKhoan!!.text.toString().trim { it <= ' ' }.isEmpty()) {
            setEditTextError(edtTaiKhoan!!, "Chưa nhập tài khoản!")
            return false
        } else if (edtMatKhau!!.text.toString().trim { it <= ' ' }.isEmpty()) {
            setEditTextError(edtMatKhau!!, "Chưa nhập mật khẩu cũ!")
            return false
        } else if (edtMatKhauMoi!!.text.toString().trim { it <= ' ' }.isEmpty()) {
            setEditTextError(edtMatKhauMoi!!, "Chưa nhập mật khẩu mới!")
            return false
        } else if (edtNhapLaiMatKhauMoi!!.text.toString().trim { it <= ' ' }.isEmpty()) {
            setEditTextError(edtNhapLaiMatKhauMoi!!, "Chưa nhập lại mật khẩu mới!")
            return false
        } else if (edtMatKhauMoi!!.text.toString().trim { it <= ' ' }.length < 3) {
            setEditTextError(edtMatKhauMoi!!, "Mật khẩu mới phải có ít nhất 3 kí tự!")
            return false
        } else if (!edtNhapLaiMatKhauMoi!!.text.toString().trim { it <= ' ' }.equals(
                edtMatKhauMoi!!.text.toString().trim { it <= ' ' }, ignoreCase = true
            )
        ) {
            setEditTextError(edtNhapLaiMatKhauMoi!!, "Mật khẩu nhập lại không trùng khớp!")
            return false
        } else if (user.name == null) {
            setEditTextError(edtTaiKhoan!!, "Tài khoản hoặc mật khẩu cũ không chính xác!")
            return false
        }
        return true
    }


    private fun setEditTextError(editText: EditText, error: String) {
        editText.requestFocus()
        editText.error = error
    }
}