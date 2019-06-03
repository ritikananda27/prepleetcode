package com.prepcode.tree;

import java.util.*;

public class LinkedInNew {


    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int minEleIndex = 0;
        int low = 0;
        int high = nums.length - 1;
        if (nums[low] < nums[high]) {
            minEleIndex = 0;
        } else {
            while (low <= high) {
                int mid = (low + high) / 2;
                if (nums[mid] > nums[mid + 1]) {
                    minEleIndex = mid + 1;
                    break;
                }
                if (nums[mid] > nums[high]) {
                    low = mid;
                } else {
                    high = mid - 1;
                }
            }
        }

        int serachStartIndex;
        int searchEndINdex;

        if (target > nums[0]) {
            serachStartIndex = 0;
            searchEndINdex = minEleIndex;
        } else {
            serachStartIndex = minEleIndex;
            searchEndINdex = nums.length - 1;
        }

        int resIndex = -1;

        while (serachStartIndex <= searchEndINdex) {
            int miid = (searchEndINdex + searchEndINdex) / 2;
            if (nums[miid] == target) {
                resIndex = miid;
                break;
            }
            if (target > nums[miid]) {
                searchEndINdex = miid + 1;
            } else {
                searchEndINdex = miid - 1;
            }
        }


        return resIndex;
    }

    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        if (nums.length == 0) return res;
        int prod = 1;
        List<Integer> zeroIndices = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == 0) {
                zeroIndices.add(i);
                if (zeroIndices.size() > 1) {
                    break;
                }
            } else {
                prod = prod * num;
            }
        }

        if (zeroIndices.size() == 0) {
            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                res[i] = prod / num;
            }
        } else if (zeroIndices.size() == 1) {
            int index = zeroIndices.get(0);
            res[index] = prod;
        }
        return res;
    }


    // Encodes a tree to a single string.
    StringBuilder sb = new StringBuilder();

    public String serialize(TreeNode root) {
        serializeHelper(root);
        return sb.toString();
    }

    private void serializeHelper(TreeNode root) {
        if (root == null) {
            sb.append('#');
            return;
        } else {
            sb.append(root.val);
        }

        serialize(root.left);
        serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            list.add(data.charAt(i));
        }
        return deserialiZeHelper(list);
    }

    private TreeNode deserialiZeHelper(List<Character> list) {
        if (list.size() == 0) return null;
        char c = list.get(0);
        list.remove(0);
        if (c == '#') {
            return null;
        }
        int num = Character.getNumericValue(c);
        TreeNode node = new TreeNode(num);
        node.left = deserialiZeHelper(list);
        node.right = deserialiZeHelper(list);

        return node;

    }

    public int[][] merge(int[][] intervals) {

        int[][] res = new int[intervals.length][2];
        if (intervals.length == 0) return res;

        res[0] = intervals[0];
        int i = 0;

        for (int k = 1; k < intervals.length; k++) {
            if (intervals[i][1] >= intervals[k][0]) {
                intervals[i][1] = Math.max(intervals[i][i], intervals[k][1]);
            } else {
                i++;
                res[i] = intervals[k];
            }
        }

        int[][] newRes = new int[i + 1][2];
        for (int j = 0; j <= i; j++) {
            newRes[j] = res[j];
        }
        return newRes;

    }


    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, (o1, o2) -> o1.val - o2.val);
        for (int i = 0; i < lists.length; i++) {
            ListNode ln = lists[i];
            if (ln != null) {
                pq.add(ln);
            }
        }

        ListNode preHead = new ListNode(-1);
        ListNode runner = preHead;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            runner.next = node;
            runner = runner.next;
            if (node.next != null) {
                pq.add(node.next);
            }
        }

        return preHead.next;

    }


    public int[] maxSubArray(int[] nums) {
        int[] resArr = new int[2];
        if (nums.length == 0) return resArr;

        int maxOverAll = nums[0];
        int maxNow = nums[0];
        int start = 0;
        int end = 0;

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            int maxSubArrNow = num + maxNow;

            if (maxSubArrNow > num) {
                maxNow = maxSubArrNow;
            } else {
                maxNow = num;
                start = i;
            }
            if (maxNow > maxOverAll) {
                maxOverAll = maxNow;
                end = i;
            }
        }

        if (end < start) {
            resArr[0] = end;
            resArr[1] = end;
        } else {
            resArr[0] = start;
            resArr[1] = end;
        }
        return resArr;
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }


    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {

        ListNode head = null;
        ListNode tail = null;
        while (l1 != null && l2 != null) {
            ListNode next = l1.val < l2.val ? l1 : l2;
            if (head == null) {
                ListNode newNode = new ListNode(next.val);
                head = newNode;
                tail = newNode;
            } else {
                tail.next = new ListNode(next.val);
                tail = tail.next;
            }

            if (l1.val < l2.val) {
                l1 = l1.next;
            } else {
                l2 = l2.next;
            }
        }

        if (l1 != null) {
            if (tail == null) {
                head = l1;
            } else {
                tail.next = l1;
            }
        }
        if (l2 != null) {
            if (tail == null) {
                head = l2;
            } else {
                tail.next = l2;
            }
        }
        return head;

    }

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if (nums.length == 0) {
            res[0] = -1;
            res[1] = -1;
        } else {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int first = nums[i];
                int second = target - first;

                if (map.containsKey(second)) {
                    res[0] = map.get(second);
                    res[1] = i;
                    break;
                } else {
                    map.put(first, i);
                }
            }
        }

        return res;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Tree {
        public TreeNode getBST() {
            TreeNode n4 = new TreeNode(4);
            TreeNode n5 = new TreeNode(5);
            TreeNode n1 = new TreeNode(1);
            TreeNode n3 = new TreeNode(3);
            TreeNode n6 = new TreeNode(6);

            n1.left = n3;
            n1.right = n4;
            n4.left = n5;
            n4.right = n6;


            return n1;
        }
    }
}
