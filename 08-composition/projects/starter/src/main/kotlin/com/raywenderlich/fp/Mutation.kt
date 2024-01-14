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

typealias Updater<T> = (T) -> T
typealias WithMutation<A, B, S> = (A) -> Pair<B, Updater<S>>

inline infix fun <A, B, C, S> WithMutation<A, B, S>.compose(
    crossinline g: WithMutation<B, C, S>
): WithMutation<A, C, S> = { a: A ->
    val (b, op) = this(a)
    val (c, op2) = g(b)
    c to (op compose op2)

}

fun squareWithEffect(x: Int): Pair<Int, Updater<MutableCounter>> {
    val result = x * x
    return result to { counter -> counter.count *= 10; counter}
}

fun doubleWithEffect(x: Int): Pair<Int, Updater<MutableCounter>> {
    val result = x * 2
    return result to { counter -> counter.count /= 2; counter}
}
data class MutableCounter(
    var count: Int = 1
)

val counter = MutableCounter()

/*
fun squareWithMutationEffect(x: Int): Int {
    val result = x * x
    counter.count *= 10
    return result
}

fun doubleWithMutationEffect(x: Int): Int {
    val result = x * 2
    counter.count /= 2
    return result
}*/

fun main() {
    val composed = ::squareWithEffect compose ::doubleWithEffect compose ::squareWithEffect
    val  counter = MutableCounter()
    val (result, comUpdate) = composed(3)
    result pipe ::println
    counter pipe comUpdate pipe ::println
}