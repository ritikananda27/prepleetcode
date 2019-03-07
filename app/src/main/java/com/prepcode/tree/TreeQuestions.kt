package com.prepcode.tree

import java.util.*
import kotlin.collections.HashMap

class TreeQuestions {
    fun printDFSRecursive(root: TreeNodeT?) {
        if (root == null) {
            return
        }
        println(" value is " + root.value)
        printDFSRecursive(root.left)
        printDFSRecursive(root.right)

    }

    fun printBFSRecursive(root: TreeNodeT?) {
        if (root == null) {
            return
        }
        val queue = ArrayDeque<TreeNodeT>()
        queue.add(root)

        while (!queue.isEmpty()) {
            val node = queue.poll()
            println("value is " + node.value)
            if (node.left != null) queue.add(node.left)
            if (node.right != null) queue.add(node.right)
        }

    }

    fun isTreeComplete(root: TreeNodeT?): Boolean {

        if (root == null) {
            return false
        }
        var result = true
        val queue = ArrayDeque<TreeNodeT>()
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

    fun printBinaryTreeInOrderTraversal(root: TreeNodeT?) {
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

    fun putBinaryInOrderInList(root: TreeNodeT?): List<Int> {
        val returnList = ArrayList<Int>()
        helperBinaryInOrderInList(root, returnList)
        return returnList
    }

    fun helperBinaryInOrderInList(root: TreeNodeT?, list: ArrayList<Int>) {
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


    fun zigzagTreeLevelOrder(root: TreeNodeT?): List<List<Int>> {
        val resList = ArrayList<ArrayList<Int>>()
        var childList = ArrayList<Int>()
        if (root === null) {
            resList
        }
        val queue = LinkedList<TreeNodeT?>()
        val stack = Stack<TreeNodeT?>()
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


    fun serializeTree(treeNodeT: TreeNodeT?): String? {
        val sb = StringBuilder()
        if (treeNodeT == null) return null
        sb.append(treeNodeT.value.toString() + ",")
        putTreeIntoString(treeNodeT, sb)
        return sb.toString().trimEnd(',')
    }

    private fun putTreeIntoString(treeNodeT: TreeNodeT?, sb: StringBuilder) {

        if (treeNodeT == null) return
        if (treeNodeT.left != null) {
            sb.append(treeNodeT.left!!.value.toString() + ',')
        } else {
            sb.append("null" + ",")
        }
        if (treeNodeT.right != null) {
            sb.append(treeNodeT.right!!.value.toString() + ',')
        } else {
            sb.append("null" + ",")
        }
        putTreeIntoString(treeNodeT.left, sb)
        putTreeIntoString(treeNodeT.right, sb)

    }

    // deserialization is in java file


    /*Find all duplicate sub trees */

    fun findDuplicateSubtrees(root: TreeNodeT?): List<TreeNodeT?> {
        val map = HashMap<String, Int>()
        val resList = mutableListOf<TreeNodeT>()
        recordDuplicateSubTrees(map, resList, root)
        return resList
    }

    private fun recordDuplicateSubTrees(
            map: HashMap<String, Int>,
            resList: MutableList<TreeNodeT>,
            root: TreeNodeT?
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


    fun mergeTrees(t1: TreeNodeT?, t2: TreeNodeT?): TreeNodeT? {
        if (t1 == null && t2 == null) {
            return null
        }
        if (t1 == null && t2 != null) {
            return t2
        }
        if (t2 == null && t1 != null) {
            return t1
        }

        val res = TreeNodeT(t1!!.value + t2!!.value)
        res.left = mergeTrees(t1.left, t2.left)
        res.right = mergeTrees(t1.right, t2.right)
        return res
    }

    fun levelOrder(root: TreeNodeT?): List<List<Int>> {
        if (root == null) return emptyList()
        val resList = mutableListOf<List<Int>>()
        val queue = java.util.LinkedList<Pair<TreeNodeT, Int>>()
        queue.offer(Pair(root, 0))
        generateLevelOrder(queue, resList)
        return resList
    }

    private fun generateLevelOrder(queue: java.util.Queue<Pair<TreeNodeT, Int>>, resList: MutableList<List<Int>>) {
        var level = 0
        var mutableChildList: MutableList<Int> = mutableListOf()
        while (!queue.isEmpty()) {
            val pair = queue.poll()
            if (pair.second == level) {
                mutableChildList.add(pair.first.value)
            } else {
                resList.add(mutableChildList)
                mutableChildList = mutableListOf()
                level = pair.second
                mutableChildList.add(pair.first.value)
            }
            if (pair.first.left != null) {
                queue.offer(Pair(pair.first.left!!, pair.second.plus(1)))
            }

            if (pair.first.right != null) {
                queue.offer(Pair(pair.first.right!!, pair.second.plus(1)))
            }

        }

        if (mutableChildList.size > 0) {
            resList.add(mutableChildList)
        }
    }


    fun rightSideView(root: TreeNodeT?): List<Int> {

        if (root == null) return emptyList()
        val queue = java.util.LinkedList<Pair<TreeNodeT, Int>>()
        queue.add(Pair(root, 0))
        var levelsCovered = mutableListOf<Int>()
        val resList = mutableListOf<Int>()

        while (!queue.isEmpty()) {
            val pair = queue.poll()
            if (!levelsCovered.contains(pair.second)) {
                resList.add(pair.first.value)
                levelsCovered.add(pair.second)
            }
            val node = pair.first
            if (node.right != null) {
                queue.add(Pair(node.right!!, pair.second.plus(1)))
            }
            if (node.left != null) {
                queue.add(Pair(node.left!!, pair.second.plus(1)))
            }
        }

        return resList
    }

    fun insertIntoBST(root: TreeNode?, `val`: Int): TreeNode? {
        if (root == null) return TreeNode(`val`)

        if (`val` <= root.`val`) {
            if (root.left == null) {
                root.left = TreeNode(`val`)
                return root
            } else {
                insertIntoBST(root.left, `val`)
            }
        } else {
            if (root.right == null) {
                root.right = TreeNode(`val`)
                return root
            } else {
                insertIntoBST(root.right, `val`)
            }
        }

        return root
    }


}