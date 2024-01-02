package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.operation
import com.raywenderlich.fp.times

fun fibo2(): () -> Int {
    var count = 0
    var past = 0
    var previous = 1

    return {
        if (count == 0 || count == 1) {
            val result = count
            count += 1
            result
        } else {
            val result = past + previous
            past = previous
            previous = result
            result
        }
    }
}

fun fibo(): () -> Int {
    var first = 0
    var second = 1
    var count = 0

    return {
        val next = when (count) {
            0 -> 0
            1 -> 1
            else -> {
                val ret = first + second
                first = second
                second = ret
                ret
            }
        }
        count++
        next
    }
}

fun main() {
    val fib = fibo()

    12.times {
        println(fib())
    }

}