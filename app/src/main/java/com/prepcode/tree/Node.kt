package com.prepcode.tree

class Node(val value: Int, val left: Node?, val right: Node?) {

}

class MutableNode(val value: Int, var left: MutableNode?=null, var right: MutableNode?=null) {

}

class Tree() {
    private var n1: Node

    init {
        val n8 = Node(8, null, null)
        val n9 = Node(9, null, null)
        val n11 = Node(11, null, null)
        val n10 = Node(10, n11, null)
        val n7 = Node(7, n10, null)
        val n6 = Node(6, null, null)
        val n5 = Node(5, n8, n9)
        val n4 = Node(4, null, null)
        val n2 = Node(2, n4, n5)
        val n3 = Node(3, n6, n7)

        n1 = Node(1, n2, n3)
    }

    fun getBasicTree(): Node {
        return n1
    }

    fun getEmptyTree(node: Node): Node {
        return node
    }
}