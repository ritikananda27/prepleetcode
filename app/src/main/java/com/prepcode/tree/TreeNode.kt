package com.prepcode.tree

class TreeNode(val value: Int, var left: TreeNode? = null, var right: TreeNode? = null) {

}

class MutableNode(val value: Int, var left: MutableNode? = null, var right: MutableNode? = null) {

}

class Tree() {
    private var n1: TreeNode

    init {
        val n8 = TreeNode(8, null, null)
        val n9 = TreeNode(9, null, null)
        val n11 = TreeNode(11, null, null)
        val n10 = TreeNode(10, n11, null)
        val n7 = TreeNode(7, n10, null)
        val n6 = TreeNode(6, null, null)
        val n5 = TreeNode(5, n8, n9)
        val n4 = TreeNode(4, null, null)
        val n2 = TreeNode(2, n4, n5)
        val n3 = TreeNode(3, n6, n7)

        n1 = TreeNode(1, n2, n3)
    }

    fun getBasicTree1(): TreeNode {
        val n5 = TreeNode(5, null, null)
        val n2 = TreeNode(2, null, null)
        val n3 = TreeNode(3, n5, null)
        n1 = TreeNode(1, n3, n2)
        return n1
    }


    fun getBasicTree2(): TreeNode {
        val n4 = TreeNode(4, null, null)
        val n7 = TreeNode(7, null, null)
        val n3 = TreeNode(3, null, n7)
        val n1 = TreeNode(1, null, n4)
        val n2 = TreeNode(2, n1, n3)
        return n2
    }

    fun getEmptyTree(treeNode: TreeNode): TreeNode {
        return treeNode
    }


    fun getTreeNoeWithDuplicateSubTrees(): TreeNode {
        val n4 = TreeNode(4, null, null)
        val n2 = TreeNode(2, n4, null)
        val n3 = TreeNode(3, n2, n4)
        val n1 = TreeNode(1, n2, n3)

        return n1
    }
}