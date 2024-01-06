package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.FList
import com.raywenderlich.fp.head
import com.raywenderlich.fp.size

/*fun <T> FList<T>.first(): T? = match(
    whenNil = { null },
    whenCons = { head, _ ->
        head
    }
)*/

fun <T> FList<T>.first(): T? = head()

fun <T> FList<T>.last(): T? =
    skip(size() - 1).head()

fun main() {
    println(FList.empty<Int>().first())
    println(FList.empty<Int>().last())
    println(FList.of(1).first())
    println(FList.of(1).last())
    println(FList.of(1, 2).first())
    println(FList.of(1, 2).last())
}