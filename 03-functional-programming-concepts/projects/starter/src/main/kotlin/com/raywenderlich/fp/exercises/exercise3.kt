package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.util.assertOrThrow

fun main() {
    val expr3 = { 42 }
    val (a1, a2) = expr3() to expr3()
    val expr3Eval = expr3()

    val (a1Eval, a2Eval) = expr3Eval to expr3Eval

    assertOrThrow("not RT") {
        a1 == a1Eval && a2 == a2Eval
    }

}