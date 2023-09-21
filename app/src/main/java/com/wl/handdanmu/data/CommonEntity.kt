package com.wl.handdanmu.data

open class BaseEntity(open val title: String)

data class FontEntity(override val title: String) : BaseEntity(title)

