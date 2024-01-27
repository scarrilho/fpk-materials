package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.Monoid

object MonoidStringConcat: Monoid<String> {
    override val unit: String
        get() = ""
    override val combine: String.(String) -> String
        get() = String::plus
}