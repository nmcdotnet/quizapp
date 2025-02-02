package com.cstrien.thi_trac_nghiem.user

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cstrien.thi_trac_nghiem.R

class InstructActivity : AppCompatActivity() {
    private var btnHome: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruct)
        addControls()
        btnHome!!.setOnClickListener {
            val intent = Intent(
                this@InstructActivity,
                UserActivity::class.java
            )
            startActivity(intent)
        }
    }

    private fun addControls() {
        btnHome = findViewById(R.id.btnHome)
    }
}