package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.Predicate1

/*
inline fun <reified T> Array<T>.all(predicate: Predicate1<T>): Array<T> =
    this.filter(predicate::accept).toTypedArray()
*/

inline fun <reified T> Array<T>.all(predicate: Predicate1<T>): Array<T> =
    this.filter { predicate.accept(it) }.toTypedArray()

val isPrime = Predicate1<Int> { value ->
    if (value <= 3) value > 1
    else if (value % 2 == 0 || value % 3 == 0) false
    else {
        var i = 5
        while (i * i <= value) {
            if (value % i == 0 || value % (i + 2) == 0)
                return@Predicate1 false
                i += 6
            }
        true
    }
}

fun main() {
    arrayOf(1, 45, 67, 78, 34, 56, 89, 121, 2, 11, 12, 13)
        .all(isPrime)
        .forEach(::println)
}