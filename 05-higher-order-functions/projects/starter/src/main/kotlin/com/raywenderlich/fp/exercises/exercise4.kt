package com.raywenderlich.fp.exercises

fun <T> T.isEqualsPredicate(): (T) -> Boolean =
    { value: T -> this == value}

fun interface SinglePredicate<T> {
    fun accept(other: T): Boolean
}

fun <T> T.isEqualsIPredicate(): SinglePredicate<T> =
    SinglePredicate { value: T -> this == value }

fun main() {
    listOf(1, 2, 3, 4, 4, 5, 6, 7, 8, 8)
        .filter(4.isEqualsPredicate())
        .forEach(::println)

    listOf("Helllo", "Helo", "Hello")
        .filter("Hello".isEqualsPredicate())
        .forEach(::println)


    listOf(1, 2, 3, 4, 4, 5, 6, 7, 8, 8)
        .filter { it.isEqualsIPredicate().accept(4) }
        .forEach(::println)

    val fourPredicate: SinglePredicate<Int> = 4.isEqualsIPredicate()

    listOf(1, 2, 3, 4, 4, 5, 6, 7, 8, 8)
        .filter { fourPredicate.accept(it) }
        .forEach(::println)

    listOf(1, 2, 3, 4, 4, 5, 6, 7, 8, 8)
        .filter(fourPredicate::accept)
        .forEach(::println)
}