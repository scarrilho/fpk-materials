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

import compose

fun List<Int>.imperativeSum(): Int {
    var sum = 0
    for (i in 0 until size) {
        sum += this[i]
    }
    return sum
}

fun List<Int>.declarativeSum(): Int {
    tailrec fun helper(pos: Int, acc: Int): Int {
        if (pos == size) {
            return acc
        }
        return helper(pos + 1, this[pos] + acc)
    }

    return helper(0, 0)
}

fun List<Int>.declarativeProduct(): Int {
    tailrec fun helper(pos: Int, acc: Int): Int {
        if (pos == size) {
            return acc
        }
        return helper(pos + 1, this[pos] * acc)
    }

    return helper(0, 1)
}

fun <T, S> List<T>.declarativeFold(
    start: S,
    combineFunc: (S, T) -> S
): S {
    tailrec fun helper(pos: Int, acc: S): S {
        if (pos == size) {
            return acc
        }
        return helper(pos + 1, combineFunc(acc, this[pos]))
    }

    return helper(0, start)
}

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    list.imperativeSum() pipe ::println
    List<Int>::imperativeSum compose ::println epip list
    list.declarativeSum() pipe ::println
    list.declarativeProduct() pipe ::println
    list.declarativeFold(0) { acc, item ->
        acc + item
    } pipe ::println

    list.declarativeFold(1) { acc, item ->
        acc * item
    } pipe ::println

    list.fold(0) { acc, item -> acc + item } pipe ::println
    list.fold(1) { acc, item -> acc * item } pipe ::println
}