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

fun reverseAlt(str: String) =
    str.toCharArray().toList()
        .declarativeFoldRight(StringBuilder()) { c, acc ->
            acc.append(c)
            acc
        }.toString()

fun main() {
    val s = "supercalifragilisticexpialidocious"
    reverseString(s) pipe ::println

    s.reverse() pipe ::println

    s pipe ::reverseString pipe ::println

    reverseAlt(s) pipe ::println
}