package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.declarativeFold

typealias Predicate<T> = (T) -> Boolean

fun <T> List<T>.filterFold(predicate: Predicate<T>) : List<T> =
    fold(mutableListOf()) { acc, item ->
        if (predicate(item)) {
            acc.add(item)
        }
        acc
    }

fun main() {
    listOf(1, 2, 3, 4, 6, 7, 8, 9, 10)
        .filterFold { it % 2 == 0 }
        .forEach(::println)
}