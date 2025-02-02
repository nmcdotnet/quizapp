package com.cstrien.thi_trac_nghiem


import android.util.Log
import com.cstrien.thi_trac_nghiem.model.Category
import com.cstrien.thi_trac_nghiem.model.Question
import com.cstrien.thi_trac_nghiem.model.Score
import com.cstrien.thi_trac_nghiem.model.User

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {

    companion object {
        private const val DATABASE_NAME = "TracNghiem"
        private const val VERSION = 1

        // Bảng users
        private const val TABLE_USERS = "users"
        private const val ID_USER = "id"
        private const val NAME_USER = "name"
        private const val PASSWORD = "password"
        private const val ROLE = "role"

        // Bảng categories
        private const val TABLE_CATEGORIES = "categories"
        private const val ID_CATEGORY = "id"
        private const val NAME_CATEGORY = "name"

        // Bảng questions
        private const val TABLE_QUESTIONS = "questions"
        private const val ID_QUESTION = "id"
        private const val NAME_QUESTION = "question"
        private const val OPTION1 = "option1"
        private const val OPTION2 = "option2"
        private const val OPTION3 = "option3"
        private const val OPTION4 = "option4"
        private const val ANSWER = "answer"
        private const val ID_CATEGORY_FK = "id_category"

        // Bảng scores
        private const val TABLE_SCORES = "scores"
        private const val ID_SCORE = "id"
        private const val ID_USER_FK = "id_user"
        private const val ID_CATEGORY_FK_2 = "id_category"
        private const val SCORE = "score"
        private const val DATE = "create_at"
    }

    // Tạo bảng users
    private val CREATE_USERS_TABLE =
        "CREATE TABLE $TABLE_USERS ($ID_USER INTEGER PRIMARY KEY AUTOINCREMENT, $NAME_USER TEXT UNIQUE, $PASSWORD TEXT, $ROLE INTEGER)"

    // Tạo bảng categories
    private val CREATE_CATEGORIES_TABLE =
        "CREATE TABLE $TABLE_CATEGORIES ($ID_CATEGORY INTEGER PRIMARY KEY AUTOINCREMENT, $NAME_CATEGORY TEXT)"

    // Tạo bảng questions
    private val CREATE_QUESTIONS_TABLE =
        "CREATE TABLE $TABLE_QUESTIONS ($ID_QUESTION INTEGER PRIMARY KEY AUTOINCREMENT, $NAME_QUESTION TEXT, $OPTION1 TEXT, $OPTION2 TEXT, $OPTION3 TEXT, $OPTION4 TEXT, $ANSWER INTEGER, $ID_CATEGORY_FK INTEGER, FOREIGN KEY ($ID_CATEGORY_FK) REFERENCES $TABLE_CATEGORIES($ID_CATEGORY) ON DELETE CASCADE)"

    // Tạo bảng scores
    private val CREATE_SCORES_TABLE =
        "CREATE TABLE $TABLE_SCORES ($ID_SCORE INTEGER PRIMARY KEY AUTOINCREMENT, $ID_USER_FK INTEGER, $ID_CATEGORY_FK_2 INTEGER, $SCORE INTEGER, $DATE TEXT DEFAULT (strftime('%d-%m-%Y, %H:%M','now', 'localtime')), FOREIGN KEY ($ID_USER_FK) REFERENCES $TABLE_USERS($ID_USER) ON DELETE CASCADE, FOREIGN KEY ($ID_CATEGORY_FK_2) REFERENCES $TABLE_CATEGORIES($ID_CATEGORY_FK_2) ON DELETE CASCADE)"

    // Dữ liệu users
    private val insertUser =
        "INSERT INTO users VALUES (null,'admin', '123', 1), (null,'trien', '123', 0)"

    // Chèn dữ liệu categories
    private val insertCate =
        "INSERT INTO categories VALUES (null,'Toán'), (null,'Văn'), (null,'Anh')"

    // Chèn dữ liệu questions
    private val insertQues =
        "INSERT INTO questions VALUES (null, 'Một cân sắt và một cân bông cái nào nặng hơn?', 'A. Sắt', 'B. Bông', 'C. Bằng nhau', 'D. A hoặc B', 3, 1), " +
                "(null, '10^6 - 10^5 + 800 = ?', 'A. 800 - 10^5 + 10^6', 'B. 900800', 'C. 10^6', 'D. ?', 4, 1), " +
                "(null, 'Diện tích hình tam giác có chiều cao h và đáy d bằng ?', 'A. d*h/2', 'B. d*h', 'C. (d+h)/2', 'D. ?', 1, 1), " +
                "(null, '1 + 1 = ?', 'A. 1', 'B. 2', 'C. 3', 'D. 4', 2, 1), " +
                "(null, 'Cho ba vecto a, b, c. Điều kiện nào sau đây không kết luận được ba vecto đó đồng phẳng.', 'A. Một trong ba vecto đó bằng 0.', 'B. Có hai trong ba vecto đó cùng phương.', 'C. Có một vecto không cùng hướng với hai vecto còn lại', 'D. Có hai trong ba vecto đó cùng hướng.', 2, 1), " +
                "(null, 'Trong các khẳng định sau; khẳng định nào đúng?', 'A. Qua hai điểm phân biệt có duy nhất một mặt phẳng.', 'B. Qua ba điểm phân biệt bất kì có duy nhất một mặt phẳng.', 'C. Qua ba điểm không thẳng hàng có duy nhất một mặt phẳng.', 'D. Qua bốn điểm bất kì có duy nhất một mặt phẳng.', 3, 1), " +
                "(null, 'Trong không gian; cho 5 điểm không đồng phẳng. Có thể xác định được tối đa bao nhiêu mặt phẳng phân biệt từ các điểm đã cho?', 'A. 7', 'B. 8', 'C. 10', 'D. 4', 3, 1), " +
                "(null, 'Trong các hình chóp, hình chóp có ít cạnh nhất có số cạnh là bao nhiêu?', 'A. 3', 'B. 4', 'C. 5', 'D. 6', 4, 1), " +
                "(null, 'Huy năm nay 10 tuổi, chỉ Huy gấp đôi tuổi Huy. Hỏi 10 năm sau chị Huy bao nhiêu tuổi?', 'A. 40', 'B. 30', 'C. 50', 'D. 45', 2, 1), " +
                "(null, 'Một cầu vồng có 7 màu, vậy 3 cầu vồng có?', 'A. 7', 'B. 14', 'C. 21', 'D. 28', 1, 1), " +
                "(null, 'Một con ngựa đau cả tàu _____?', 'A. bỏ cỏ', 'B. bỏ đi', 'C. mặc kệ', 'D. bỏ nhỏ', 1, 2), " +
                "(null, 'Thẳng như _____?', 'A. thước kẻ', 'B. bóng đèn', 'C. ruột ngựa', 'D. ruột lợn', 3, 2), " +
                "(null, 'Nguyễn Duy Mạnh là?', 'A. Nhà thơ', 'B. Thợ hát', 'C. Thợ xây', 'D. Thợ mộc', 2, 2), " +
                "(null, 'Ai là tác giả bài thơ Đồng chí?', 'A. Tố Hữu', 'B. Nguyễn Duy Mạnh', 'C. Huy Cận', 'D. Chế Lan Viên', 1, 2), " +
                "(null, 'Đại Việt sử ký toàn thư do ai sáng tác?', 'A. Trần Hưng Đạo', 'B. Ngô Sĩ Liên', 'C. Trần Thủ Độ', 'D. Trần Khánh Dư', 2, 2), " +
                "(null, 'Bài thơ Lượm được sáng tác năm bao nhiêu?', 'A. 1949', 'B. 1950', 'C. 1961', 'D. 1962', 1, 2), " +
                "(null, 'Tác phẩm Số đỏ do ai viết?', 'A. Tố Hữu', 'B. Vũ Trọng Phụng', 'C. Xuân Diệu', 'D. Nam Cao', 2, 2), " +
                "(null, 'Cậu Vàng xuất hiện trong tác phẩm nào?', 'A. Đời thừa', 'B. Lão Hạc', 'C. Quê tôi', 'D. Con chó', 2, 2), " +
                "(null, 'Bà huyện Thanh Quan là tác giả của?', 'A. Quanh co', 'B. Dốc đứng', 'C. Đèo dọc', 'D. Đèo ngang', 4, 2), " +
                "(null, 'Ai là tác giả bài thơ Đoàn thuyền đánh cá?', 'A. Tố Hữu', 'B. Huy Cận', 'C. Xuân Diệu', 'D. Chế Lan Viên', 2, 2), " +
                "(null, 'Who are all ________ people?', 'A. this', 'B. those', 'C. them', 'D. that', 2, 3), " +
                "(null, 'Claude is ________.', 'A. Frenchman', 'B. a French', 'C. a Frenchman', 'D. French man', 2, 3), " +
                "(null, 'They are all ________ ready for the party.', 'A. getting', 'B. going', 'C. doing', 'D. putting', 1, 3), " +
                "(null, 'Jane _____ as a fashion designer for ten years before becoming a famous singer.', 'A. worked', 'B. is working', 'C. works', 'D. will work', 1, 3), " +
                "(null, 'Dan can _____ the drum very well.', 'A. play', 'B. do', 'C. make', 'D. think', 1, 3), " +
                "(null, 'My friend is ______ so she has a lot of free time.', 'A. singer', 'B. married', 'C. single', 'D. free', 3, 3), " +
                "(null, 'I know somebody ________ can play the guitar.', 'A. he', 'B. who', 'C. what', 'D. that he', 2, 3), " +
                "(null, 'Did you ask your father ________ some money?', 'A. 0', 'B. after', 'C. on', 'D. for', 4, 3), " +
                "(null, 'Those students are working very _____ for their next exams.', 'A. hardly', 'B. hard', 'C. harder', 'D. hardest', 2, 3), " +
                "(null, 'When do you go ________ bed?', 'A. to', 'B. to the', 'C. in', 'D. on', 1, 3)"

    // Chèn dữ liệu scores
    private val insertScore =
        "INSERT INTO scores(id_user, id_category, score) VALUES (2, 1, 230), (1, 2, 240), (1, 2, 250),(2, 1, 260)"

    override fun onOpen(db: SQLiteDatabase) {
        super.onOpen(db)
        db.disableWriteAheadLogging()
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Tạo bảng
        db.execSQL(CREATE_USERS_TABLE)
        db.execSQL(CREATE_CATEGORIES_TABLE)
        db.execSQL(CREATE_QUESTIONS_TABLE)
        db.execSQL(CREATE_SCORES_TABLE)
        // Chèn dữ liệu
        db.execSQL(insertUser)
        db.execSQL(insertCate)
        db.execSQL(insertQues)
        db.execSQL(insertScore)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CATEGORIES")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_QUESTIONS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_SCORES")
        onCreate(db)
    }

    // CATEGORIES
    fun addCategory(category: Category): Boolean {
        return try {
            val db = writableDatabase
            val values = ContentValues().apply {
                put(NAME_CATEGORY, category.name)
            }
            db.insert(TABLE_CATEGORIES, null, values)
            db.close()
            true
        } catch (e: Exception) {
            false
        }
    }

    fun updateCategory(category: Category): Boolean {
        return try {
            val db = writableDatabase
            val values = ContentValues().apply {
                put(NAME_CATEGORY, category.name)
            }
            val rs = db.update(
                TABLE_CATEGORIES,
                values,
                "$ID_CATEGORY = ?",
                arrayOf(category.id.toString())
            )
            db.close()
            rs > 0
        } catch (e: Exception) {
            false
        }
    }

    fun deleteCategory(id_category: Int): Boolean {
        val db = readableDatabase
        val rs = db.delete(TABLE_CATEGORIES, "$ID_CATEGORY=?", arrayOf(id_category.toString()))
        return rs > 0
    }

    fun getCategoryById(id: Int): Category {
        val category = Category()
        val db = readableDatabase
        val c = db.rawQuery(
            "SELECT * FROM $TABLE_CATEGORIES WHERE $ID_CATEGORY = ?",
            arrayOf(id.toString())
        )
        if (c.moveToFirst()) {
            category.id = c.getInt(0)
            category.name = c.getString(1)
        }
        c.close()
        return category
    }

    fun getListCategories(key: String?): ArrayList<Category> {
        val listCategories = ArrayList<Category>()
        val db = readableDatabase
        val c: Cursor = if (key == null) {
            db.rawQuery("SELECT * FROM $TABLE_CATEGORIES", null)
        } else {
            db.rawQuery(
                "SELECT * FROM $TABLE_CATEGORIES WHERE $ID_CATEGORY LIKE ? OR $NAME_CATEGORY LIKE ?",
                arrayOf("%$key%", "%$key%")
            )
        }
        if (c.moveToFirst()) {
            do {
                val category = Category()
                category.id = c.getInt(0)
                category.name = c.getString(1)
                listCategories.add(category)
            } while (c.moveToNext())
        }
        c.close()
        return listCategories
    }

    // QUESTIONS
    fun updateQuestion(q: Question): Boolean {
        return try {
            val db = writableDatabase
            val values = ContentValues().apply {
                put(NAME_QUESTION, q.question)
                put(OPTION1, q.option1)
                put(OPTION2, q.option2)
                put(OPTION3, q.option3)
                put(OPTION4, q.option4)
                put(ANSWER, q.answer)
                put(ID_CATEGORY_FK, q.id_category)
            }
            val rs =
                db.update(TABLE_QUESTIONS, values, "$ID_QUESTION = ?", arrayOf(q.id.toString()))
            db.close()
            rs > 0
        } catch (e: Exception) {
            false
        }
    }

    fun addQuestion(q: Question): Boolean {
        return try {
            val db = writableDatabase
            val values = ContentValues().apply {
                put(NAME_QUESTION, q.question)
                put(OPTION1, q.option1)
                put(OPTION2, q.option2)
                put(OPTION3, q.option3)
                put(OPTION4, q.option4)
                put(ANSWER, q.answer)
                put(ID_CATEGORY_FK, q.id_category)
            }
            db.insert(TABLE_QUESTIONS, null, values)
            db.close()
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteQuestion(id_question: Int): Boolean {
        val db = readableDatabase
        val rs = db.delete(TABLE_QUESTIONS, "$ID_QUESTION=?", arrayOf(id_question.toString()))
        return rs > 0
    }

    fun getQuestionById(id: Int): Question {
        val question = Question()
        val db = readableDatabase
        val c = db.rawQuery(
            "SELECT * FROM $TABLE_QUESTIONS WHERE $ID_QUESTION = ?",
            arrayOf(id.toString())
        )
        if (c.moveToFirst()) {
            question.id = c.getInt(0)
            question.question = c.getString(1)
            question.option1 = c.getString(2)
            question.option2 = c.getString(3)
            question.option3 = c.getString(4)
            question.option4 = c.getString(5)
            question.answer = c.getInt(6)
            question.id_category = c.getInt(7)
        }
        c.close()
        return question
    }

    fun getListQuestions(name: String?, categoryID: Int): ArrayList<Question> {
        val listQuestions = ArrayList<Question>()
        val db = readableDatabase
        val c: Cursor = if (name == null) {
            db.rawQuery(
                "SELECT * FROM $TABLE_QUESTIONS WHERE $ID_CATEGORY_FK = ?",
                arrayOf(categoryID.toString())
            )
        } else {
            db.rawQuery(
                "SELECT * FROM $TABLE_QUESTIONS WHERE $ID_CATEGORY_FK = ? AND $NAME_QUESTION LIKE ?",
                arrayOf(categoryID.toString(), "%$name%")
            )
        }
        if (c.moveToFirst()) {
            do {
                val question = Question()
                question.id = c.getInt(0)
                question.question = c.getString(1)
                question.option1 = c.getString(2)
                question.option2 = c.getString(3)
                question.option3 = c.getString(4)
                question.option4 = c.getString(5)
                question.answer = c.getInt(6)
                question.id_category = c.getInt(7)
                listQuestions.add(question)
            } while (c.moveToNext())
        }
        c.close()
        return listQuestions
    }

    // USERS
    fun getListUsers(key: String?): ArrayList<User> {
        val listUsers = ArrayList<User>()
        val db = readableDatabase
        val query = if (key == null) {
            "SELECT * FROM $TABLE_USERS"
        } else {
            "SELECT * FROM $TABLE_USERS WHERE $ID_USER LIKE '%$key%' OR $NAME_USER LIKE '%$key%'"
        }
        val c: Cursor = db.rawQuery(query, null)
        if (c.moveToFirst()) {
            do {
                val user = User()
                user.id = c.getInt(0)
                user.name = c.getString(1)
                user.password = c.getString(2)
                user.role = c.getInt(3)
                listUsers.add(user)
            } while (c.moveToNext())
        }
        c.close()
        return listUsers
    }

    fun getUserByName(name: String): User {
        val user = User()
        val db = readableDatabase
        val c = db.rawQuery("SELECT * FROM $TABLE_USERS WHERE $NAME_USER = ?", arrayOf(name))
        if (c.moveToFirst()) {
            user.id = c.getInt(0)
            user.name = c.getString(1)
            user.password = c.getString(2)
            user.role = c.getInt(3)
        }
        c.close()
        return user
    }

    fun getUserById(id: Int): User {
        val user = User()
        val db = readableDatabase
        val c = db.rawQuery("SELECT * FROM $TABLE_USERS WHERE $ID_USER = ?", arrayOf(id.toString()))
        if (c.moveToFirst()) {
            user.id = c.getInt(0)
            user.name = c.getString(1)
            user.password = c.getString(2)
            user.role = c.getInt(3)
        }
        c.close()
        return user
    }

    fun getUserByNameAndPass(name: String, pass: String): User {
        val user = User()
        val db = readableDatabase
        val c = db.rawQuery(
            "SELECT * FROM $TABLE_USERS WHERE $NAME_USER = ? AND $PASSWORD = ?",
            arrayOf(name, pass)
        )
        if (c.moveToFirst()) {
            user.id = c.getInt(0)
            user.name = c.getString(1)
            user.password = c.getString(2)
            user.role = c.getInt(3)
        }
        c.close()
        return user
    }

    fun signUp(user: User): Boolean {
        return try {
            val db = writableDatabase
            val values = ContentValues().apply {
                put(NAME_USER, user.name)
                put(PASSWORD, user.password)
                put(ROLE, user.role)
            }
            db.insert(TABLE_USERS, null, values)
            db.close()
            true
        } catch (e: Exception) {
            false
        }
    }

    fun updateUser(user: User): Boolean {
        return try {
            val db = writableDatabase
            val values = ContentValues().apply {
                put(PASSWORD, user.password)
                put(ROLE, user.role)
            }
            val rs = db.update(TABLE_USERS, values, "$NAME_USER = ?", arrayOf(user.name))
            db.close()
            rs > 0
        } catch (e: Exception) {
            false
        }
    }

    fun changePass(userName: String, pass: String): Boolean {
        return try {
            val db = writableDatabase
            val values = ContentValues().apply {
                put(PASSWORD, pass)
            }
            val rs = db.update(TABLE_USERS, values, "$NAME_USER = ?", arrayOf(userName))
            db.close()
            rs > 0
        } catch (e: Exception) {
            false
        }
    }

    fun deleteUser(idUser: Int): Boolean {
        val db = readableDatabase
        val rs = db.delete(TABLE_USERS, "$ID_USER = ?", arrayOf(idUser.toString()))
        return rs > 0
    }

    // SCORE
    fun getListScore(idCategory: Int, userName: String?): ArrayList<Score> {
        val listScore = ArrayList<Score>()
        val db = readableDatabase
        val query = if (userName != null) {
            "SELECT * FROM $TABLE_SCORES WHERE $ID_CATEGORY_FK_2 = $idCategory AND $ID_USER_FK = ${
                getUserByName(
                    userName
                ).id
            } ORDER BY $ID_SCORE DESC LIMIT 10"
        } else {
            "SELECT * FROM $TABLE_SCORES WHERE $ID_CATEGORY_FK_2 = $idCategory ORDER BY $SCORE DESC LIMIT 10"
        }
        val c = db.rawQuery(query, null)
        if (c.moveToFirst()) {
            do {
                val score = Score()
                score.id = c.getInt(0)
                score.id_user = c.getInt(1)
                score.id_category = c.getInt(2)
                score.score = c.getInt(3)
                score.date = c.getString(4)
                listScore.add(score)
            } while (c.moveToNext())
        }
        c.close()
        return listScore
    }

    fun createScore(userName: String, idCategory: Int, score: Int) {
        try {
            val db = writableDatabase
            val values = ContentValues().apply {
                put(ID_USER_FK, getUserByName(userName).id)
                put(ID_CATEGORY_FK_2, idCategory)
                put(SCORE, score)
            }
            db.insert(TABLE_SCORES, null, values)
            db.close()
        } catch (e: Exception) {
            Log.d("Error", e.toString())
        }
    }

    fun getHighScoreByIdCategory(idCategory: Int): Int {
        val db = readableDatabase
        var highScore = 0
        val c = db.rawQuery(
            "SELECT * FROM $TABLE_SCORES WHERE $ID_CATEGORY_FK_2 = $idCategory ORDER BY $SCORE DESC LIMIT 1",
            null
        )
        if (c.moveToFirst()) {
            highScore = c.getInt(3)
        }
        c.close()
        return highScore
    }
}