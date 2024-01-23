package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.pipe

fun List<Double>.average(): Double =
    fold(0.0) { acc, item ->
       acc + item
   } / fold(0.0) { acc, _ -> acc + 1 }

fun main() {
    val list = List<Int>(37) { it }
    list.average() pipe ::println
}