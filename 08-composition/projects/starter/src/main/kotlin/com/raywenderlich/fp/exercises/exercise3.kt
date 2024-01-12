package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.Fun
import com.raywenderlich.fp.curry
import com.raywenderlich.fp.pipe

typealias Fun3<I1, I2, I3, O> = (I1, I2, I3) -> O

typealias Fun4<I1, I2, I3, I4, O> = (I1, I2, I3, I4) -> O

typealias Fun5<I1, I2, I3, I4, I5, O> = (I1, I2, I3, I4, I5) -> O

typealias Chain3<I1, I2, I3, O> = (I1) -> (I2) -> (I3) -> O
typealias Chain4<I1, I2, I3, I4, O> = (I1) -> (I2) -> (I3) -> (I4) -> O
typealias Chain5<I1, I2, I3, I4, I5, O> = (I1) -> (I2) -> (I3) -> (I4) -> (I5) -> O

fun <I1, I2, I3, O> Fun3<I1, I2, I3, O>.curry(): Chain3<I1, I2, I3, O> =
    { i1: I1, i2: I2 ->
        { i3: I3 ->
            this(i1, i2, i3)
        }
}.curry()

fun <I1, I2, I3, I4, O> Fun4<I1, I2, I3, I4, O>.curry(): Chain4<I1, I2, I3, I4, O> =
    { i1: I1, i2: I2, i3: I3 ->
        { i4: I4 ->
            this(i1, i2, i3, i4)
        }
    }.curry()

fun <I1, I2, I3, I4, I5, O> Fun5<I1, I2, I3, I4, I5, O>.curry(): Chain5<I1, I2, I3, I4, I5, O> =
    { i1: I1, i2: I2, i3: I3, i4: I4 ->
        { i5: I5 ->
            this(i1, i2, i3, i4, i5)
        }
    }.curry()

infix fun <A, B> Fun<A, B>.epip(a: A): B = this(a)

fun main() {
    val sum = { a: Int, b: Int, c: Int, d: Int, e: Int -> a + b + c + d + e }
    val curriedSum = sum.curry()
    val result = 5 pipe (4 pipe (3 pipe (2 pipe (1 pipe curriedSum))))
    val result2 = curriedSum epip 1 epip 2 epip 3 epip 4 epip 5
    println(result)

    println(result2)
    println(curriedSum(1)(2)(3)(4)(5))
}