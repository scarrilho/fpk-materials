package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.Fun

inline infix fun <A, B, C> Fun<A, B>.compose(crossinline g: Fun<B, C>): Fun<A, C> =
    {a: A -> g(this(a))}