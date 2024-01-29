package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.Fun
import java.awt.geom.NoninvertibleTransformException
import java.lang.module.ModuleDescriptor.Opens

sealed class Optional<out T> {
    companion object {
        @JvmStatic
        fun <T> lift(value: T): Optional<T> = Some(value)

        @JvmStatic
        fun <T> empty(): Optional<T> = None
    }

    object None : Optional<Nothing>()
    data class Some<T>(val value: T) : Optional<T>()


    fun <A, B> Optional<A>.map(fn: Fun<A, B>): Optional<B> =
        when (this) {
            is Some<A> -> Optional.Some(fn(this.value))
            is None -> None
        }

    fun <T> Optional<Optional<T>>.optionalFlatten(): Optional<T> =
        when (this) {
            is Some<Optional<T>> -> when (this.value) {
                is Some<T> ->Optional.lift<T>(this.value.value)
                is None -> Optional.empty()
            }
            is None -> Optional.empty()
        }

    infix fun <B, C> Optional<B>.optionalBind(
        g: Fun<B, Optional<C>>
    ): Optional<C> = map(g).optionalFlatten()

    infix fun <A, B, C> Fun<A, Optional<B>>.optionalFish(
        g: Fun<B, Optional<C>>
    ): Fun<A, Optional<C>> = { a: A ->
        this(a).optionalBind(g)
    }
}