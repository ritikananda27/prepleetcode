package com.prepcode.tree

import java.util.*

class InMemoryDatabase<T> {

    private val inMemoryMap: HashMap<Any, Any> = HashMap()

    fun put(objectToSave: T) {
        objectToSave as SaveObject
        transaction(objectToSave.getKey(), objectToSave.getValue())
    }

    fun get(key: Any): Any? {
        return inMemoryMap[key]
    }

    private fun transaction(key: Any, value: Any) {
        synchronized(this) {
            inMemoryMap[key] = value
        }
    }
}


abstract class SaveObject {
    abstract fun getKey(): Any
    abstract fun getValue(): Any
}



