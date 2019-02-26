package com.prepcode.tree

import java.util.*

class TimeMap() {

    private val mainMap = mutableMapOf<String, java.util.TreeMap<Int, String>>()

    fun set(key: String, value: String, timestamp: Int) {
        if (!mainMap.containsKey(key)) {
            mainMap[key] = TreeMap()
        }
        mainMap[key]!![timestamp] = value
    }

    fun get(key: String, timestamp: Int): String {
        val valueMap = mainMap[key]
        if (valueMap.isNullOrEmpty()) return ""

        val floor: Int? = valueMap.floorKey(timestamp)
        return valueMap[floor]!!

    }

}