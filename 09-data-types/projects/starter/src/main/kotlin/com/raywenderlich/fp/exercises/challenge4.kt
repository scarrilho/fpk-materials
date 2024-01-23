package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.pipe

fun <T> List<T>.lastFold(): T? =
    fold(null as T?) { _, item -> item}

fun <T> List<T>.firstFold(): T? =
    foldRight(null as T?) { item, _ -> item}

fun main() {
    val list = List<Int>(37) { it }
    list.lastFold() pipe ::println
    val empty = emptyList<Int>()
    empty.lastFold() pipe ::println

    list.firstFold() pipe ::println
    empty.firstFold() pipe ::println
}