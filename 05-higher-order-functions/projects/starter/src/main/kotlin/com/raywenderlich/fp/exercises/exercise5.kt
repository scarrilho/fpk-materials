package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.*

val isValidEmail = Predicate1<String> {
    EMAIL_REG_EX.matches(it)
}

fun isLongerThan(length: Int): Predicate1<String> =
    Predicate1 {
        value -> value.length > length
    }

fun main() {
    val predicate = isValidEmail and isLongerThan(10)

    emails
        .filterWithPredicate(predicate)
        .take(5)
        .forEach(::println)
}