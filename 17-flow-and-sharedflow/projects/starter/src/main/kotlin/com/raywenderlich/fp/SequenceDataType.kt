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

import com.raywenderlich.fp.lib.curry
import com.raywenderlich.fp.lib.double
import com.raywenderlich.fp.lib.filterOdd
import com.raywenderlich.fp.lib.logged

fun <A, B> Sequence<A>.ap(
    fn: Sequence<(A) -> B>
): Sequence<B> = sequence {
    val iterator = iterator()
    while (iterator.hasNext()) {
        val fnIterator = fn.iterator()
        val item = iterator.next()
        while (fnIterator.hasNext()) {
            yield(fnIterator.next().invoke(item))
        }
    }
}

infix fun <A, B> Sequence<(A) -> B>.appl(a: Sequence<A>) =
    a.ap(this)

fun main() {
    /*sequenceOf(1, 2, 3, 4, 5)
        .filter(filterOdd.logged("filterOdd"))
        .map(double.logged("double"))
        .count()*/

    /*    data class User(
        val id: Int,
        val name: String,
        val email: String
    )

    val userBuilder = ::User.curry()
    val userBuilderSeq = sequenceOf(userBuilder)
    val idSec = sequenceOf(10, 20, 30)
    val nameSeq = sequenceOf("Minnie", "Donald", "Mickey")
    val emailSeq = sequenceOf("aaaaaaaaa@aaaaaa.com", "bbbbbbbbbb@bbbbbb.com")
    val userSeq =
        userBuilderSeq appl idSec appl nameSeq appl emailSeq

    userSeq.forEach(::println)
*/
    val seqTo = { n: Int -> (1..n).toList().asSequence()}
    val seqOfSeq = sequenceOf(1, 2, 3, 4, 5).flatMap(seqTo)
    seqOfSeq.forEach { println("$it") }
}
