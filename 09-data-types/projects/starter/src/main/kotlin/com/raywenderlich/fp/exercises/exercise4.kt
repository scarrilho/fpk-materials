package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.declarativeFoldRight
import com.raywenderlich.fp.pipe

fun reverseString(s: String): String =
    s.toList().declarativeFoldRight("") { item, acc ->
        acc + item
    }

fun String.reverse(): String =
    this.toList().declarativeFoldRight("") { item, acc ->
        acc + item
    }

fun main() {
    val s = "Hello world!"
    reverseString(s) pipe ::println

    "Hello world!".reverse() pipe ::println

    "Hello world!" pipe ::reverseString pipe ::println
}