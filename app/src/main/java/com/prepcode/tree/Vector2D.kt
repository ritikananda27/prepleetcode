package com.prepcode.tree

class Vector2D(v: Array<IntArray>) {

    private val list = mutableListOf<Int>()

    init {
        v.forEach {
            it.forEach {
                list.add(it)
            }
        }
    }

    fun next(): Int {
        return if (list.size > 0) {
            list[0]
            list.removeAt(0)
        } else -1
    }

    fun hasNext(): Boolean {
        return list.size > 0
    }
}