package com.prepcode.tree

import java.util.*

class TreeQuestions {
    fun printDFSRecursive(root: Node?) {
        if (root == null) {
            return
        }
        println(" value is " + root.value)
        printDFSRecursive(root.left)
        printDFSRecursive(root.right)

    }

    fun printBFSRecursive(root: Node?) {
        if (root == null) {
            return
        }
        val queue = ArrayDeque<Node>()
        queue.add(root)

        while (!queue.isEmpty()) {
            val node = queue.poll()
            println("value is " + node.value)
            if (node.left != null) queue.add(node.left)
            if (node.right != null) queue.add(node.right)
        }

    }

    fun isTreeComplete(root: Node?): Boolean {

        if (root == null) {
            return false
        }
        var result = true
        val queue = ArrayDeque<Node>()
        queue.add(root)

        while (!queue.isEmpty()) {
            val node = queue.poll()
            if (node.left != null) {
                queue.add(node.left)
            } else {
                if (node.right != null) {
                    result = false
                    break
                }
            }
            if (node.right != null) {
                queue.add(node.right)
            }
        }

        println("result is " + result)
        return result


    }

    fun printBinaryTreeInOrderTraversal(root: Node?) {
        if (root == null) {
            return
        }
        if (root.left != null) {
            printBinaryTreeInOrderTraversal(root.left)
        }
        println(root.value)
        if (root.right != null) {
            printBinaryTreeInOrderTraversal(root.right)
        }
    }

    fun putBinaryInOrderInList(root: Node?): List<Int> {
        val returnList = ArrayList<Int>()
        helperBinaryInOrderInList(root, returnList)
        return returnList
    }

    fun helperBinaryInOrderInList(root: Node?, list: ArrayList<Int>) {
        if (root != null) {
            if (root.left != null) {
                helperBinaryInOrderInList(root.left, list)
            }
            list.add(root.value)
            if (root.right != null) {
                helperBinaryInOrderInList(root.right, list)
            }

        }
    }


    fun zigzagTreeLevelOrder(root: Node?): List<List<Int>> {
        val resList = ArrayList<ArrayList<Int>>()
        var childList = ArrayList<Int>()
        if (root === null) {
            resList
        }
        val queue = LinkedList<Node?>()
        val stack = Stack<Node?>()
        queue.add(root)
        queue.add(null)
        var normal = false;

        while (!queue.isEmpty()) {
            val node = queue.poll()
            if (node != null) {
                if (normal) {
                    childList.add(node.value)
                    if (node.left != null) queue.add(node.left)
                    if (node.right != null) queue.add(node.right)
                } else {
                    if (node.left != null) queue.add(node.left)
                    if (node.right != null) queue.add(node.right)
                    stack.add(node)
                }
            } else {
                normal = !normal
                if (!queue.isEmpty()) {
                    queue.add(null)
                }
                while (!stack.isEmpty()) {
                    childList.add(stack.pop()!!.value)
                }
                resList.add(childList)
                childList = ArrayList()
            }

        }
        return resList
    }


    /*Serialize and Deserialize a tree - convert the tree nodes into string such that it can be recreated from it */


    fun serializeTree(node: Node?): String? {
        val sb = StringBuilder()
        if (node == null) return null
        sb.append(node.value.toString() + ",")
        putTreeIntoString(node, sb)
        return sb.toString().trimEnd(',')
    }

    private fun putTreeIntoString(node: Node?, sb: StringBuilder) {

        if (node == null) return
        if (node.left != null) {
            sb.append(node.left.value.toString() + ',')
        } else {
            sb.append("null" + ",")
        }
        if (node.right != null) {
            sb.append(node.right.value.toString() + ',')
        } else {
            sb.append("null" + ",")
        }
        putTreeIntoString(node.left, sb)
        putTreeIntoString(node.right, sb)

    }

    // deserialization is in java file
}