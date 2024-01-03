package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.times

fun <A: Any> myNullableLazy(fn: () -> A?): () -> A? {
    var result: A? = null
    var evaluated = false

    return {
        if (!evaluated) {
            result = fn()
            evaluated = true
        }
        result
    }
}

fun main() {
    val myLazy = myNullableLazy { println("I am very lazy!"); null }
    3.times {
        println(myLazy())
    }
}