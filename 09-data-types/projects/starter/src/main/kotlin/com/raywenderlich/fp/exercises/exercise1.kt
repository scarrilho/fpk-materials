package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.pipe

fun <T: Any> T.lift(): T? = this

fun <T: Any> T.empty(): T? = null

fun <T: Any> T?.getOrDefault(defaultValue: T): T =
    this ?: defaultValue

fun main() {
    val optStr = "10".lift()
    optStr pipe ::println
    val empty = String.empty()
    empty pipe ::println
    optStr
        .getOrDefault("Default")
        .pipe (::println)
    empty
        .getOrDefault("Default")
        .pipe (::println)
}