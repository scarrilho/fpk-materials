package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.times

fun euler(): () -> Double {
    var counter = 1
    var acc: Double = 2.0
    var div: Int = 1
    return {
       val result = when (counter) {
           1 -> { acc }
           else -> {
               val ret = div
               div = counter * ret
               acc += (1.0 / div)
               acc
           }
       }
       counter++
       result
    }
}

fun e(): () -> Double {
    var currentSum = 1.0
    var n = 1

    tailrec fun factorial(n: Int, tmp: Int): Int =
        if (n == 1) tmp else factorial(n - 1, n * tmp)

    return {
        currentSum += 1.0 / factorial(n++, 1).toDouble()
        currentSum
    }
}

fun factSeq(): () -> Int {
    var partial = 1
    var n = 1
    return {
        partial *= n++
        partial
    }
}

fun fastE(): () -> Double {
    var currentSum = 1.0
    val fact = factSeq()
    return {
        currentSum += 1.0 / fact().toDouble()
        currentSum
    }
}

fun main() {
    val eulerSeq = euler()
    val e = e()
    val fastE = fastE()
    10.times {
        println("${eulerSeq()}")
        //println(e())
        //println(fastE())
    }
}