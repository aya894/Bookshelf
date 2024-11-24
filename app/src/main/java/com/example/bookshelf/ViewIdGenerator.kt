package com.example.bookshelf

import java.util.concurrent.atomic.AtomicInteger

object ViewIdGenerator {
    private val id = AtomicInteger(1)
    fun generateViewId(): Int {
        while (true) {
            val result = id.get()
            val newValue = result + 1
            if (newValue > Int.MAX_VALUE) { // Reset si la valeur dépasse Int.MAX_VALUE
                id.set(1)
            }
            if (id.compareAndSet(result, newValue)) {
                return result
            }
        }
    }
}
