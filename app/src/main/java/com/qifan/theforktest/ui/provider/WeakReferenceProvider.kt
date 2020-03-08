package com.qifan.theforktest.ui.provider

import java.lang.ref.WeakReference
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class WeakReferenceProvider<T> : ReadWriteProperty<Any, T> {
    private var mRef: WeakReference<T>? = null

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return mRef?.get() ?: throw IllegalStateException()
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        this.mRef = WeakReference(value)
    }
}
