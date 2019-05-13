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

   /* internal inner class LRUCacheFb(private val capacity: Integer) {

        private val lruMap: MutableMap<Int, Int>
        private val lruQueue: Queue<Int>

        init {
            lruMap = HashMap()
            lruQueue = LinkedList()
        }

        operator fun get(key: Integer): Int {
            if (lruMap.containsKey(key)) {
                refreshEntry(key)
                return lruMap[key]
            } else {
                return -1
            }
        }

        fun put(key: Int, value: Int) {
            if (lruMap.containsKey(key)) {
                refreshEntry(key)
                lruMap[key] = value
            } else {
                if (lruQueue.size == capacity) {
                    val valToRemove = lruQueue.peek()
                    lruMap.remove(valToRemove)
                    lruQueue.remove(valToRemove)
                }
                lruMap[key] = value
                lruQueue.add(key)

            }

        }


        private fun refreshEntry(key: Int) {
            lruQueue.remove(key)
            lruQueue.add(key)
        }
    }


    *//*LRU cache in O(1) using HashTable and Double linked list*//*

    internal inner class BestLRU(private val capacity: Int) {

        private var head: Node? = null
        private var tail: Node? = null
        private val ticketsMap: HashMap<Int, Node>

        private inner class Node internal constructor(internal var key: Int, internal var value: Int) {
            internal var prev: Node? = null
            internal var next: Node? = null


        }

        init {
            this.ticketsMap = HashMap()
        }


        operator fun get(key: Int): Int {

            if (!ticketsMap.containsKey(key)) {
                return -1
            }

            val n = ticketsMap[key]
            removeFromQueue(n)
            setHead(n)

            return n.value

        }

        fun put(key: Int, value: Int) {

            if (ticketsMap.containsKey(key)) {
                val n = ticketsMap[key]
                n.value = value
                removeFromQueue(n)
                setHead(n)
            } else {
                if (ticketsMap.size >= capacity) {
                    ticketsMap.remove(tail!!.key)
                    removeFromQueue(tail!!)

                }
                val n = Node(key, value)
                setHead(n)
                ticketsMap[key] = n
            }
        }

        //set a node to be head
        private fun setHead(t: Node) {
            if (head != null) {
                head!!.prev = t
            }
            t.next = head
            t.prev = null

            head = t

            if (tail == null) {
                tail = head
            }
        }

        private fun removeFromQueue(node: Node) {
            if (node.next != null) {
                node.next!!.prev = node.prev
            } else {
                tail = node.prev
            }

            if (node.prev != null) {
                node.prev!!.next = node.next
            } else {
                head = node.next
            }
        }

    }

*/
}