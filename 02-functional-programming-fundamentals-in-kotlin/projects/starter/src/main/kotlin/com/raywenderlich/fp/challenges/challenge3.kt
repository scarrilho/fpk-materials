package com.raywenderlich.fp.challenges

fun oneOver(x: Int): Double = 1.0 / x

@JvmInline
value class NonZeroInt(val value: Int) {
    init {
        require(value != 0) { "0 in not a value for this type!"}
    }
}

fun oneOver(x: NonZeroInt): Double = 1.0 / x.value

fun main() {
    println("1/3 = ${oneOver(NonZeroInt(3))}")
    println("1/0 = ${oneOver(NonZeroInt(0))}")
}