package com.prepcode.tree;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.*;

public class GoogleFinal {


    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums.length == 0) return res;

        int left = 0;
        int right = 0;

        while (right < nums.length - 1) {
            int num = nums[right];
            int next = num + 1;

            if (nums[right + 1] == next) {
                right++;
            } else {
                String re = createString(nums, left, right);
                res.add(re);
                right++;
                left = right;
            }
        }


        res.add(createString(nums, left, right));


        return res;

    }

    private String createString(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start] + "";
        }

        return nums[start] +
                "->" +
                nums[end];
    }

    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix.length == 0) return false;

        int col = matrix[0].length - 1;
        int row = 0;

        while (col >= 0 && row < matrix.length) {
            int val = matrix[row][col];
            if (target == val) {
                return true;
            } else if (target < val) {
                col--;
            } else {
                row++;
            }
        }

        return false;

    }


    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        if (T.length == 0) return res;

        Stack<Integer> buffer = new Stack<>();

        for (int i = T.length - 1; i >= 0; i--) {
            int temp = T[i];

            while (!buffer.isEmpty() && T[buffer.peek()] <= temp) {
                buffer.pop();
            }

            res[i] = buffer.isEmpty() ? 0 : buffer.peek() - i;
            buffer.add(i);
        }
        return res;

    }

    class BSTIterator {

        List<Integer> resList = new ArrayList<>();

        public BSTIterator(TreeNode root) {
            populateResList(root);
        }

        private void populateResList(TreeNode root) {
            if (root == null) return;
            populateResList(root.left);
            resList.add(root.val);
            populateResList(root.right);
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            if (resList.size() > 0) {
                int val = resList.get(0);
                resList.remove(0);
                return val;
            } else return -1;
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return resList.size() > 0;
        }
    }

    public String decodeString(String s) {

        if (s.isEmpty()) return s;

        int i = 0;

        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();

        while (i < s.length()) {

            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                int start = i;
                while (Character.isDigit(s.charAt(i + 1))) {
                    i++;
                }
                countStack.push(Integer.parseInt(s.substring(start, i + 1)));
            } else if (ch == '[') {
                resStack.push("");
            } else if (ch == ']') {
                int lastCount = countStack.pop();
                String lastStr = resStack.pop();

                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < lastCount; j++) {
                    sb.append(lastStr);
                }
                if (resStack.isEmpty()) {
                    resStack.push(sb.toString());
                } else {
                    resStack.push(resStack.pop() + sb.toString());
                }
            } else {
                if (resStack.isEmpty()) {
                    resStack.push(ch + "");
                } else {
                    resStack.push(resStack.pop() + ch);
                }
            }

            i++;
        }

        return resStack.pop();
    }

    List<Integer> resList = new ArrayList<>();

    public List<Integer> flattenList(List<Object> list) {
        if (list.isEmpty()) return null;
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if (obj instanceof Integer) {
                resList.add((Integer) obj);
            } else {
                flattenList((List<Object>) obj);
            }
        }

        return resList;

    }

    public int[][] reconstructQueue(int[][] people) {

        Arrays.sort(people, (o1, o2) -> {
            int value1 = o1[1] - o2[1];
            if (value1 == 0) {
                return o1[0] - o2[0];
            }
            return value1;
        });

        return people;

    }

    public boolean isPowerOfThree(int n) {
        if (n == 0) return false;
        return power3Helper(n);
    }

    private boolean power3Helper(int n) {
        if (n == 1) return true;
        if (n % 3 == 0) return power3Helper(n / 3);
        else return false;
    }


    public boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        return power2Helper(n);
    }

    private boolean power2Helper(int n) {
        if (n == 1) return true;
        if (n % 2 == 0) return power2Helper(n / 2);
        else return false;
    }

    private enum Move {

        UP(1, 'U'),
        DOWN(-1, 'D'),
        LEFT(2, 'L'),
        RIGHT(-2, 'R');

        public int val;
        public char charVal;

        private Move(int val, char c) {
            this.val = val;
            this.charVal = c;

        }

    }


    public boolean judgeCircle(String moves) {

        int sum = 0;
        for (char c : moves.toCharArray()) {
            if (c == Move.UP.charVal) {
                sum = sum + Move.UP.val;
            } else if (c == Move.DOWN.charVal) {
                sum = sum + Move.DOWN.val;
            } else if (c == Move.LEFT.charVal) {
                sum = sum + Move.LEFT.val;
            } else if (c == Move.RIGHT.charVal) {
                sum = sum + Move.RIGHT.val;
            }
        }

        return sum == 0;

    }

    public String reverseVowels(String s) {
        Set<Character> vowelSet = new HashSet<>();
        vowelSet.add('a');
        vowelSet.add('e');
        vowelSet.add('i');
        vowelSet.add('o');
        vowelSet.add('u');
        vowelSet.add('A');
        vowelSet.add('E');
        vowelSet.add('I');
        vowelSet.add('O');
        vowelSet.add('U');

        List<Integer> vowelIndices = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (vowelSet.contains(s.charAt(i))) {
                vowelIndices.add(i);
            }
        }

        int i = 0;
        int j = vowelIndices.size() - 1;


        char[] sArr = s.toCharArray();
        while (i < j) {
            char temp = sArr[vowelIndices.get(i)];
            sArr[vowelIndices.get(i)] = sArr[vowelIndices.get(j)];
            sArr[vowelIndices.get(j)] = temp;
            i++;
            j--;
        }

        return new String(sArr);
    }

    public int[] plusOne(int[] digits) {
        if (digits.length == 0) return new int[0];

        for (int j = digits.length - 1; j >= 0; j--) {
            if (digits[j] < 9) {
                digits[j] = digits[j] + 1;
                return digits;
            }
            digits[j] = 0;
        }

        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;


    }

    public int islandPerimeter(int[][] grid) {
        if (grid == null) return 0;
        int paramter = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    paramter = paramter + calculateParameter(grid, row, col);
                }
            }
        }

        return paramter;
    }

    private int calculateParameter(int[][] grid, int row, int col) {
        int val = 0;

        if (col == 0 || grid[row][col - 1] == 0) val++;
        if (col == grid[0].length - 1 || grid[row][col + 1] == 0) val++;
        if (row == 0 || grid[row - 1][col] == 0) val++;
        if (row == grid.length - 1 || grid[row + 1][col] == 0) val++;
        return val;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            int val = nums[index];
            if (val > 0) {
                nums[index] = nums[index] * -1;
            }
        }

        for (int j = 0; j < nums.length; j++) {
            int val = nums[j];
            if (val > 0) {
                res.add(j + 1);
            }
        }

        return res;
    }

    public String addStrings(String num1, String num2) {
        if (num1.isEmpty()) return num2;
        if (num2.isEmpty()) return num1;

        int p1 = num1.length() - 1;
        int p2 = num2.length() - 1;
        StringBuilder sb = new StringBuilder();

        int carry = 0;
        while (p1 >= 0 || p2 >= 0) {
            int l1 = 0;
            int l2 = 0;

            if (p1 >= 0) {
                l1 = Character.getNumericValue(num1.charAt(p1));
                p1--;
            }
            if (p2 >= 0) {
                l2 = Character.getNumericValue(num2.charAt(p2));
                p2--;
            }
            int sum = carry + l2 + l1;
            int ans = sum;
            if (sum > 9) {
                ans = sum % 10;
                carry = sum / 10;
            } else {
                carry = 0;
            }
            sb.append(ans);
        }

        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int firstUniqChar(String s) {

        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            int count = countMap.getOrDefault(c, 0);
            countMap.put(c, count + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            char cc = s.charAt(i);
            if (countMap.get(cc) == 1) {
                return i;
            }
        }

        return -1;

    }

    class MinStack {

        class Node {
            int value;
            int min;
            Node next;

            public Node(int value, int min, Node next) {
                this.value = value;
                this.min = min;
                this.next = next;
            }
        }

        Node node;

        public MinStack() {
            node = null;
        }

        public void push(int x) {
            if (node == null) {
                node = new Node(x, x, null);
            } else {
                Node newNext = new Node(x, Math.min(x, node.min), node);
                node = newNext;
            }

        }

        public void pop() {
            if (node != null) {
                node = node.next;
            }
        }

        public int top() {
            if (node != null) {
                return node.value;
            }
            return -1;

        }

        public int getMin() {
            if (node != null) {
                return node.min;
            }
            return -1;
        }
    }

    public int numUniqueEmails(String[] emails) {

        Map<String, Integer> map = new HashMap<>();

        for (String email : emails) {
            String[] parts = email.split("@");
            String first = parts[0];
            String second = parts[1];

            int index = first.indexOf('+');
            if (index >= 0) {
                first = first.substring(0, index);
            }
            first = first.replace(".", "");

            String key = first + "@" + second;
            if (!map.containsKey(key)) {
                map.put(key, 1);
            }
        }

        return map.size();
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                    stack.push(c);
                    break;
                case ')':
                    if (stack.size() > 0 && stack.peek() == '(') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;

                case '[':
                    stack.push(c);
                    break;
                case ']':
                    if (stack.size() > 0 && stack.peek() == '[') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;

                case '{':
                    stack.push(c);
                    break;
                case '}':
                    if (stack.size() > 0 && stack.peek() == '{') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;


            }
        }

        return stack.isEmpty();
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
            TreeNode n7 = new TreeNode(7);
            TreeNode n3 = new TreeNode(3);
            TreeNode n15 = new TreeNode(15);
            TreeNode n9 = new TreeNode(9);
            TreeNode n20 = new TreeNode(20);

            n7.left = n3;
            n7.right = n15;
            n15.left = n9;
            n15.right = n20;


            return n7;
        }
    }
}
