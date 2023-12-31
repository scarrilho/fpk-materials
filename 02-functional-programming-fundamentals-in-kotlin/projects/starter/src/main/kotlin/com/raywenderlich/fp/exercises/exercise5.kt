package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.Fun
import com.raywenderlich.fp.after
import com.raywenderlich.fp.identity

fun addOne(x: Int) = x + 1

fun removeOne(x: Int) = x - 1

fun main() {
    val x = 1

    val f: Fun<Int, Int> = ::addOne
    val g: Fun<Int, Int> = ::removeOne

    val gAfterf = g after f
    val fAfterg = f after g

    val isSame = (gAfterf(x) == fAfterg(x)) && (gAfterf(x) == identity(x)) && (fAfterg(x) == identity(x))

    println(isSame)
    println("${gAfterf(x)}, ${fAfterg(x)}, ${identity(x)}")
}