package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.FList
import com.raywenderlich.fp.match

fun <T> FList<T>.forEachIndexed(fn: (Int, T) -> Unit) {
    fun go(index: Int, flist: FList<T>): Unit = flist.match(
        whenNil = { },
        whenCons = { head, tail ->
            fn(index, head)
            go(index + 1, tail)
        }
    )

    go(0, this)
}

/*
fun <T> FList<T>.forEachIndexed(fn: (Int, T) -> Unit) {
    fun FList<T>.loop(i: Int = 0): Unit = match(
        whenNil = {},
        whenCons = { head, tail ->
            fn(i, head)
            tail.loop(i + 1)
        }
    )
    loop()
}
*/

fun main() {
    listOf("a", "b", "c").forEachIndexed { index, item ->
        println("$index $item")
    }

    FList.of("d", "e", "f").forEachIndexed { index, item ->
        println("$index $item")
    }
}