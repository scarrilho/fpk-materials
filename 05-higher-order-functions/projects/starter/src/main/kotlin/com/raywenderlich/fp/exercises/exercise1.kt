package com.raywenderlich.fp.exercises

public inline fun <T> Array<T>.first(predicate: (T) -> Boolean): T {
    for (element in this) {
        if (predicate(element)) {
            return element
        }
    }
    throw NoSuchElementException("No elements matching the predicate")
}
public inline fun <T> Array<T>.firstOrNull(predicate: (T) -> Boolean): T? {
    for (element in this) {
        if (predicate(element)) {
            return element
        }
    }
    return null
}
fun main() {
    val intArray = arrayOf(1, 3, 4, 5, 6, 7, 8, 9)
    val emptyArray = arrayOf<Int>()

    println(intArray.first {
        it > 1
    })
    // println(intArray.first { it > 10 })
    // println(emptyArray.first { it > 1 })
    println(emptyArray.firstOrNull { it > 1 })
    println(intArray.firstOrNull { it > 100 })
}