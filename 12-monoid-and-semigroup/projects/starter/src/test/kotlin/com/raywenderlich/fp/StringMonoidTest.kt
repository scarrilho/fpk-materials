package com.raywenderlich.fp

import com.google.common.truth.Truth
import com.raywenderlich.fp.exercises.MonoidStringConcat
import org.junit.Test

class StringMonoidTest {
    @Test
    fun `test string concat using generators`() {
        100.times {
            val stringConcatProp =
                AssociativeProperty<String>() and
                        IdentityProperty("")
            val evaluation = stringConcatProp(StringGenerator()) {
                MonoidStringConcat.combine(it[0], it[1])
            }
            Truth.assertThat(evaluation).isTrue()
        }
    }
}