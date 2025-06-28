package com.iagoaf.movieexplorer

import org.junit.Assert.assertEquals
import org.junit.Test


class CounterViewModel {
    var count = 0
        private set

    fun increment() {
        count++
    }

}

class CounterViewModelTest {
    @Test
    fun increment_aumentaContador() {
        val vm = CounterViewModel()
        vm.increment()
        assertEquals(1, vm.count)
    }
}