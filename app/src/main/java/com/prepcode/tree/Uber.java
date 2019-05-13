package com.prepcode.tree;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.*;
import java.util.LinkedList;

public class Uber {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }


    }

    public class Tree {
        public TreeNode getBasicTree() {
            TreeNode n3 = new TreeNode(3);
            TreeNode n9 = new TreeNode(9);
            TreeNode n20 = new TreeNode(20);
            TreeNode n15 = new TreeNode(15);
            TreeNode n7 = new TreeNode(7);

            n3.left = n9;
            n3.right = n20;
            n20.left = n15;
            n20.right = n7;

            return n3;
        }
    }

    public int trap(int[] height) {

        int finalWaterAmount = 0;
        if (height.length == 0) return 0;

        for (int i = 0; i < height.length; i++) {

            int leftMax = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > leftMax) {
                    leftMax = height[j];
                }
            }

            int rightMax = 0;
            for (int k = i + 1; k < height.length; k++) {
                if (height[k] > rightMax) {
                    rightMax = height[k];
                }
            }

            int maxHeightUseful = Math.min(leftMax, rightMax);
            maxHeightUseful = maxHeightUseful - height[i];
            if (maxHeightUseful > 0) {
                finalWaterAmount = finalWaterAmount + maxHeightUseful;
            }

        }

        return finalWaterAmount;
    }

    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {

            int first = nums[i];
            int second = target - first;
            if (map.containsKey(second)) {
                ans[0] = map.get(second);
                ans[1] = i;
                break;
            } else {
                map.put(first, i);
            }
        }

        return ans;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null && l2 == null) {
            return null;
        }

        int carry = 0;
        ListNode resNode = new ListNode(0);
        ListNode curRes = resNode;

        while (l1 != null || l2 != null) {
            int x = l1 != null ? l1.getValue() : 0;
            int y = l2 != null ? l2.getValue() : 0;

            int sum = x + y + carry;

            carry = sum / 10;

            int nodeVal = sum % 10;

            curRes.setNext(new ListNode(nodeVal));
            curRes = curRes.getNext();

            if (l1 != null) {
                l1 = l1.getNext();
            }
            if (l2 != null) {
                l2 = l2.getNext();
            }
        }

        if (carry != 0) {
            curRes.setNext(new ListNode(carry));
        }

        return resNode.getNext();
    }


    class LRUCache {

        Node head = null;
        Node tail = null;
        int capacity;
        Map<Integer, Node> map;


        class Node {

            int value;
            Node previous = null;
            Node next = null;

            Node(int val) {
                this.value = val;
            }
        }

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            Node node = map.get(key);
            removeNode(node);
            setTail(node);
            return node.value;

        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void put(int key, int value) {
            Node newNode = new Node(value);
            if (map.containsKey(key)) {
                removeNode(newNode);
            } else {
                if (map.size() == capacity) {
                    map.remove(head.value);
                    removeNode(head);

                }
            }
            setTail(newNode);
            map.put(key, newNode);
        }


        private void setTail(Node n) {
            if (tail == null) {
                tail = n;
            } else {
                n.next = tail;
                tail.previous = n;
                tail = n;
                tail.previous = null;
            }

            if (head == null) {
                head = tail;
            }
        }

        private void removeNode(Node n) {
            if (n.next != null) {
                n.next.previous = n.previous;
            } else {
                head = n.previous;
            }

            if (n.previous != null) {
                n.previous.next = n.next;
            } else {
                tail = n.next;
            }
        }


    }

    public int maxSubArray(int[] nums) {

        if (nums.length == 0) return -1;

        int maxTillNow = nums[0];
        int maxNow = nums[0];

        for (int i = 1; i < nums.length; i++) {

            maxNow = Math.max(nums[i], maxNow + nums[i]);
            maxTillNow = Math.max(maxNow, maxTillNow);
        }

        return maxTillNow;
    }

    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {
        }

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    ;

    Map<Node, Node> nodeMap = new HashMap<>();

    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }

        if (nodeMap.containsKey(head)) {
            return nodeMap.get(head);
        }

        Node newNode = new Node(head.val, null, null);
        nodeMap.put(newNode, newNode);

        newNode.next = copyRandomList(head.next);
        newNode.random = copyRandomList(head.random);

        return newNode;
    }

    public int reverse(int x) {

        int sign = x < 0 ? -1 : 1;
        x = Math.abs(x);

        int rev = 0;

        while (x > 0) {
            int y = x % 10;
            x = x / 10;

            if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) {
                rev = 0;
                break;
            }
            rev = rev * 10 + y;
        }
        return sign * rev;


    }

    public int wordsTyping(String[] sentence, int rows, int cols) {

        int rowIndex = 0;
        int wordIndex = 0;

        int wordsAccomodated = 0;

        while (rowIndex < rows) {
            int colIndex = 0;
            while (colIndex < cols) {

                int availableSpace = cols - colIndex;
                String nextWord;
                if (wordIndex >= sentence.length) {
                    wordIndex = 0;
                }
                nextWord = sentence[wordIndex];
                if (availableSpace >= nextWord.length()) {
                    colIndex = colIndex + nextWord.length() + 1;
                    wordIndex++;
                    wordsAccomodated++;
                } else {
                    break;
                }
            }
            rowIndex++;
        }

        return wordsAccomodated / sentence.length;

    }


    class MinStack {

        Node node = null;

        class Node {
            int val;
            int min;
            Node next;

            Node(int val, int min, Node next) {
                this.val = val;
                this.min = min;
                this.next = next;
            }
        }


        public void push(int x) {

            if (node == null) {
                node = new Node(x, x, null);
            } else {
                Node nn = new Node(x, Math.min(x, node.min), node);
                node = nn;
            }
        }

        public void pop() {
            if (node != null) {
                node = node.next;
            }
        }

        public int top() {
            if (node != null) {
                return node.val;
            } else {
                return -1;
            }
        }

        public int getMin() {
            return node.min;
        }
    }


    public String reverseWords(String s) {

        if (s.isEmpty()) return s;
        Stack<String> stack = new Stack<>();

        String[] arr = s.split(" ");

        for (int i = 0; i < arr.length; i++) {
            String str = arr[i];
            if (!str.isEmpty()) {
                stack.push(arr[i].trim());
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
            sb.append(" ");
        }

        return sb.toString();
    }


    // variables needed
    int numTicketsUsed = 0;
    int numTotalTickets = 0;
    Map<String, List<String>> ticketsMap = new HashMap<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<String> findItinerary(List<List<String>> tickets) {

        if (tickets.isEmpty()) return new ArrayList<>();

        numTotalTickets = tickets.size();
        for (int i = 0; i < tickets.size(); i++) {
            List<String> destinations = ticketsMap.getOrDefault(tickets.get(i).get(0), new ArrayList<>());
            destinations.add(tickets.get(i).get(1));
            ticketsMap.put(tickets.get(i).get(0), destinations);
        }

        Iterator<Map.Entry<String, List<String>>> itr = ticketsMap.entrySet().iterator();
        while (itr.hasNext()) {
            Collections.sort(itr.next().getValue());
        }

        List<String> result = new ArrayList<>();
        result.add("JFK");

        dfsRoute("JFK", result);

        return result;

    }

    private void dfsRoute(String start, List<String> result) {

        if (!ticketsMap.containsKey(start) || ticketsMap.get(start).size() == 0) return;

        List<String> dests = ticketsMap.get(start);

        for (int i = 0; i < dests.size(); i++) {
            String dest = dests.get(i);
            numTicketsUsed++;
            result.add(dest);
            dests.remove(dest);
            dfsRoute(dest, result);

            if (numTicketsUsed == numTotalTickets) {
                return;
            }

            numTicketsUsed--;
            dests.add(i, dest);
            result.remove(result.size() - 1);
        }


    }


    public boolean isValidBST(TreeNode root) {

        return binaryTreeHelper(root, null, null);
    }


    private boolean binaryTreeHelper(TreeNode root, Integer maxValue, Integer minValue) {

        if (root == null) return true;
        int val = root.val;

        if ((minValue != null && val <= minValue) || (maxValue != null && val >= maxValue)) return false;
        return binaryTreeHelper(root.left, root.val, minValue) && binaryTreeHelper(root.right, maxValue, root.val);

    }


    List<List<Integer>> resList = new ArrayList<>();

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        zigzagListHelper(new ArrayList<>(), queue);
        return resList;
    }

    private void zigzagListHelper(List<Integer> temp, Queue<TreeNode> queue) {

        int level = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                resList.add(new ArrayList<>(temp));
                temp = new ArrayList<>();
                level++;
                if (queue.size() > 0) {
                    queue.add(null);
                }
            } else {
                if (level % 2 == 0) {
                    temp.add(node.val);
                } else {
                    temp.add(0, node.val);
                }

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Integer> topKFrequent(int[] nums, int k) {

        if (nums.length == 0) return null;

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(k, (a, b) -> b.getValue() - a.getValue());
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int val = map.getOrDefault(nums[i], 0);
            val = val + 1;
            map.put(nums[i], val);
        }

        Iterator itr = map.entrySet().iterator();

        while (itr.hasNext()) {
            Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) itr.next();
            pq.add(entry);
        }

        List<Integer> res = new ArrayList<>();

        for (int n = 0; n < k; n++) {
            Map.Entry<Integer, Integer> entry = pq.poll();
            res.add(entry.getKey());
        }

        return res;

    }


    // Permutation

    List<List<Integer>> permuteHelper = new ArrayList<>();


    public List<List<Integer>> permute(int[] nums) {

        if (nums.length > 0) {
            permuteHelper(nums, permuteHelper, new ArrayList<>());
        }

        return permuteHelper;
    }

    private void permuteHelper(int[] nums, List<List<Integer>> res, List<Integer> temp) {

        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
        }
        for (int i = 0; i < nums.length; i++) {
            if (!temp.contains(nums[i])) {
                temp.add(nums[i]);
                permuteHelper(nums, res, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    //Combinations

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }


    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();

        if (matrix.length == 0) {
            return res;
        }

        int colBegin = 0;
        int colEnd = matrix[0].length - 1;
        int rowBegin = 0;
        int rowEnd = matrix.length - 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {

            //Traverse right
            for (int i = colBegin; i <= colEnd; i++) {
                res.add(matrix[rowBegin][i]);
            }
            rowBegin++;

            //Traverse down
            for (int i = rowBegin; i <= rowEnd; i++) {
                res.add(matrix[i][colEnd]);
            }
            colEnd--;

            if (rowBegin <= rowEnd) {
                for (int j = colEnd; j >= 0; j--) {
                    res.add(matrix[rowEnd][j]);
                }
            }

            rowEnd--;

            if (colBegin <= colEnd) {
                for (int i = 0; i <= colEnd; i++) {
                    res.add(matrix[i][colBegin]);
                }
            }

            colBegin++;

        }

        return res;
    }

}
