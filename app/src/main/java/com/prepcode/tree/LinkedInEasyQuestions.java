package com.prepcode.tree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

public class LinkedInEasyQuestions {

    public boolean isValid(String s) {
        if (s.isEmpty()) return true;
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                char c1 = stack.peek();

                if (c == ')') {
                    if (c1 == '(') {
                        stack.pop();
                        continue;
                    }
                } else if (c == ']') {
                    if (c1 == '[') {
                        stack.pop();
                        continue;
                    }

                } else if (c == '}') {
                    if (c1 == '{') {
                        stack.pop();
                        continue;
                    }
                }
                stack.push(c);
            }
        }

        return stack.isEmpty();

    }


    class TwoSum {

        Map<Integer, Integer> map = new HashMap<>();

        public TwoSum() {

        }

        /**
         * Add the number to an internal data structure..
         */
        public void add(int number) {
            map.put(number, map.containsKey(number) ? map.get(number) + 1 : 1);

        }

        /**
         * Find if there exists any pair of numbers which sum is equal to the value.
         */
        public boolean find(int value) {
            Iterator<Map.Entry<Integer, Integer>> itr = map.entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry entry = itr.next();
                Integer first = (Integer) entry.getKey();

                Integer second = value - first;
                if ((first == second && map.get(first) > 1) || (first != second && map.containsKey(first) && map.containsKey(second))) {
                    return true;
                }


            }
            return false;
        }
    }

    public int[] twoSum(int[] nums, int target) throws IllegalArgumentException {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int first = nums[i];
            int second = target - first;
            if (map.containsKey(second)) {
                return new int[]{i, map.get(second)};
            } else {
                map.put(first, i);
            }
        }

        throw new IllegalArgumentException();
    }


    public boolean isSymmetric(TreeNode root) {
        return isSymmetricImpl(root, root);
    }

    private boolean isSymmetricImpl(TreeNode root1, TreeNode root2) {

        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        return root1.getVal() == root2.getVal() && isSymmetricImpl(root1.getLeft(), root2.getRight()) && isSymmetricImpl(root1.getRight(), root2.getLeft());
    }

    public ListNode reverseList(ListNode head) {
        if(head==null || head.getNext()==null) return head;

        ListNode rev = reverseList(head.getNext());

        head.getNext().setNext(head);
        head.setNext(null);

        return rev;

    }



}


