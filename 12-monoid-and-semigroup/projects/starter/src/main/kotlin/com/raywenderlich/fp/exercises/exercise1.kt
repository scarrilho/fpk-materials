package com.raywenderlich.fp.exercises

fun main() {
    val str1 = "Hello"
    val str2 = " World!"

    println(str1 + str2)
    println(str2 + str1)

    val unit = ""
    println(str1 + unit)
    println(unit + str1)
}