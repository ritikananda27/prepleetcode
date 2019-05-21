package com.prepcode.tree;

import java.util.*;

public class TreeJava {


    private Map<String, TreeMap<Integer, String>> map = new HashMap<>();


    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new TreeMap<>());
        }
        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer, String> treeMap = map.get(key);
        if (treeMap == null) {
            return "";
        }
        Integer floor = treeMap.floorKey(timestamp);
        if (floor == null) {
            return "";
        }
        return treeMap.get(floor);


    }


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


    static String decrypt(String word) {
        if (word.isEmpty()) return null;
        StringBuffer sb = new StringBuffer();
        int middle = 1;

        for (int i = 0; i < word.length(); i++) {
            int newVal = word.charAt(i);
            newVal = newVal - middle;

            while (newVal < 'a') {
                newVal = newVal + 26;
            }
            sb.append((char) newVal);
            middle = middle + newVal;
        }
        return sb.toString();

    }

    /*Deapth first way */
    /*Serialize and Deserialize a tree */

    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        serializeImpl(sb, root);
        return sb.toString();
    }


    private void serializeImpl(java.lang.StringBuilder sb, TreeNode head) {

        if (head == null) {
            sb.append("#");
            return;
        }
        sb.append(head.getVal());
        serializeImpl(sb, head.getLeft());
        serializeImpl(sb, head.getRight());

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        List dataList = new ArrayList();

        for (int i = 0; i < data.length(); i++) {
            dataList.add(data.charAt(i));
        }

        return desertializeImpl(dataList);
    }

    private TreeNode desertializeImpl(List<Character> data) {
        if (data.size() == 0) return null;
        char val = data.get(0);
        data.remove(0);
        if (val == '#') {
            return null;
        }
        TreeNode head = new TreeNode(Integer.parseInt(Character.toString(val)));
        head.setLeft(desertializeImpl(data));
        head.setRight(desertializeImpl(data));


        return head;

    }

    public double myPow(double x, int n) {
        int sign;
        if (n < 0) {
            sign = 0;
        } else {
            sign = 1;
        }

        double res = 1.0;
        for (int count = 0; count < n; count++) {
            res *= x;
        }

        if (sign > 0) {
            return res;
        } else {
            return 1 / res;
        }
    }

}
