package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.*

/*
fun <T> FList<T>.addHead(newItem: T): FList<T> = match(
    whenNil = { FList.of(newItem) },
    whenCons = { head, tail ->
        FCons(newItem, FCons(head, tail))
    }
)
*/

fun <T> FList<T>.addHead(newItem: T): FList<T> =
    FCons(newItem, this)

fun main() {
    val initialList = FList.of(1, 2)
    val addedList = initialList.addHead(3)
    initialList.forEach {
        println("$it")
    }
    println()
    addedList.forEach {
        println("$it")
    }
}