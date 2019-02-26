package com.prepcode.tree

import java.util.*
import kotlin.collections.HashMap

class TreeQuestions {
    fun printDFSRecursive(root: TreeNode?) {
        if (root == null) {
            return
        }
        println(" value is " + root.value)
        printDFSRecursive(root.left)
        printDFSRecursive(root.right)

    }

    fun printBFSRecursive(root: TreeNode?) {
        if (root == null) {
            return
        }
        val queue = ArrayDeque<TreeNode>()
        queue.add(root)

        while (!queue.isEmpty()) {
            val node = queue.poll()
            println("value is " + node.value)
            if (node.left != null) queue.add(node.left)
            if (node.right != null) queue.add(node.right)
        }

    }

    fun isTreeComplete(root: TreeNode?): Boolean {

        if (root == null) {
            return false
        }
        var result = true
        val queue = ArrayDeque<TreeNode>()
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

    fun printBinaryTreeInOrderTraversal(root: TreeNode?) {
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

    fun putBinaryInOrderInList(root: TreeNode?): List<Int> {
        val returnList = ArrayList<Int>()
        helperBinaryInOrderInList(root, returnList)
        return returnList
    }

    fun helperBinaryInOrderInList(root: TreeNode?, list: ArrayList<Int>) {
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


    fun zigzagTreeLevelOrder(root: TreeNode?): List<List<Int>> {
        val resList = ArrayList<ArrayList<Int>>()
        var childList = ArrayList<Int>()
        if (root === null) {
            resList
        }
        val queue = LinkedList<TreeNode?>()
        val stack = Stack<TreeNode?>()
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


    fun serializeTree(treeNode: TreeNode?): String? {
        val sb = StringBuilder()
        if (treeNode == null) return null
        sb.append(treeNode.value.toString() + ",")
        putTreeIntoString(treeNode, sb)
        return sb.toString().trimEnd(',')
    }

    private fun putTreeIntoString(treeNode: TreeNode?, sb: StringBuilder) {

        if (treeNode == null) return
        if (treeNode.left != null) {
            sb.append(treeNode.left.value.toString() + ',')
        } else {
            sb.append("null" + ",")
        }
        if (treeNode.right != null) {
            sb.append(treeNode.right.value.toString() + ',')
        } else {
            sb.append("null" + ",")
        }
        putTreeIntoString(treeNode.left, sb)
        putTreeIntoString(treeNode.right, sb)

    }

    // deserialization is in java file


    /*Find all duplicate sub trees */

    fun findDuplicateSubtrees(root: TreeNode?): List<TreeNode?> {
        val map = HashMap<String, Int>()
        val resList = mutableListOf<TreeNode>()
        recordDuplicateSubTrees(map, resList, root)
        return resList
    }

    private fun recordDuplicateSubTrees(
        map: HashMap<String, Int>,
        resList: MutableList<TreeNode>,
        root: TreeNode?
    ): String {

        if (root == null) {
            return "#"
        }
        val sb = java.lang.StringBuilder()
        sb.append(recordDuplicateSubTrees(map, resList, root.left))
        sb.append(root.value)
        sb.append(recordDuplicateSubTrees(map, resList, root.right))


        if (map.containsKey(sb.toString()) && map[sb.toString()] == 1) {
            map[sb.toString()] = map[sb.toString()]!!.plus(1)
            resList.add(root)
        } else {
            map[sb.toString()] = 1
        }

        return sb.toString()
    }
}