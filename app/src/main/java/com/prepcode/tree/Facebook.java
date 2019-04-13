package com.prepcode.tree;

import java.util.ArrayList;
import java.util.List;

public class Facebook {


    // ********************************* Easy ******************************************************

    public boolean isPalindrome2(ListNode head) {

        String s = generateString(head, "");

        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;

    }

    private String generateString(ListNode head, String res) {

        if (head == null) return res;

        String r = generateString(head.getNext(), res);
        r = r + head.getValue();
        return r;

    }

    public boolean isPalindrome(ListNode head) {

        if (head == null || head.getNext() == null) return true;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        ListNode reverse = reverseLinkedList(slow);

        while (reverse != null) {
            if (head.getValue() != reverse.getValue()) {
                return false;
            }
            head = head.getNext();
            reverse = reverse.getNext();
        }

        return true;
    }

    ListNode reverseLinkedList(ListNode head) {

        if (head == null || head.getNext() == null) {
            return head;
        }

        ListNode rev = reverseLinkedList(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return rev;

    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int n1P = 0;
        int n2P = 0;

        while (n1P < nums1.length && n2P < nums2.length) {
            if (n1P >= m && nums1[n1P] == 0) {
                nums1[n1P] = nums2[n2P];
                n2P++;
            } else if (nums1[n1P] > nums2[n2P]) {
                createSpaceinNums1AtPosition(n1P, nums1, m);
                nums1[n1P] = nums2[n2P];
                m++;
                n2P++;
            } else if (nums1[n1P] == nums2[n2P]) {
                createSpaceinNums1AtPosition(n1P + 1, nums1, m);
                nums1[n1P + 1] = nums2[n2P];
                m++;
                n2P++;
            }

            n1P++;

        }

    }

    private void createSpaceinNums1AtPosition(int position, int[] nums1, int m) {
        while (m > position) {
            nums1[m] = nums1[m - 1];
            m--;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (p.getVal() < q.getVal()) {
            return lowestCommonAncestorImpl(root, p, q);
        } else {
            return lowestCommonAncestorImpl(root, q, p);
        }

    }

    public int strStr(String haystack, String needle) {
        if (needle.length() > haystack.length()) return -1;
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                int end = Math.min(needle.length(), haystack.length() - i);
                String sub = haystack.substring(i, i + end);
                if (sub.equals(needle)) {
                    return i;
                }
            }
        }

        return -1;
    }

    private TreeNode lowestCommonAncestorImpl(TreeNode root, TreeNode small, TreeNode large) {

        if (small.getVal() <= root.getVal() && root.getVal() <= large.getVal()) {
            return root;
        }

        if (large.getVal() < root.getVal()) {
            return lowestCommonAncestorImpl(root.getLeft(), small, large);
        } else {
            return lowestCommonAncestorImpl(root.getRight(), small, large);
        }


    }


    // ************************************ Medium *************************************************

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();
        subsetsImpl(nums, resList, new ArrayList(), 0);
        return resList;
    }

    private void subsetsImpl(int[] nums, List<List<Integer>> resList, List<Integer> child, int i) {
        resList.add(new ArrayList<>(child));
        for (int j = i; j < nums.length; j++) {
            child.add(nums[j]);
            subsetsImpl(nums, resList, child, j + 1);
            child.remove(child.size() - 1);
        }

    }


    public int countSubstrings(String s) {
        List<String> resList = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            palidromicBacktrack(s, i, i, resList, new StringBuilder());
        }
        return resList.size();

    }

    private void palidromicBacktrack(String s, int start, int end, List<String> resList, StringBuilder sb) {
        if (end == s.length()) {
            return;
        }
        sb.append(s.charAt(end));
        palidromicBacktrack(s, start, end + 1, resList, sb);

        if (isPalindrome(s, start, end)) {
            resList.add(sb.toString());
        }

        sb.deleteCharAt(sb.length() - 1);
    }

    private boolean isPalindrome(String s, int start, int end) {

        if (start == end) return true;

        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;


    }


}
