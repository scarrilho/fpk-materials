package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.Monoid

object MonoidIntMul: Monoid<Int> {
    override val unit: Int
        get() = 1
    override val combine: Int.(Int) -> Int
        get() = Int::times
}
