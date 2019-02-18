package com.prepcode.tree

import java.util.*
import java.util.LinkedList

class LRUCache(private val capacity: Int) {

    private val queue = LinkedList<Int>()
    private val map = HashMap<Int, Int>()


    fun get(key: Int): Int {

        return if (map.containsKey(key)) {
            markKeyRecentlyUsed(key)
            map[key]!!
        } else {
            -1
        }
    }

    fun put(key: Int, value: Int) {

        if (map.containsKey(key)) {
            map[key] = value
            markKeyRecentlyUsed(key)
        } else {
            if (queue.size == capacity) {
                val eleToRemove = queue.poll()
                map.remove(eleToRemove)
            }
            map[key] = value
            queue.add(key)
        }

    }


    private fun markKeyRecentlyUsed(key: Int) {
        queue.remove(key)
        queue.add(key)
    }


}