package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.Fun
import com.raywenderlich.fp.chronoMs
import com.raywenderlich.fp.pipe
import java.lang.Thread.sleep
import java.util.concurrent.Callable

typealias WithCallable<A, B> = Fun<A, Callable<B>>

infix fun <A,B,C> WithCallable<A, B>.compose(
    g: WithCallable<B, C>): WithCallable<A, C> = { a: A ->
    Callable<C> {
        g(this(a).call()).call()
    }
}

fun main() {
    val waitAndReturn = { a: Int ->
       Callable {
           sleep(1000)
           a
       }
    }
    val comp = waitAndReturn compose  waitAndReturn
    chronoMs {
        comp(2).call()
    } pipe ::println
}