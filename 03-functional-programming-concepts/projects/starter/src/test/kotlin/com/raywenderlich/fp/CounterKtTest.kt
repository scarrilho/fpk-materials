package com.raywenderlich.fp

import com.google.common.truth.Truth.assertThat
import com.raywenderlich.fp.util.invokeTimes
import org.junit.Test

class CounterKtTest {
    @Test
    fun test100Times() {
        var c = 0
        100.invokeTimes {
            val (count, _) = countedAbs(c, it)
            c = count
        }
        assertThat(c).isEqualTo(100)
    }

    @Test
    fun test50Times() {
        var c = 0
        50.invokeTimes {
            val (count, _) = countedAbs(c, it)
            c = count
        }
        assertThat(c).isEqualTo(50)
    }
}