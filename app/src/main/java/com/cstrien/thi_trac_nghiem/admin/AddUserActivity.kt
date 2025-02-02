package com.cstrien.thi_trac_nghiem.admin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cstrien.thi_trac_nghiem.Database
import com.cstrien.thi_trac_nghiem.R
import com.cstrien.thi_trac_nghiem.model.User

class AddUserActivity : AppCompatActivity() {
    private var txtUserName: TextView? = null

    private var btnHome: TextView? = null
    private var btnCreateUser: ImageButton? = null
    private var edtTaiKhoan: EditText? = null
    private var edtMatKhau: EditText? = null
    private var btnBackUser: ImageButton? = null
    private var rdoGroup: RadioGroup? = null
    private var rdoAdmin: RadioButton? = null
    private var rdoUser: RadioButton? = null
    private var user_name: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        //
        addControls()
        val intent = intent

        user_name = intent.getStringExtra("user")
        txtUserName!!.text = "Xin chào $user_name"


        //
        btnCreateUser!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                if (validateInput()) {
                    val user = User()
                    user.name = edtTaiKhoan!!.text.toString().trim { it <= ' ' }
                    user.password = edtMatKhau!!.text.toString().trim { it <= ' ' }
                    user.role = role
                    if (addUser(user)) {
                        Toast.makeText(
                            this@AddUserActivity,
                            "Tạo tài khoản thành công!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else Toast.makeText(
                        this@AddUserActivity,
                        "Tạo tài khoản không thành công!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
        btnBackUser!!.setOnClickListener {
            val intent = Intent(
                this@AddUserActivity,
                MenuUserActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }
        btnHome!!.setOnClickListener {
            val intent = Intent(
                this@AddUserActivity,
                AdminActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }
    }

    private fun addControls() {
        btnBackUser = findViewById(R.id.btnBackUser)
        btnCreateUser = findViewById(R.id.btnCreateUser)
        btnHome = findViewById(R.id.btnHome)
        txtUserName = findViewById(R.id.txtUserName)
        edtMatKhau = findViewById(R.id.edtMatKhau)
        edtTaiKhoan = findViewById(R.id.edtTaiKhoan)
        rdoAdmin = findViewById(R.id.rdoAdmin)
        rdoUser = findViewById(R.id.rdoUser)
        rdoGroup = findViewById(R.id.rdoGroup)
        btnHome = findViewById(R.id.btnHome)

        rdoUser!!.isChecked = true
    }

    private fun addUser(user: User): Boolean {
        val db = Database(this)
        return db.signUp(user)
    }

    private fun validateInput(): Boolean {
        val taiKhoan = edtTaiKhoan!!.text.toString().trim { it <= ' ' }
        val matKhau = edtMatKhau!!.text.toString().trim { it <= ' ' }

        if (taiKhoan.isEmpty()) {
            Toast.makeText(this@AddUserActivity, "Chưa nhập tài khoản!", Toast.LENGTH_SHORT).show()
            return false
        } else if (matKhau.isEmpty()) {
            Toast.makeText(this@AddUserActivity, "Chưa nhập mật khẩu!", Toast.LENGTH_SHORT).show()
            return false
        } else if (matKhau.length < 3) {
            Toast.makeText(
                this@AddUserActivity,
                "Mật khẩu phải có ít nhất 3 kí tự!",
                Toast.LENGTH_SHORT
            ).show()
            return false
        } else if (!checkExistUser(taiKhoan)) {
            Toast.makeText(
                this@AddUserActivity,
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

    private val role: Int
        get() {
            if (rdoAdmin!!.isChecked) return 1
            return 0
        }
}
