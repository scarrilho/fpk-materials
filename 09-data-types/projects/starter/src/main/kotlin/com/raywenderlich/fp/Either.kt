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

sealed class Either<out A, out B> {
    companion object {
        @JvmStatic
        fun <A> left(left: A): Either<A, Nothing> = Left(left)

        @JvmStatic
        fun <B> right(right: B): Either<Nothing, B> = Right(right)
    }
}

data class Left<A>(val left: A) : Either<A, Nothing>()
data class Right<B>(val right: B): Either<Nothing, B>()

fun <A, B, C, D> Either<A, B>.bimap(
    fl: (A) -> C,
    fr: (B) -> D
): Either<C, D> = when (this) {
    is Left<A> -> Either.left(fl(left))
    is Right<B> -> Either.right(fr(right))
}

fun <A, B, C> Either<A, B>.leftMap(
    fl: (A) -> C
): Either<C, B> = when(this){
    is Left<A> -> Either.left(fl(left))
    is Right<B> -> this
}

fun <A, B, D> Either<A, B>.rightMap(
    fr: (B) -> D
): Either<A, D> = when (this) {
    is Right<B> -> Either.right(fr(right))
    is Left<A> -> this
}
 fun <A, B> Either<A, B>.getOrDefault(
     defaultValue: B
 ): B = when (this) {
     is Left<A> -> defaultValue
     is Right<B> -> right
 }

fun <A, B> Either<A, B>.getRightOrDefault(
    defaultValue: B
): B = when (this) {
    is Left<A> -> defaultValue
    is Right<B> -> right
}

fun <A, B> Either<A,B>.getLeftOrDefault(
    defaultValue: A
): A = when (this) {
    is Left<A> -> left
    is Right<B> -> defaultValue
}

fun <A, B> Either<A, B>.flip(): Either<B, A> =
    when (this) {
        is Left<A> -> Either.right(left)
        is Right<B> -> Either.left(right)
    }