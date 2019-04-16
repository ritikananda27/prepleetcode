package com.prepcode.tree

class TreeNodeT(val value: Int, var left: TreeNodeT? = null, var right: TreeNodeT? = null) {

}


data class TreeNode(val `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class MutableNode(val value: Int, var left: MutableNode? = null, var right: MutableNode? = null) {

}

class Tree() {
    private var n1: TreeNodeT

    init {
        val n8 = TreeNodeT(8, null, null)
        val n9 = TreeNodeT(9, null, null)
        val n11 = TreeNodeT(11, null, null)
        val n10 = TreeNodeT(10, n11, null)
        val n7 = TreeNodeT(7, n10, null)
        val n6 = TreeNodeT(6, null, null)
        val n5 = TreeNodeT(5, n8, n9)
        val n4 = TreeNodeT(4, null, null)
        val n2 = TreeNodeT(2, n4, n5)
        val n3 = TreeNodeT(3, n6, n7)

        n1 = TreeNodeT(1, n2, n3)
    }

    fun getBasicTree1(): TreeNodeT {
        val n5 = TreeNodeT(5, null, null)
        val n2 = TreeNodeT(2, null, null)
        val n3 = TreeNodeT(3, n5, null)
        n1 = TreeNodeT(1, n3, n2)
        return n1
    }


    fun getBasicTree2(): TreeNodeT {
        val n4 = TreeNodeT(4, null, null)
        val n7 = TreeNodeT(7, null, null)
        val n3 = TreeNodeT(3, null, n7)
        val n1 = TreeNodeT(1, null, n4)
        val n2 = TreeNodeT(2, n1, n3)
        return n2
    }

    fun getEmptyTree(treeNodeT: TreeNodeT): TreeNodeT {
        return treeNodeT
    }


    fun getMinHeap() :TreeNode {

        val n1 = TreeNode(1)
        val n3 = TreeNode(3)
        val n8 = TreeNode(8)
        val n9 = TreeNode(9)
        val n4 = TreeNode(4)
        val n12 = TreeNode(12)
        val n14 = TreeNode(14)

        n1.left = n4
        n1.right = n3
        n3.left = n8
        n3.right = n9
       /* n4.left = n12
        n4.right = n14*/

        return n1

    }

    fun getBST(): TreeNode {
        val n4 = TreeNode(4)
        val n2 = TreeNode(2)
        val n7 = TreeNode(7)
        val n1 = TreeNode(1)
        val n3 = TreeNode(3)

        n4.left = n2
        n4.right = n7
        n2.left = n1
        n2.right = n3

        return n4
    }

    fun getSymantecTree(): TreeNode {
        val n4 = TreeNode(4)
        val n2 = TreeNode(2)
        val n22 = TreeNode(2)
        val n1 = TreeNode(1)
        val n3 = TreeNode(3)

        n1.left = n2
        n1.right = n22

        n2.left = n3
        n2.right = n4

        n22.left = n4
        n22.right = n3

        return n1
    }

    fun getTreeNoeWithDuplicateSubTrees(): TreeNodeT {
        val n4 = TreeNodeT(4, null, null)
        val n2 = TreeNodeT(2, n4, null)
        val n3 = TreeNodeT(3, n2, n4)
        val n1 = TreeNodeT(1, n2, n3)

        return n1
    }
}