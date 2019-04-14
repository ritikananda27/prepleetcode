package com.prepcode.tree.facebook;

import android.os.Build;
import android.support.annotation.RequiresApi;
import com.prepcode.tree.ListNode;
import com.prepcode.tree.TreeNode;

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
                }else{
                    res[j]= 0;
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

}
