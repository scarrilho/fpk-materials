package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.Fun

fun <A: Any, B: Any> A?.map(fn: Fun<A, B>): B? =
   if (this != null) fn(this).lift() else null

fun <A: Any, B: Any> A?.flatMap(fn: Fun<A, B?>): B? =
    if (this != null) fn(this)?.lift() else null