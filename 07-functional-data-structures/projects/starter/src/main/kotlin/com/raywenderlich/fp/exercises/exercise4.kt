package com.raywenderlich.fp.exercises

sealed class IFList<out T>: Iterable<T> {
    companion object {
        @JvmStatic
        fun <T> of(vararg items: T): IFList<T> {
            val tail = items.sliceArray(1 until items.size)
            return if (items.isEmpty()) {
                empty()
            } else {
                ICons(items[0], of(*tail))
            }
        }

        @JvmStatic
        fun <T> empty(): IFList<T> = INil
    }
}

internal object INil : IFList<Nothing>() {
    override fun iterator(): Iterator<Nothing> = object : Iterator<Nothing> {
        override fun hasNext(): Boolean = false

        override fun next(): Nothing = throw NoSuchElementException()
    }
}

internal data class ICons<T>(
    val head: T,
    val tail: IFList<T> = INil
) : IFList<T>() {
    override fun iterator(): Iterator<T> = object : Iterator<T> {
        var current: IFList<T> = this@ICons
        override fun hasNext(): Boolean = current is ICons<T>

        override fun next(): T {
            val asICons = current as? ICons<T> ?: throw NoSuchElementException()
            current = asICons.tail
            return asICons.head
        }
    }
}

fun main() {
    IFList.of(1, 2, 3).forEach {
        println(it)
    }

    IFList.of("a", "b", "c").forEachIndexed { index, s ->
        println("$index $s")
    }
}