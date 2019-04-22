package com.prepcode.tree.facebook;

import android.os.Build;
import android.support.annotation.RequiresApi;
import com.prepcode.tree.ListNode;

import java.util.*;

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

        if (p.val < q.val) {
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

        if (small.val <= root.val && root.val <= large.val) {
            return root;
        }

        if (large.val < root.val) {
            return lowestCommonAncestorImpl(root.left, small, large);
        } else {
            return lowestCommonAncestorImpl(root.right, small, large);
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


    public String multiply(String num1, String num2) {

        if (num1.equals("0") || num2.equals("0")) return "0";
        int s2 = num2.length() - 1;
        List<String> resList = new ArrayList<>();

        while (s2 >= 0) {
            char c = num2.charAt(s2);
            int i = Character.getNumericValue(c);

            int res = i * Integer.parseInt(num1);

            StringBuilder sb = new StringBuilder();
            for (int j = s2; j < num2.length() - 1; j++) {
                sb.append("0");

            }
            resList.add(res + sb.toString());
            s2--;
        }

        int res = 0;

        for (int i = 0; i < resList.size(); i++) {
            int a = Integer.parseInt(resList.get(i));
            res = res + a;
        }

        return Integer.toString(res);
    }

    public int numIslands(char[][] grid) {

        int count = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                char c = grid[row][col];
                if (c == '1') {
                    count++;
                    determinIslandSize(grid, row, col);
                }
            }
        }


        return count;

    }

    private void determinLargestIslandSize(char[][] grid, int row, int col) {

        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] == '0') {
            return;
        }

        grid[row][col] = '0';

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i != row || j != col) {
                    determinLargestIslandSize(grid, i, j);
                }
            }
        }

    }


    private void determinIslandSize(char[][] grid, int row, int col) {

        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] == '0') {
            return;
        }

        grid[row][col] = '0';

        determinIslandSize(grid, row - 1, col);
        determinIslandSize(grid, row + 1, col);
        determinIslandSize(grid, row, col - 1);
        determinIslandSize(grid, row, col + 1);


    }


    public List<List<Integer>> threeSum(int[] nums) {

        if (nums.length < 3) return Collections.emptyList();
        Set<List<Integer>> res = new HashSet<>();

        int sum = 0;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {

            int remain = sum - nums[i];
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int ss = nums[j] + nums[k];
                if (ss == remain) {
                    List<Integer> r = new ArrayList<>();
                    r.add(nums[i]);
                    r.add(nums[j]);
                    r.add(nums[k]);
                    res.add(r);
                    j++;
                    k--;
                } else if (ss > remain) {
                    k--;
                } else {
                    j++;
                }

            }

        }

        return new ArrayList<>(res);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() < 2) return intervals;
        intervals.sort((o1, o2) -> o1.start - o2.start);
        return mergeImpl(new ArrayList<>(intervals));

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<Interval> mergeImpl(ArrayList<Interval> intervals) {


        List<Interval> mergedList = new LinkedList<>();
        for (int i = 0; i < intervals.size(); i++) {
            Interval inter = intervals.get(i);
            if (mergedList.isEmpty()) {
                mergedList.add(inter);
            } else {
                Interval lastMerged = ((LinkedList<Interval>) mergedList).getLast();
                if (lastMerged.end >= inter.start) {
                    if (lastMerged.end < inter.end) {
                        Interval newMerged = new Interval(lastMerged.start, inter.end);
                        ((LinkedList<Interval>) mergedList).removeLast();
                        mergedList.add(newMerged);
                    }

                } else {
                    mergedList.add(inter);
                }
            }
        }
        return mergedList;
    }


    public int[][] kClosest(int[][] points, int K) {

        TreeMap<Integer, List<int[]>> pointDistanceMap = new TreeMap<>();

        for (int i = 0; i < points.length; i++) {
            int[] point = points[i];
            Integer dist = (point[0] * point[0]) + (point[1] * point[1]);

            if (pointDistanceMap.containsKey(dist)) {
                pointDistanceMap.get(dist).add(point);
            } else {
                List<int[]> pointList = new ArrayList<>();
                pointList.add(point);
                pointDistanceMap.put(dist, pointList);
            }
        }

        int[][] answer = new int[K][2];


        int count = 0;

        Iterator itr = pointDistanceMap.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            List<int[]> pList = (List) entry.getValue();
            if (count < K) {
                for (int i = 0; i < pList.size(); i++) {
                    if (count < K) {
                        answer[count] = pList.get(i);
                        count++;
                    } else {
                        break;
                    }
                }
            } else {
                break;
            }
        }


        return answer;


    }

    public int minMeetingRooms(Interval[] intervals) {

        if (intervals.length < 2) return intervals.length;
        Arrays.sort(intervals, (o1, o2) -> o1.start - o2.start);

        List<java.util.LinkedList> totalRooms = new ArrayList<>();
        java.util.LinkedList<Interval> room1 = new java.util.LinkedList();
        room1.add(intervals[0]);
        totalRooms.add(room1);

        for (int i = 1; i < intervals.length; i++) {
            Interval newMeeting = intervals[i];
            boolean roomFound = false;
            for (java.util.LinkedList<Interval> room : totalRooms) {
                Interval lastMeeting = room.getLast();
                if (newMeeting.start >= lastMeeting.end) {
                    room.add(newMeeting);
                    roomFound = true;
                    break;
                }
            }
            if (!roomFound) {
                java.util.LinkedList newRoom = new java.util.LinkedList();
                newRoom.add(newMeeting);
                totalRooms.add(newRoom);
            }
        }

        return totalRooms.size();
    }


    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        public Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public int[] productExceptSelf(int[] nums) {
        int product = 1;
        int[] res = new int[nums.length];
        List<Integer> indexesOfZeros = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                indexesOfZeros.add(i);
            } else {
                product = product * nums[i];
            }
        }

        if (indexesOfZeros.size() > 1) {
            for (int i = 0; i < res.length; i++) {
                res[i] = 0;
            }
        } else if (indexesOfZeros.size() == 1) {
            for (int j = 0; j < res.length; j++) {
                if (indexesOfZeros.contains(j)) {
                    res[j] = product;
                } else {
                    res[j] = 0;
                }
            }
        } else {
            for (int j = 0; j < res.length; j++) {
                int val = product / nums[j];
                res[j] = val;

            }
        }


        return res;
    }


    /*["hot","dot","dog","lot","log","cog"]
     */

    // hit -> cog

    public int findLadders(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord)) return -1;
        ArrayDeque<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        queue.add(beginWord);
        queue.add("#");
        visited.add(beginWord);

        int minPath = 1;

        while (!queue.isEmpty()) {
            String en = queue.poll();
            if (en.equals(endWord)) {
                break;
            }
            if (en.equals("#")) {
                minPath++;
                queue.add("#");
                continue;
            }

            List<String> children = getChildren(en, wordList);
            if (children.size() > 0) {
                for (String s : children) {
                    if (visited.add(s)) {
                        queue.add(s);
                    }
                }
            }

        }

        return minPath;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (!wordList.contains(endWord) || beginWord.equals(endWord)) return res;
        Set<String> visited = new HashSet<>();
        Queue<LadderNode> queue = new ArrayDeque<>();

        LadderNode begin = new LadderNode(beginWord);

        visited.add(beginWord);
        queue.add(begin);

        buildChildLadderGraph(endWord, queue, visited, wordList);
        addAllPaths(res, begin, new ArrayList(), endWord);

        if (res.size() > 1) {
            res.sort((o1, o2) -> o1.size() - o2.size());

            int neededSize = res.get(0).size();
            List<List<String>> newRes = new ArrayList<>();

            for (int i = 0; i < res.size(); i++) {
                if (res.get(i).size() == neededSize) {
                    newRes.add(res.get(i));
                } else {
                    break;
                }
            }

            res = newRes;

        }


        return res;

    }

    private void addAllPaths(List<List<String>> res, LadderNode root, List<String> temp, String end) {

        if (root.node.equals(end)) {
            temp.add(root.node);
            res.add(new ArrayList<>(temp));
            return;
        }

        temp.add(root.node);
        List<LadderNode> children = root.children;
        if (children.size() > 0) {
            for (LadderNode n : children) {
                addAllPaths(res, n, temp, end);
                temp.remove(n.node);
            }
        }
    }

    private void buildChildLadderGraph(String end, Queue<LadderNode> queue, Set<String> visited, List<String> dict) {

        while (!queue.isEmpty()) {
            LadderNode node = queue.poll();
            if (node != null) {
                List<String> uncheckedChildren = getChildNodes(dict, node.node);
                for (String s : uncheckedChildren) {
                    if (visited.add(s) || s.equals(end)) {
                        LadderNode n = new LadderNode(s);
                        node.children.add(n);
                        queue.add(n);
                    }
                }
            }

        }


    }

    private class LadderNode {
        String node;
        List<LadderNode> children = new ArrayList<>();

        LadderNode(String val) {
            this.node = val;
        }
    }

    private List<String> getChildNodes(List<String> dict, String word) {
        List<String> children = new ArrayList<>();
        char[] find = word.toCharArray();

        for (String s : dict) {

            int diff = 0;
            char[] sChar = s.toCharArray();
            for (int i = 0; i < sChar.length; i++) {
                if (sChar[i] != find[i]) {
                    diff++;
                }
                if (diff > 1) {
                    break;
                }
            }

            if (diff == 1) {
                children.add(s);
            }

        }

        return children;

    }

    private List<String> getChildren(String word, List<String> wordList) {
        List<String> children = new ArrayList<>();
        char[] res = word.toCharArray();
        for (String s : wordList) {
            int diff = 0;
            char[] arr = s.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                if (res[i] != arr[i]) {
                    diff++;
                }
                if (diff > 1) {
                    break;
                }
            }
            if (diff == 1) {
                children.add(s);
            }

        }
        return children;
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    private void backtrack(List<String> ans, String cur, int open, int close, int max) {

        if (cur.length() == 2 * max) {
            ans.add(cur);
            return;
        }

        if (open < max) {
            backtrack(ans, cur + "(", open + 1, close, max);
        }
        if (close < open) {
            backtrack(ans, cur + ")", open, close + 1, max);
        }

    }

    public class BSTIterator {

        LinkedList<Integer> list = new LinkedList<>();

        public BSTIterator(TreeNode root) {
            generateIteratorList(root);

        }

        private void generateIteratorList(TreeNode root) {
            if (root == null) {
                return;
            }

            generateIteratorList(root.left);
            list.add(root.val);
            generateIteratorList(root.right);

        }

        /**
         * @return the next smallest number
         */
        public int next() {
            if (list.size() > 0) {
                int val = list.poll();
                return val;
            } else {
                return -1;
            }

        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return list.size() > 0;
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
            TreeNode n2 = new TreeNode(2);
            TreeNode n7 = new TreeNode(7);
            TreeNode n1 = new TreeNode(1);
            TreeNode n3 = new TreeNode(3);

            n4.left = n2;
            n4.right = n7;
            n2.left = n1;
            n2.right = n3;

            return n4;
        }
    }

}
