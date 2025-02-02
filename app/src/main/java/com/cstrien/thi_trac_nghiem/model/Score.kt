package com.cstrien.thi_trac_nghiem.model


class Score {
    @JvmField
    var id: Int = 0
    @JvmField
    var id_user: Int = 0
    @JvmField
    var id_category: Int = 0
    @JvmField
    var score: Int = 0
    var date: String? = null

    constructor()

    constructor(id: Int, id_user: Int, id_category: Int, score: Int) {
        this.id = id
        this.id_user = id_user
        this.id_category = id_category
        this.score = score
    }
}
