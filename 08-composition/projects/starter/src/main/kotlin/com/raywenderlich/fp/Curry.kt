/*
 *  Copyright (c) 2022 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use, copy
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * Not withstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the Software in
 * any work that is designed, intended, or marketed for pedagogical or instructional
 * purposes related to programming, coding, application development, or information
 * technology.  Permission for such use, copying, modification, merger, publication,
 * distribution, sublicensing, creation of derivative works, or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
 * USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.raywenderlich.fp

typealias Fun2<A, B, C> = (A, B) -> C

fun <A, B, C> Fun2<A, B, C>.curry(): (A) -> (B) -> (C) = { a: A ->
    { b: B ->
        this(a, b)
    }
}

infix fun <A, B> A.pipe(f: Fun<A, B>): B = f(this)

fun sum(a: Int): (Int) -> Int = { b: Int -> a + b}
fun main() {
    val double = { a: Int -> a * 2 }
    val square = { a: Int -> a * a }
    val sum = { a: Int, b: Int -> a + b }
    val stringify = Int::toString

    /* val addThree = sum(3)
    val result = addThree(4)
    println(result)*/

    val curriedSum = sum.curry() // (Int) -> (Int) -> (Int)
    val addThree =  curriedSum(3) // (Int) -> (Int)
    val result = addThree(4)
//    println(curriedSum)
//    println(addThree)
    println(result)

    fun comp(a: Int, b: Int): String {
        val currySum: (Int) -> (Int) -> Int = sum.curry()
        println(currySum)
        val doubleComposeSum: (Int) -> (Int) -> Int = double compose currySum
        println(doubleComposeSum)
        val right: (Int) -> Int = doubleComposeSum(a)
        println(right)
        return (square compose right compose stringify) (b)
    }

    fun comp2(a: Int, b: Int):String {
        val right = (double compose sum.curry()) (a)
        return (square compose right compose stringify)(b)
    }

    fun comp3(a: Int, b: Int): String = b pipe
            (square compose (a pipe (double compose sum.curry())) compose stringify)

    println(comp(10, 2))
    println(comp3(10, 2))
}