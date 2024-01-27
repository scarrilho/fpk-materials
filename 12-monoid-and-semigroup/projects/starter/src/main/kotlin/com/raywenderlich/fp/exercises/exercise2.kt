package com.raywenderlich.fp.exercises

fun main() {
    val a = 3
    val b = 7
    val c = 13
    val res1 = a * (b * c)
    val res2 = (a * b) * c
    println(res1)
    println(res2)

    val unit = 1
    println(a * unit)
    println(unit * a)
}