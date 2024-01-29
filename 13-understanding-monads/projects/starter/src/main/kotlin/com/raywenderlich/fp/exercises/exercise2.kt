package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.Fun

fun <A> Optional<A>.lift(value: A): Optional<A> =
    Optional.lift(value)

fun <A, B> Optional<A>.flatMap(fn: Fun<A, Optional<B>>):
        Optional<B> = map(::lift optionalFish fn).optionalFlatten()