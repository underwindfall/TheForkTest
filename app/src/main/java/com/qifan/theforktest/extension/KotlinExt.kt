package com.qifan.theforktest.extension

inline fun <T : Any> guardLet(vararg elements: T?, closure: (List<T>) -> Unit) {
    if (elements.all { it != null }) {
        closure(elements.filterNotNull())
    }
}

inline fun <T : Any> ifSomeNull(vararg elements: T?, closure: () -> Nothing) {
    if (elements.any { it == null }) {
        closure()
    }
}