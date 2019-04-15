package com.prepcode.tree.airbnb;

import java.util.*;

public class Airbnb {

    public String countAndSay(int n) {

        return countAndSayHelper(n, 1, "1");
    }


    private String countAndSayHelper(int n, int k, String prev) {

        if (k == n) {
            return prev;
        }

        String next = generateStringFromPrevious(prev);
        return countAndSayHelper(n, k + 1, next);
    }

    private String generateStringFromPrevious(String prev) {
        char curr = prev.charAt(0);
        int count = 1;
        char newChar = '1';
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < prev.length(); i++) {
            newChar = prev.charAt(i);
            if (curr == newChar) {
                count++;
            } else {
                sb.append(count);
                sb.append(curr);
                count = 1;
                curr = newChar;
            }

        }

        sb.append(count);
        sb.append(newChar);
        return sb.toString();
    }


    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> res = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            String s = strs[i];
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = String.valueOf(arr);

            List<String> anagrams;
            if (res.containsKey(key)) {
                anagrams = res.get(key);
                anagrams.add(s);
            } else {
                anagrams = new ArrayList<>();
                anagrams.add(s);
            }

            res.put(key, anagrams);

        }

        Iterator<Map.Entry<String, List<String>>> itr = res.entrySet().iterator();
        List<List<String>> resList = new ArrayList<>();

        while (itr.hasNext()) {

            Map.Entry entry = itr.next();
            resList.add((List<String>) entry.getValue());
        }


        return resList;

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) return null;
        if (root.val == p.val || root.val == q.val) {
            return root;
        }

        TreeNode leftPresence = lowestCommonAncestor(root.left, p, q);
        TreeNode rightPresence = lowestCommonAncestor(root.right, p, q);

        if (leftPresence != null && rightPresence != null) {
            return root;
        }

        return (leftPresence != null) ? leftPresence : rightPresence;

    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int x) {
            val = x;
        }


    }

    public class Tree {


        public TreeNode getBasicTree1() {

            TreeNode n10 = new TreeNode(10);

            TreeNode n6 = new TreeNode(6);
            TreeNode n2 = new TreeNode(2);
            TreeNode n9 = new TreeNode(9);

            TreeNode n15 = new TreeNode(15);

            TreeNode n12 = new TreeNode(12);
            TreeNode n20 = new TreeNode(20);
            TreeNode n11 = new TreeNode(11);
            TreeNode n19 = new TreeNode(19);
            TreeNode n25 = new TreeNode(25);


            n10.left = n6;
            n6.left = n2;
            n6.right = n9;
            n10.right = n15;
            n15.left = n12;
            n12.left = n11;
            n15.right = n20;
            n20.left = n19;
            n20.right = n25;

            return n10;
        }
    }
}

