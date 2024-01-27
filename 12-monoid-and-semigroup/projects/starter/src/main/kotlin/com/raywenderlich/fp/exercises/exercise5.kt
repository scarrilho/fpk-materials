package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.Generator
import com.raywenderlich.fp.Property

class DoubleIncrementProperty : Property<Int> {
    override fun invoke(gen: Generator<Int>, fn: (List<Int>) -> Int): Boolean {
        val randomValue = gen.generate(1)[0]
        val res1 = fn(listOf(fn(listOf(randomValue, 1)), 1))
        val res2 = fn(listOf(randomValue, 2))
        return res1 == res2
    }
}