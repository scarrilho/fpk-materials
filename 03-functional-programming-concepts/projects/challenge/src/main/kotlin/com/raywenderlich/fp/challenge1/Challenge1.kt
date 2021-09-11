/*
 *  Copyright (c) 2021 Razeware LLC
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
 */

package com.raywenderlich.fp.challenge1

var count = 0

/**
 * The answer, in this case, isn’t so obvious. Every time you invoke inc3, you calculate a
 * result incrementing count, a global variable. You then print the result and decrement
 * the global variable count before returning the result. println makes this function
 * impure.
 *
 * But what if you remove the println? Is the function still impure? The function is still
 * impure because it changes the values of count while executing. If you invoke another
 * function in another thread accessing the same count variable, a race condition might
 * cause some unexpected results.
 */
fun inc3(x: Int): Int {
  val result = x + ++count + 1
  println("Res: $result") // HERE
  --count
  return result
}