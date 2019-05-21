package com.prepcode.tree;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.*;

public class LinkedIn {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    Map<Double, List<int[]>> slopePointMap = new HashMap<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int maxPoints(int[][] points) {

        if (points.length < 3) {
            return points.length;
        }

        for (int i = 0; i < points.length - 1; i++) {
            int[] firstPoint = points[i];
            for (int k = i + 1; k < points.length; k++) {
                int[] secondPoint = points[k];

                int y = secondPoint[1] - firstPoint[1];
                int x = secondPoint[0] - firstPoint[0];

                double slope;
                if (x != 0) {
                    slope = (double) y / (double) x;
                } else {
                    slope = Double.MIN_VALUE;
                }
                List<int[]> set = slopePointMap.getOrDefault(slope, new ArrayList<>());
                if (set.size() == 0) {
                    set.add(firstPoint);
                    set.add(secondPoint);
                } else {
                    set.add(secondPoint);
                }
                slopePointMap.put(slope, set);
            }
        }

        int res = 0;
        if (slopePointMap.size() > 0) {
            Iterator<Map.Entry<Double, List<int[]>>> itr = slopePointMap.entrySet().iterator();

            while (itr.hasNext()) {
                Map.Entry entry = itr.next();
                List set = (List) entry.getValue();
                if (set.size() > res) {
                    res = set.size();
                }
            }
        }

        return res;
    }

    class WordDistance {

        String[] words;

        public WordDistance(String[] words) {
            this.words = words;
        }

        List<Integer> listWord1 = new ArrayList<>();
        List<Integer> listWOrd2 = new ArrayList<>();

        public int shortest(String word1, String word2) {
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if (word.equals(word1)) {
                    listWord1.add(i);
                } else if (word.equals(word2)) {
                    listWOrd2.add(i);
                }
            }

            int minDiff = Integer.MAX_VALUE;
            for (int k = 0; k < listWord1.size(); k++) {
                for (int j = 0; j < listWOrd2.size(); j++) {
                    minDiff = Math.min(minDiff, Math.abs(listWord1.get(k) - listWOrd2.get(j)));
                }
            }

            return minDiff;
        }
    }


    public List<List<Integer>> findLeaves(TreeNode root) {

        List<List<Integer>> finalRes = new ArrayList<>();

        while (root != null) {
            List<Integer> res = new ArrayList<>();
            root = removeLeaves(root, res);
            finalRes.add(res);
        }

        return finalRes;

    }

    private TreeNode removeLeaves(TreeNode node, List<Integer> res) {

        if (node == null) return null;
        if (node.left == null && node.right == null) {
            res.add(node.val);
            return null;
        }

        node.left = removeLeaves(node.left, res);
        node.right = removeLeaves(node.right, res);
        return node;
    }


    class MaxStack {

        Stack<Integer> stack;
        Stack<Integer> maxStack;

        public MaxStack() {
            stack = new Stack();
            maxStack = new Stack();
        }

        public void push(int x) {
            int max = maxStack.isEmpty() ? x : maxStack.peek();
            maxStack.push(max > x ? max : x);
            stack.push(x);
        }

        public int pop() {
            maxStack.pop();
            return stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int peekMax() {
            return maxStack.peek();
        }

        public int popMax() {
            int max = peekMax();
            Stack<Integer> buffer = new Stack();
            while (top() != max) {
                buffer.push(pop());
            }
            pop();
            while (!buffer.isEmpty()) {
                push(buffer.pop());
            }
            return max;
        }
    }
}
