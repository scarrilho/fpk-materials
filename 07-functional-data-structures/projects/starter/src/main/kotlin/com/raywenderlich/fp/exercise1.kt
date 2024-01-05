package com.raywenderlich.fp

/*fun <T> FList<T>.isEmpty(): Boolean =
    when (this) {
        is Nil -> false
        is FCons -> true
    }*/

fun <T> FList<T>.isEmpty(): Boolean =
    match(
        whenNil = { true },
        whenCons = { _, _ -> false}
    )
fun main() {
    println(FList.empty<Int>().isEmpty())
    println(FList.of(1).isEmpty())
    println(FList.of(1, 2, 3).isEmpty())
}