/*
 * Copyright (c) 2022 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.raywenderlich.fp

import com.raywenderlich.fp.exercises.compose

fun twice(a: Int): Int = a * 2

fun format(b: Int): String = "Result is $b"

fun formatAfterTwice(a: Int) = format(twice(a))

fun lenght(s: String): Int = s.length

fun half(a: Int): Int = a / 2

fun main() {
    println(format(twice(37)))
    println(formatAfterTwice(37))

    val f: Fun<Int, Int> = ::twice
    val g: Fun<Int, String> = ::format

    val formatTwice = g after f
/*
    println(formatTwice)
    println(formatTwice(37))

    val composeFormatTwice = f compose g
    println(composeFormatTwice(37))*/

    val h: Fun<String, Int> = ::lenght
    val leftSide: Fun<Int, Int> = (h after g) after f
    val rightSide: Fun<Int, Int> = h after (g after f)
    println(leftSide(37) == rightSide(37))

    //absurd<Int>(TODO())
    println(unit<Int>(1))

    fun one(u: Unit): Int = 1
    fun two(u: Unit): Int = 2
    fun minusThree(u: Unit): Int = -3
    val twiceTwo = ::twice after ::two
    println(twiceTwo(Unit))
}