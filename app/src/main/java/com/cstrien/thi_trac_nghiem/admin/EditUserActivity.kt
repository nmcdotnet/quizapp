package com.cstrien.thi_trac_nghiem.admin

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cstrien.thi_trac_nghiem.Database
import com.cstrien.thi_trac_nghiem.R
import com.cstrien.thi_trac_nghiem.model.User

class EditUserActivity : AppCompatActivity() {
    private var txtUserName: TextView? = null

    private var btnHome: TextView? = null

    private var spCategory: Spinner? = null
    private var btnEditUser: ImageButton? = null
    private var btnBackUser: ImageButton? = null
    private var edtTaiKhoan: EditText? = null
    private var edtMatKhau: EditText? = null
    private var rdoGroup: RadioGroup? = null
    private var rdoAdmin: RadioButton? = null
    private var rdoUser: RadioButton? = null
    private var user_name: String? = null
    private var id_user = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)
        //
        anhXa()

        //
        btnBackUser!!.setOnClickListener {
            val intent = Intent(
                this@EditUserActivity,
                MenuUserActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }

        btnEditUser!!.setOnClickListener {
            if (validateInput()) {
                val user = User()
                user.id = id_user
                user.name = edtTaiKhoan!!.text.toString().trim { it <= ' ' }
                user.password = edtMatKhau!!.text.toString().trim { it <= ' ' }

                if (rdoAdmin!!.isChecked) user.role = 1
                else user.role = 0

                if (updateUser(user)) {
                    Toast.makeText(
                        this@EditUserActivity,
                        "Cập nhật tài khoản thành công!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else Toast.makeText(
                    this@EditUserActivity,
                    "Cập nhật tài khoản không thành công!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        btnHome!!.setOnClickListener {
            val intent = Intent(
                this@EditUserActivity,
                AdminActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }
    }


    private fun anhXa() {
        txtUserName = findViewById(R.id.txtUserName)
        spCategory = findViewById(R.id.spCategory)

        btnEditUser = findViewById(R.id.btnEditUser)
        btnBackUser = findViewById(R.id.btnBackUser)
        edtMatKhau = findViewById(R.id.edtMatKhau)
        edtTaiKhoan = findViewById(R.id.edtTaiKhoan)
        rdoAdmin = findViewById(R.id.rdoAdmin)
        rdoUser = findViewById(R.id.rdoUser)
        rdoGroup = findViewById(R.id.rdoGroup)
        btnHome = findViewById(R.id.btnHome)

        val intent = intent
        user_name = intent.getStringExtra("user")
        id_user = intent.getIntExtra("id_user", 0)
        txtUserName!!.setText("Xin chào $user_name")
        val u = getUserById(id_user)
        edtTaiKhoan!!.setText(u.name)
        edtMatKhau!!.setText(u.password)
        if (u.role == 1) rdoAdmin!!.setChecked(true)
        else rdoUser!!.setChecked(true)
    }

    private fun updateUser(user: User): Boolean {
        val db = Database(this)
        return db.updateUser(user)
    }

    private fun validateInput(): Boolean {
        val matKhau = edtMatKhau!!.text.toString().trim { it <= ' ' }

        if (matKhau.isEmpty()) {
            Toast.makeText(this@EditUserActivity, "Chưa nhập mật khẩu!", Toast.LENGTH_SHORT).show()
            return false
        } else if (matKhau.length < 3) {
            Toast.makeText(
                this@EditUserActivity,
                "Mật khẩu phải có ít nhất 3 kí tự!",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }


    private fun getUserById(id: Int): User {
        val db = Database(this)
        return db.getUserById(id)
    }

    private fun setEditTextError(editText: EditText, error: String) {
        editText.requestFocus()
        editText.error = error
    }
}
