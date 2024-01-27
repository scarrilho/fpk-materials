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
@file:JvmName("PropertyTest")

package com.raywenderlich.fp

import org.junit.Test
import kotlin.random.Random

/** Simple sum of two integer */
fun sum(a: Int, b: Int): Int = a + b

// fun sum(a: Int, b: Int): Int = a * b
// fun sum(a: Int, b: Int): Int = 0

fun interface Generator<T> {
    fun generate(n: Int): List<T>
}

object IntGenerator: Generator<Int> {
    override fun generate(n: Int): List<Int> = List(n) {
        Random.nextInt()
    }
}

interface Property<T> {
    operator fun invoke(
        gen: Generator<T>,
        fn: (List<T>) -> T
    ): Boolean
}

class CommutativeProperty<T> : Property<T> {
    override fun invoke(gen: Generator<T>, fn: (List<T>) -> T): Boolean {
        val values = gen.generate(2)
        val res1 = fn(listOf(values[0], values[1]))
        val res2 = fn(listOf(values[1], values[0]))
        return res1 == res2
    }
}

class AssociativeProperty<T> : Property<T> {
    override fun invoke(gen: Generator<T>, fn: (List<T>) -> T): Boolean {
       val values = gen.generate(3)
        val res1 = fn(listOf(fn(listOf(values[0], values[1])), values[2]))
        val res2 = fn(listOf(values[0], fn(listOf(values[1], values[2]))))
        return res1 == res2
    }
}

class IdentityProperty<T>(private val unit: T) : Property<T> {
    override fun invoke(gen: Generator<T>, fn: (List<T>) -> T): Boolean {
       val randomValue = gen.generate(1)[0]
        val res1 = fn(listOf(randomValue, unit))
        val res2 = fn(listOf(unit, randomValue))
        return res1 == randomValue && res2 == randomValue
    }
}


infix fun <T> Property<T>.and(
    rightProp: Property<T>
): Property<T> = object : Property<T> {
    override fun invoke(
        gen: Generator<T>,
        fn: (List<T>) -> T
    ): Boolean =
       this@and(gen, fn) && rightProp(gen, fn)
}