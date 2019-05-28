package com.prepcode.tree;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.*;
import java.util.LinkedList;

public class LinkedIn {


    public int[] searchRange(int[] nums, int target) {

        if (nums.length == 0) return new int[]{-1, -1};
        if (target < nums[0] || target > nums[nums.length - 1]) return new int[]{-1, -1};

        int low = 0;
        int high = nums.length - 1;

        boolean found = false;

        while (low <= high) {

            int mid = (low + high) / 2;
            int midNum = nums[mid];

            if (midNum == target) {
                found = true;
                low = mid;
                while (low > 0 && nums[low - 1] == target) {
                    low--;
                }
                high = mid;
                while (high < nums.length-1 && nums[high + 1] == target) {
                    high++;
                }

                break;

            } else if (midNum < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }

        }

        if (found) {
            return new int[]{low, high};
        } else {
            return new int[]{-1, -1};
        }

    }

    public int countSubstrings(String s) {

        if (s.isEmpty()) return 0;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            countSubStringHelper(s, i, i + 1, res);
        }

        return res.size();

    }

    private boolean isPalindrome(String st) {
        if (st.length() == 1) return true;
        int i = 0;
        int j = st.length() - 1;

        while (i < j) {
            if (st.charAt(i) != st.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    private void countSubStringHelper(String s, int start, int end, List<String> res) {
        if (end > s.length()) return;
        String ss = s.substring(start, end);
        if (isPalindrome(ss)) {
            res.add(ss);
        }
        countSubStringHelper(s, start, end + 1, res);

    }

    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;

        int ans = nums[0];
        int minSoFar = nums[0];
        int maxSoFar = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            int curMin = Math.min(Math.min((minSoFar * num), (maxSoFar * num)), num);
            int curMax = Math.max(Math.max((maxSoFar * num), (minSoFar * num)), num);
            ans = Math.max(ans, curMax);
            minSoFar = curMin;
            maxSoFar = curMax;
        }

        return ans;

    }

    public int rob(int[] nums) {
        int PM = 0;
        int CM = 0;

        for (int x : nums) {
            int temp = CM;
            CM = Math.max((PM + x), CM);
            PM = temp;
        }

        return CM;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> levelNodes = new ArrayList<>();
        queue.offer(root);
        queue.offer(null);

        while (!queue.isEmpty()) {

            TreeNode tn = queue.poll();
            if (tn == null) {
                res.add(new ArrayList<>(levelNodes));
                if (queue.size() > 0) {
                    queue.offer(null);
                    levelNodes = new ArrayList<>();
                }
            } else {
                levelNodes.add(tn.val);
                if (tn.left != null) {
                    queue.offer(tn.left);
                }
                if (tn.right != null) {
                    queue.offer(tn.right);
                }
            }

        }

        return res;
    }

    public int evalRPN(String[] tokens) {
        if (tokens.length == 0) return 0;

        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < tokens.length; i++) {
            String s = tokens[i];
            if (isNumeric(s)) {
                numbers.add(Integer.parseInt(s));
            } else {

                int last = numbers.get(numbers.size() - 1);
                int second = numbers.get(numbers.size() - 2);
                numbers.remove(numbers.size() - 1);
                numbers.remove(numbers.size() - 1);

                if (s.equals("+")) {
                    numbers.add(last + second);
                } else if (s.equals("-")) {
                    numbers.add(second - last);
                } else if (s.equals("/")) {
                    if (last != 0) {
                        int res = second / last;
                        numbers.add(res);
                    }
                } else if (s.equals("*")) {
                    numbers.add(last * second);
                }
            }
        }

        if (numbers.size() == 1) {
            return numbers.get(0);
        } else {
            return -1;
        }

    }


    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> res = new HashSet<>();
        if (s.isEmpty()) return new ArrayList<>();

        Set<String> set = new HashSet<>();
        for (int i = 0; i < s.length() - 9; i++) {
            String sequence = s.substring(i, i + 10);
            if (!set.add(sequence)) {
                res.add(sequence);
            }
        }

        return new ArrayList<>(res);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public int shortestDistance(String[] words, String word1, String word2) {

        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            List<Integer> list = map.getOrDefault(words[i], new ArrayList<>());
            list.add(i);
            map.put(words[i], list);
        }

        if (!map.containsKey(word1) || !map.containsKey(word2)) return -1;
        if (word1.equals(word2)) return 0;

        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);

        int p1 = 0;
        int p2 = 0;

        int minDistance = Integer.MAX_VALUE;
        while (p1 < list1.size() && p2 < list2.size()) {
            int index1 = list1.get(p1);
            int index2 = list2.get(p2);

            int diff = Math.abs(index1 - index2);

            if (diff < minDistance) {
                minDistance = diff;
            }
            if (index1 > index2) {
                p2++;
            } else {
                p1++;
            }
        }

        return minDistance;
    }


    public boolean isIsomorphic(String s, String t) {
        Map m1 = new HashMap();
        Map m2 = new HashMap();

        for (Integer i = 0; i < s.length(); ++i)
            if (m1.put(s.charAt(i), i) != m2.put(t.charAt(i), i))
                return false;
        return true;
    }


    public int minCost(int[][] costs) {

        if (costs.length == 0) return 0;

        for (int i = 1; i < costs.length; i++) {
            costs[i][0] = costs[i][0] + Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] = costs[i][1] + Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] = costs[i][2] + Math.min(costs[i - 1][1], costs[i - 1][0]);
        }

        int n = costs.length - 1;
        return Math.min(Math.min(costs[n][0], costs[n][1]), costs[n][2]);
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        if (nums.length == 0) return new ArrayList<>();
        permuteUniqueHelper(res, new ArrayList<>(), nums, new HashSet<>());
        return new ArrayList<>(res);
    }


    private void permuteUniqueHelper(Set<List<Integer>> res, List<Integer> temp, int[] nums, Set<Integer> visited) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited.add(i)) {
                temp.add(nums[i]);
                permuteUniqueHelper(res, temp, nums, visited);
                temp.remove(temp.size() - 1);
                visited.remove(i);
            }

        }

    }

    public int countPalindromicSubsequences(String S) {
        Set<String> res = new HashSet<>();
        palindromicSequenceHelper(S, new StringBuilder(), res, 0);
        return res.size();

    }

    private void palindromicSequenceHelper(String s, StringBuilder sb, Set<String> res, int start) {

        if (sb.length() > 0 && isPalindrome(sb.toString())) {
            res.add(sb.toString());
        }
        for (int i = start; i < s.length(); i++) {
            sb.append(s.charAt(i));
            palindromicSequenceHelper(s, sb, res, i + 1);
            sb.deleteCharAt(sb.length() - 1);
        }


    }


    public boolean canPlaceFlowers(int[] flowerbed, int n) {

        int x = n;
        int i = 0;
        while (x > 0 && i < flowerbed.length) {
            int bed = flowerbed[i];
            if (bed == 0) {
                boolean left = i == 0 || ((i - 1) > 0 && flowerbed[i - 1] == 0);
                if (left) {
                    boolean right = (i == flowerbed.length - 1 || (i + 1) < flowerbed.length && flowerbed[i + 1] == 0);
                    if (right) {
                        flowerbed[i] = 1;
                        x--;
                    }
                }
            }
            i++;
        }

        return x == 0;

    }


    public int findSecondMinimumValue(TreeNode root) {

        if (root == null) return -1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int min1 = root.val;
        int min2 = Integer.MAX_VALUE;
        boolean met = false;

        while (!queue.isEmpty()) {
            TreeNode tn = queue.poll();
            if (tn.val > min1) {
                met = true;
                if (tn.val < min2) {
                    min2 = tn.val;
                }
            }
            if (tn.left != null && tn.right != null) {
                queue.offer(tn.left);
                queue.offer(tn.right);
            }
        }

        if (min2 < Integer.MAX_VALUE || met) {
            return min2;
        } else {
            return -1;
        }
    }

    public TreeNode upsideDownBinaryTree(TreeNode root) {

        if (root == null || root.left == null) return root;

        TreeNode tn = upsideDownBinaryTree(root.left);

        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;

        return tn;

    }

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        if (root == null) return resList;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);

        int min = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            TreeNode res = queue.poll();
            if (res == null) {
                resList.add(min);
                if (queue.size() > 0) {
                    min = Integer.MIN_VALUE;
                    queue.offer(null);
                }
            } else {
                if (res.val > min) {
                    min = res.val;
                }
                if (res.left != null) {
                    queue.offer(res.left);
                }
                if (res.right != null) {
                    queue.offer(res.right);
                }

            }

        }

        return resList;
    }


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

    public int depthSumInverse(List<Object> nestedList) {
        int sum = 0;
        Integer level = 1;

        if (nestedList.size() == 0) return sum;
        return calculateDepthSum(nestedList, level);
    }

    private int calculateDepthSum(List<Object> list, Integer level) {
        int sum = 0;

        for (int i = 0; i < list.size(); i++) {
            Object object = list.get(i);
            if (object instanceof Integer) {
                sum = sum + level * (Integer) object;
            } else {
                sum = sum + calculateDepthSum((List) object, level + 1);
            }
        }

        return sum;

    }

    public boolean isPerfectSquare(int num) {

        int low = 1;
        int high = num;

        while (low <= high) {
            int mid = (low + high) / 2;

            int test = mid * mid;
            if (test == num) {
                return true;
            } else if (test < num) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return false;

    }


    public boolean judgeSquareSum(int c) {

        int low = 0;
        int high = (int) Math.sqrt(c);

        while (low < high) {
            int test = (low * low) + (high * high);
            if (test == c) {
                return true;
            } else if (test < c) {
                low = low + 1;
            } else {
                high = high - 1;
            }
        }


        return false;

    }
}
