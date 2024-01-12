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

typealias Writer<A, B> = (A) -> Pair<B, String>

infix fun <A, B, C> Writer<A, B>.compose(
    g: Writer<B, C>
): Writer<A, C> = { a: A ->
    val (b, str) = this(a)
    val (c, str2) = g(b)
    c to "$str $str2"
}

fun pureFunction(x: Int) = x * x - 1

fun functionWithEffect(x: Int): Int {
    val result = x * x - 1
    println("Result: $result")
    return result
}

fun functionWithWriter(x: Int): Pair<Int, String>{
    val result = x * x - 1
    return result to "Result: $result"
}

fun main() {
/*    pureFunction(5) pipe ::println
    pureFunction(5) pipe ::println
    pureFunction(5) pipe ::println

    functionWithEffect(5) pipe ::println
    functionWithEffect(5) pipe ::println
    functionWithEffect(5) pipe ::println
 */

    listOf(1, 2, 3)
        .map(::pureFunction) pipe ::println

    listOf(1, 2, 3).map(::pureFunction).map(::pureFunction) pipe ::println
    listOf(1, 2, 3).map(::pureFunction compose ::pureFunction) pipe ::println

    listOf(1, 2, 3).map(::functionWithEffect).map(::functionWithEffect) pipe ::println
    listOf(1, 2, 3).map(::functionWithEffect compose ::functionWithEffect) pipe ::println

    val square = { a: Int -> a * a }
    val double = { a: Int -> a * 2 }
    val squareFunAndWrite = square compose ::functionWithWriter
    val doubleFunAndWrite = double compose ::functionWithWriter
    // val compFunWithWriter = ::functionWithWriter compose ::functionWithWriter
    val compFunWithWriter = squareFunAndWrite compose doubleFunAndWrite
    compFunWithWriter(5).second pipe ::println

}