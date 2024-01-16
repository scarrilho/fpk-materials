package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.pipe

fun strToInt(value: String): Int? =
    try {
       value.toInt().lift()
    } catch (nfe: NumberFormatException) {
        null
    }

fun double(value: Int): Int = value * 2

/*
fun <T: Any> T?.getOrDefault(defaultValue: T): T =
    if (this == null) defaultValue else this
*/

fun main() {
    "10"
        .lift()
        .flatMap(::strToInt)
        .map(::double)
        .getOrDefault(-1)
        .pipe(::println)

    "10a"
        .lift()
        .flatMap(::strToInt)
        .map(::double)
        .getOrDefault(-1)
        .pipe(::println)
}