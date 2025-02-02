package com.cstrien.thi_trac_nghiem.model

class Category {
    @JvmField
    var id: Int = 0
    @JvmField
    var name: String? = null

    constructor(name: String?) {
        this.name = name
    }

    constructor()

    override fun toString(): String {
        return name!!
    }
}
