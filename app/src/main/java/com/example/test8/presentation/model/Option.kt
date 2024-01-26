package com.example.test8.presentation.model

data class Option(
    val id:Int,
    val icon:Int? =null,
    val name:String,
    val switch:Boolean? = null,
    val type:Type
) {
}

enum class Type{
    NORMAL,
    Switch
}