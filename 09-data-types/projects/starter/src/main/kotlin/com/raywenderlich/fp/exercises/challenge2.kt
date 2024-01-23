package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.pipe

fun <T> List<T>.lenght(): Int =
    fold(0) { acc, _ ->
        acc +  1
    }

fun main() {
    listOf(1, 2, 3, 4, 6, 7, 8, 9, 10)
        .lenght()
        .pipe(::println)

    emptyList<Int>().lenght().pipe(::println)

    val list = List<Int>(37) { it }
    list.lenght() pipe ::println
}