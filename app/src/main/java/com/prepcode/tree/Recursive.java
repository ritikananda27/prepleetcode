package com.prepcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Recursive {

    public ListNode swapPairs(ListNode head) {
        ListNode resNode = null;
        return swap(head, resNode);

    }

    private ListNode swap(ListNode head, ListNode previousLast) {

        if (head == null || head.getNext() == null) {
            return head;
        }
        ListNode newNext = head.getNext();
        head.setNext(head.getNext().getNext());
        newNext.setNext(head);

        if (previousLast != null) {
            previousLast.setNext(newNext);
        }
        swap(head.getNext(), head);
        return newNext;

    }


    public List<List<Integer>> generate(int numRows) {
        List<Integer[]> res = new ArrayList<>();

        for (int i = 1; i <= numRows; i++) {
            Integer[] subRes = new Integer[i];
            res.add(subRes);
            for (int j = 0; j < subRes.length; j++) {
                subRes[j] = populateMatrix(i - 1, j, res);
            }
        }


        List<List<Integer>> finalRes = new ArrayList<>();

        for (int i = 0; i < res.size(); i++) {
            finalRes.add(Arrays.asList(res.get(i)));
        }
        return finalRes;
    }

    private Integer populateMatrix(int i, int j, List<Integer[]> res) {

        if (j == 0 || i == j) {
            res.get(i)[j] = 1;
            return 1;
        } else {
            return res.get(i - 1)[j - 1] + res.get(i - 1)[j];
        }

    }

    public List<Integer> getRow(int rowIndex) {

        List<Integer> previous = new ArrayList<>(1);
        previous.add(1);

        for (int i = 2; i <= rowIndex; i++) {
            previous = populateCurrentRow(previous, new Integer[i]);
        }

        return previous;
    }

    private List<Integer> populateCurrentRow(List<Integer> previous, Integer[] arr) {
        arr[0] = 1;
        arr[arr.length - 1] = 1;

        for (int j = 1; j < arr.length - 1; j++) {
            arr[j] = previous.get(j - 1) + previous.get(j);
        }
        return Arrays.asList(arr);
    }


    public ListNode reverseList(ListNode head) {
        if (head == null || head.getNext() == null) {
            return head;
        }

        ListNode reverse = reverseList(head.getNext());

        head.getNext().setNext(head);
        head.setNext(null);

        return reverse;

    }

    public int fib(int N) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        return returnFib(N, map);


    }

    private int returnFib(int N, HashMap<Integer, Integer> map) {

        if (map.containsKey(N)) {
            return map.get(N);
        }

        int res = returnFib(N - 1, map) + returnFib(N - 2, map);
        map.put(N, res);
        return res;

    }

    public int climbStairs(int n) {
        return climbStairsHelper(0, n);

    }

    private int climbStairsHelper(int curr, int n) {
        if (curr == n) return 1;
        if (curr > n) return 0;

        return climbStairsHelper(curr + 1, n) + climbStairsHelper(curr + 2, n);
    }


    public int maxDepth(TreeNode root) {

        return maxDepthCal(root);
    }

    private int maxDepthCal(TreeNode root) {
        if (root == null) {
            return 0;
        } else {

            int leftHeight = maxDepthCal(root.getLeft());
            int rightHeight = maxDepthCal(root.getRight());

            return Math.max(leftHeight, rightHeight) + 1;

        }
    }

    public double myPow(double x, int n) {
        boolean sign = false;
        if (n < 0) {
            sign = true;
        }

        n = Math.abs(n);
        double val = myPowHelper(x, n / 2, 1);

        if (n % 2 == 0) {
            val = val * val;
        } else {
            val = val * val * x;
        }
        if (sign) {
            return 1 / val;
        } else {
            return val;
        }
    }

    private double myPowHelper(double x, int n, int count) {
        if (n == 0 || count > n) {
            return 1;
        }

        double half = x * myPowHelper(x, n, count + 1);

        return half;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.getValue() < l2.getValue()) {
            l1.setNext(mergeTwoLists(l1.getNext(), l2));
            return l1;
        } else {
            l2.setNext(mergeTwoLists(l2.getNext(), l1));
            return l2;
        }

    }


    public int kthGrammar(int N, int K) {
        String finalStr = kthGrammerHelper(2, N, "0");
        return Character.getNumericValue(finalStr.charAt(K - 1));


    }

    private String kthGrammerHelper(int count, int N, String res) {
        if (count > N) {
            return res;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length(); i++) {
            char c = res.charAt(i);
            if (c == '0') {
                sb.append("01");
            } else {
                sb.append("10");
            }
        }

        res = kthGrammerHelper(count + 1, N, sb.toString());
        return res;

    }

    public TreeNode generate_trees(int start, int end, List<TreeNode> all_trees) {


        if (start > end) {
            return null;
        }

        // pick up a root
        TreeNode current_tree = null;
        for (int i = start; i <= end; i++) {
            // all possible left subtrees if i is choosen to be a root
            TreeNode left_tree = generate_trees(start, i - 1, all_trees);

            // all possible right subtrees if i is choosen to be a root
            TreeNode right_tree = generate_trees(i + 1, end, all_trees);

            // connect left and right trees to the root i

            current_tree = new TreeNode(i);
            current_tree.setLeft(left_tree);
            current_tree.setRight(right_tree);
            all_trees.add(current_tree);
        }
        return current_tree;
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        List<TreeNode> res = new ArrayList<>();
        generate_trees(1, n, res);
        return res;
    }



}
