package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.times
fun <A: Any> myLazy(fn: () -> A): () -> A {
    var result: A? = null

    return {
        if (result == null) {
            result = fn()
        }
        result!!
    }
}

fun main() {
    val myLazy = myLazy { println("I am very lazy!"); 10 }
    3.times {
        println(myLazy())
    }
}