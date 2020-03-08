package com.qifan.theforktest.ui.notifier

interface ErrorListener {
    fun showError(error: Throwable?)
}