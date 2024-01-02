package com.raywenderlich.fp.exercises

fun shortCircuit(left: () -> Boolean, right: () -> Boolean): Boolean {
    return if (left()) {
        right()
    } else {
        false
    }
}

fun main() {
    val input = 13
    shortCircuit(
        { println("left"); input > 10 },
        { println("right"); input > 15 }
    )
}