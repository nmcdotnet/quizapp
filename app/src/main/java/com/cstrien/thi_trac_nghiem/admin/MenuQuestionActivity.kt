package com.cstrien.thi_trac_nghiem.admin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewConfiguration
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cstrien.thi_trac_nghiem.Database
import com.cstrien.thi_trac_nghiem.R
import com.cstrien.thi_trac_nghiem.adapter.QuestionAdapter
import com.cstrien.thi_trac_nghiem.model.Category
import com.cstrien.thi_trac_nghiem.model.Question

class MenuQuestionActivity : AppCompatActivity() {
    private var txtUserName: TextView? = null

    private var btnHome: TextView? = null
    private var btnClear: TextView? = null
    private var edtSearch: EditText? = null
    private var btnAddQuestion: ImageButton? = null
    private var spCategory: Spinner? = null
    private var lvQuestion: ListView? = null
    private var listQuestion: ArrayList<Question>? = null
    private var listCategories: ArrayList<Category>? = null
    private var questionAdapter: QuestionAdapter? = null
    private var user_name: String? = null
    private var mLastClickTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_question)
        //
        anhXa()
        //
        val intent = intent
        user_name = intent.getStringExtra("user")
        txtUserName!!.text = "Xin chào $user_name"
        //load danh sách câu hỏi theo chủ đề
        spCategory!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                loadListView()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        //
        btnAddQuestion!!.setOnClickListener {
            val intent = Intent(
                this@MenuQuestionActivity,
                AddQuestionActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }
        //sự kiện nhấn vào item listView
        lvQuestion!!.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val currTime = System.currentTimeMillis()
                if (currTime - mLastClickTime < ViewConfiguration.getDoubleTapTimeout()) {
                    onItemDoubleClick(parent, view, position, id)
                }
                mLastClickTime = currTime
            }

            fun onItemDoubleClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val intent =
                    Intent(
                        this@MenuQuestionActivity,
                        EditQuestionActivity::class.java
                    )
                val id_question = listQuestion!![position].id
                intent.putExtra("user", user_name)
                intent.putExtra("id_question", id_question)
                startActivity(intent)
            }
        }
        lvQuestion!!.onItemLongClickListener =
            OnItemLongClickListener { parent, view, position, id ->
                DialogDelete(position)
                false
            }
        btnHome!!.setOnClickListener {
            val intent = Intent(
                this@MenuQuestionActivity,
                AdminActivity::class.java
            )
            intent.putExtra("user", user_name)
            startActivity(intent)
        }
        edtSearch!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val search = edtSearch!!.text.toString().trim { it <= ' ' }
                if (!search.equals("", ignoreCase = true)) {
                    loadSearch(search)
                } else {
                    loadListView()
                }
                if (edtSearch!!.text.toString().equals("", ignoreCase = true)) {
                    btnClear!!.setBackgroundResource(R.drawable.search)
                } else {
                    btnClear!!.setBackgroundResource(R.drawable.clear)
                    btnClear!!.setOnClickListener {
                        edtSearch!!.setText(
                            ""
                        )
                    }
                }
            }

            override fun afterTextChanged(s: Editable) {
            }
        })
    }

    private fun loadSearch(name: String) {
        val category = spCategory!!.selectedItem as Category
        val id_cate = category.id
        // Lấy dữ liệu câu hỏi theo chủ đề
        val db = Database(this)
        listQuestion = db.getListQuestions(name, id_cate)
        listCategories = db.getListCategories(null)
        questionAdapter = QuestionAdapter(listQuestion!!, listCategories!!)
        lvQuestion!!.adapter = questionAdapter
    }

    private fun loadListView() {
        val category = spCategory!!.selectedItem as Category
        val id_cate = category.id
        // Lấy dữ liệu câu hỏi theo chủ đề
        val db = Database(this)
        listQuestion = db.getListQuestions(null, id_cate)
        listCategories = db.getListCategories(null)
        questionAdapter = QuestionAdapter(listQuestion!!, listCategories!!)
        lvQuestion!!.adapter = questionAdapter
    }

    private fun anhXa() {
        txtUserName = findViewById(R.id.txtUserName)
        lvQuestion = findViewById(R.id.lvQuestion)
        spCategory = findViewById(R.id.spCategory)
        btnAddQuestion = findViewById(R.id.btnAddQuestion)
        btnHome = findViewById(R.id.btnHome)
        btnClear = findViewById(R.id.btnClear)

        edtSearch = findViewById(R.id.edtSearch)

        loadCategories()
        loadListView()
    }

    private fun loadCategories() {
        val db = Database(this)
        val listCate = db.getListCategories(null)
        // tạo adapter
        val categoryArrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, listCate)
        // bố cục
        categoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // gắn chủ đề lên spinner hiển thị
        spCategory!!.adapter = categoryArrayAdapter
    }

    private fun DialogDelete(position: Int) {
        //tạo đối tượng dialog
        val dialog = Dialog(this)

        //nạp layout vào dialog
        dialog.setContentView(R.layout.dialog_delete)

        //Tăt click ra ngoài chỉ click vào NO là đóng
        dialog.setCanceledOnTouchOutside(false)

        val btnYes = dialog.findViewById<TextView>(R.id.btnYes)
        val btnNo = dialog.findViewById<TextView>(R.id.btnNo)

        //set sự kiện click vào yes
        btnYes.setOnClickListener {
            val id_question = listQuestion!![position].id
            //xóa dữ liệu
            if (deleteQuestion(id_question)) {
                loadListView()
                dialog.cancel()
                Toast.makeText(
                    this@MenuQuestionActivity,
                    "Xóa thành công",
                    Toast.LENGTH_SHORT
                ).show()
            } else Toast.makeText(
                this@MenuQuestionActivity,
                "Xóa không thành công",
                Toast.LENGTH_SHORT
            ).show()
        }

        //set sự kiện click NO
        btnNo.setOnClickListener { dialog.cancel() }
        //thực hiện dialog
        dialog.show()
    }

    private fun deleteQuestion(id_question: Int): Boolean {
        val db = Database(this)
        return db.deleteQuestion(id_question)
    }
}
