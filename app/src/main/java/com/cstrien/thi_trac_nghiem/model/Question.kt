package com.cstrien.thi_trac_nghiem.model

class Question {
    @JvmField
    var id: Int = 0
    @JvmField
    var question: String? = null
    @JvmField
    var option1: String? = null
    @JvmField
    var option2: String? = null
    @JvmField
    var option3: String? = null
    @JvmField
    var option4: String? = null
    @JvmField
    var answer: Int = 0
    @JvmField
    var id_category: Int = 0

    constructor(
        question: String?,
        option1: String?,
        option2: String?,
        option3: String?,
        option4: String?,
        answer: Int,
        id_category: Int
    ) {
        this.question = question
        this.option1 = option1
        this.option2 = option2
        this.option3 = option3
        this.option4 = option4
        this.answer = answer
        this.id_category = id_category
    }

    constructor()
}
