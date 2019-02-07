package com.prepcode.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeJava {


    public MutableNode deserializeTree(String treeString) {

        if (treeString == null || treeString.isEmpty()) {
            return null;
        }
        String[] list = treeString.split(",");
        MutableNode root = null;
        if (list.length == 1) {
            if (!list[0].equals("null")) {
                root = new MutableNode(Integer.parseInt(list[0]), null, null);
            }
        } else {

            ArrayList arrlist = new ArrayList();

            for (int i = 0; i < list.length; i++) {
                arrlist.add(list[i]);
            }
            root = new MutableNode(Integer.parseInt(arrlist.get(0).toString()), null, null);
            arrlist.remove(0);
            convertStringToTree(root, (arrlist));
        }
        return root;
    }


    private void convertStringToTree(MutableNode root, ArrayList list) {


        if (root == null) return;

        if (list.size() > 0) {
            String leftNodeVal = list.get(0).toString();
            MutableNode leftNode = null;
            if (!leftNodeVal.equals("null")) {
                leftNode = new MutableNode(Integer.parseInt(list.get(0).toString()), null, null);
            }
            list.remove(0);

            String rightNodeVal = list.get(0).toString();
            MutableNode rightNode = null;
            if (!rightNodeVal.equals("null")) {
                rightNode = new MutableNode(Integer.parseInt(list.get(0).toString()), null, null);
            }
            list.remove(0);
            root.setLeft(leftNode);
            root.setRight(rightNode);

            convertStringToTree(root.getLeft(), list);
            convertStringToTree(root.getRight(), list);
        }

    }


    /* Take eack element, form a root node and then set left n right on that node recursively */
    public MutableNode deserializeTestInOrder(List<Integer> valueList) {
        if (valueList.size() == 0) return null;
        return deserializeTreeImpl(valueList);
    }

    MutableNode deserializeTreeImpl(List list) {

        if (list.size() == 0) return null;

        int val = (int) list.get(0);
        list.remove(0);
        if (val == -1) {
            return null;
        }

        MutableNode root = new MutableNode(val, null, null);
        root.setLeft(deserializeTreeImpl(list));
        root.setRight(deserializeTreeImpl(list));

        return root;

    }


}
