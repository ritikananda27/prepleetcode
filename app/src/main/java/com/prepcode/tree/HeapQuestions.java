package com.prepcode.tree;

public class HeapQuestions {

    public TreeNode insertIntoMinHeap(TreeNode root, TreeNode node) {

        if (root == null) return null;
        if (root.getVal() > node.getVal() && node != null) {
            TreeNode temp = root;
            root = node;
            root.setLeft(temp);
            node = null;
            return root;
        }

        TreeNode leftInsert = insertIntoMinHeap(root.getLeft(), node);
        root.setLeft(leftInsert);
        TreeNode rightInsert = insertIntoMinHeap(root.getRight(), node);
        root.setRight(rightInsert);


        return root;
    }

}
