package com.prepcode.tree

import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.absoluteValue


class MiscModerate {

    /* Question - Implement atoi which converts a string to an integer.*/
    fun myAtoi(str: String): Int {
        if (str.isEmpty()) return 0
        var sign = 1

        val string = str.trim()
        val charArr = string.toCharArray()

        val sb = StringBuilder()

        for (i: Int in 0 until charArr.size) {
            val c = charArr[i]

            if (c == '+' || c == '-') {
                if (sb.isEmpty()) {
                    sb.append(c)
                } else {
                    break
                }
            } else if (c == '0' && (sb.isEmpty() || sb.length == 1 && (sb[0] == '-' || sb[0] == '+'))) {
                continue
            } else if (c == ' ') {
                continue
            } else if (Character.isDigit(c)) {
                sb.append(c)
            } else {
                break
            }
        }

        var resStr = sb.toString()
        var res: Int = 0
        var finalStr = ""
        if (resStr.isNotEmpty()) {
            val first = resStr.substring(0, 1)

            if (first == "-" || first == "+") {
                finalStr = resStr.substring(1)
                sign = if (first == "+") {
                    1
                } else {
                    -1
                }
            } else {
                finalStr = resStr
            }
        }

        if (finalStr.isNotEmpty()) {

            if (finalStr.length < 10) {
                res = finalStr.toInt() * sign
            } else {
                if (sign < 0) {
                    res = Int.MIN_VALUE
                } else {
                    res = Int.MAX_VALUE
                }
            }

        } else {
            res = 0
        }


        return res

    }

    /* Question -  Reverse Words in a String*/

    // reverse string without preserving spaces
    fun reverseString(str: String): String {
        val sb = java.lang.StringBuilder()
        val strArr = str.split(" ")
        for (i: Int in strArr.size - 1 downTo 0) {
            var s = strArr[i]

            if (s.isNotEmpty()) {
                sb.append("$s ")
            } else {
                sb.append(" ")
            }
        }

        return sb.toString()
    }

/*You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.*/

    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var returnNode: ListNode? = null
        if (l1 == null && l2 == null) return null

        var l1Head = l1
        var l2Head = l2
        var carry = 0

        var returnHead: ListNode? = null
        while (l1Head != null || l2Head != null) {
            val l1Number = if (l1Head != null) l1Head.value else 0
            val l2Number = if (l2Head != null) l2Head.value else 0

            val sum = l1Number + l2Number + carry
            carry = if (sum >= 10) sum / 10 else 0
            val nodeVal = if (sum >= 10) sum % 10 else sum

            if (returnNode == null) {
                returnNode = ListNode(nodeVal, null)
                returnHead = returnNode
            } else {
                returnNode.next = ListNode(nodeVal, null)
                returnNode = returnNode.next
            }
            l1Head = l1Head?.next
            l2Head = l2Head?.next
        }

        if (carry > 0) {
            returnNode?.next = ListNode(carry, null)
        }


        return returnHead

    }


    /*Question -  Longest Palindromic Substring*/
    fun longestPalindrome(s: String): String {
        var str = s
        if (str.length < 2) return str
        if (str.length > 1000) {
            str = s.substring(0, 1000)
        }

        val charArray = str.toCharArray()

        val list = mutableListOf<String>()
        for (i: Int in 0 until charArray.size) {
            for (j: Int in charArray.size - 1 downTo i + 1) {
                if (charArray[i] == charArray[j]) {
                    val test = str.substring(i, j + 1)
                    if (isStringPalindrom(test)) {
                        list.add(test)
                    }
                }
            }
        }

        if (list.size > 0) {
            var largest = list[0]
            for (i: Int in 0 until list.size) {
                if (largest.length < list[i].length) {
                    largest = list[i]
                }
            }

            return largest
        } else {
            return str.substring(0, 1)
        }

    }

    fun longestPalindrome2(s: String): String {
        var str = s
        if (str.length < 2) return str
        if (str.length > 1000) {
            str = s.substring(0, 1000)
        }

        val list = mutableListOf<String>()
        for (i: Int in 0 until s.length + 1) {
            for (j: Int in i until s.length + 1) {
                val subStr = s.substring(i, j)
                if (isStringPalindrom(subStr)) {
                    list.add(subStr)
                }
            }
        }

        if (list.size > 0) {
            var largest = list[0]
            for (i: Int in 0 until list.size) {
                if (largest.length < list[i].length) {
                    largest = list[i]
                }
            }

            return largest
        } else {
            return str.substring(0, 1)
        }


    }

    private fun isStringPalindrom(s: String): Boolean {
        if (s.isEmpty()) return false
        if (s.length < 2) return true

        var i = 0
        var j = s.length - 1

        val charArr = s.toCharArray()
        var res = true

        while (i != j && i < j) {
            if (charArr[i] == charArr[j]) {
                i++
                j--
            } else {
                res = false
                break
            }
        }
        return res
    }


    /*Question -  Container With Most Water*/
    fun maxArea(height: IntArray): Int {
        if (height.isEmpty()) return 0
        if (height.size == 1) return height[0]

        var left = 0
        var right = height.size - 1
        var largestArea = 0

        while (left < right) {
            val base = right - left
            var currArea = 0
            if (height[left] < height[right]) {
                currArea = height[left] * base
                left++
            } else {
                currArea = height[right] * base
                right--
            }

            if (largestArea < currArea) {
                largestArea = currArea
            }

        }

        return largestArea
    }


    /*Question - Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.*/

    /*use an extra space and sorting */
    fun threeSum(nums: IntArray): List<List<Int>> {
        val listOfList = mutableSetOf<List<Int>>()
        if (nums.isEmpty() || nums.size < 3) return listOfList.toList()

        nums.sort()
        val size = nums.size
        for (i: Int in 0..size - 2) {

            var l = i + 1
            var r = size - 1

            while (l < r) {
                val check = nums[i] + nums[l] + nums[r]
                if (check == 0) {
                    val list = listOf(nums[i], nums[l], nums[r])
                    listOfList.add(list)
                    l++
                    r--
                } else if (check < 0) {
                    l++
                } else {
                    r--
                }
            }

        }
        return listOfList.toList()

    }

    fun threeSumWithoutSorting(nums: IntArray): List<List<Int>> {
        val listOfList = mutableSetOf<List<Int>>()
        if (nums.isEmpty() || nums.size < 3) return listOfList.toList()

        val size = nums.size

        for (i: Int in 0..size - 2) {
            var l = i + 1
            var r = size - 1

            while (r > i) {
                val chk = nums[i] + nums[l] + nums[r]
                if (chk == 0) {
                    val list = listOf(nums[i], nums[l], nums[r])
                    listOfList.add(list)
                }
                l++
                if (l >= r) {
                    r--
                    l = i + 1
                }
            }

        }

        return listOfList.toList()

    }

    /*Question - divide without using division and multiplication operators  */
    fun divide(dividend: Int, divisor: Int): Int {
        if (divisor == 0) return Int.MAX_VALUE
        var newDividend = dividend
        var sign = 1
        if ((divisor > 0 && dividend < 0) || (dividend > 0 && divisor < 0)) {
            sign = -1
        }
        val newDivisor = divisor.absoluteValue
        if (dividend == Int.MIN_VALUE) {
            if (divisor == -1) {
                return Int.MIN_VALUE
            } else {
                newDividend = Int.MAX_VALUE
            }
        }

        if (newDividend < 0) {
            newDividend *= -1
        }
        if (newDividend < newDivisor) return 0
        var factor = 0
        while (newDividend >= newDivisor) {
            factor++
            newDividend -= newDivisor
        }

        return factor * sign
    }


    /*Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand. find the int target*/
    fun search(target: Int): Int {
        val arr = IntArray(1)
        arr[0] = 4


        if (arr.isEmpty()) return -1
        if (arr.size == 1) {
            return if (arr[0] == target) {
                0
            } else {
                -1
            }
        }

        // find the position of the pivot

        var positionFromEnd = 0
        val startPos = arr[0]

        for (i: Int in arr.size - 1 downTo 0) {
            if (startPos > arr[i]) {
                positionFromEnd++
            } else {
                break
            }
        }

        val pushFromStart = arr.size - positionFromEnd

        // find the position of the the element in the rotated arr

        var start = 0
        var end = arr.size - 1

        var currPos = -1

        while (start < end) {
            val startEle = arr[start]
            val endEle = arr[end]

            if (target >= startEle) {
                if (target == startEle) {
                    currPos = start
                    break
                }
                start++
            } else {
                if (target == endEle) {
                    currPos = end
                    break
                }
                end--
            }

        }

        if (currPos > -1) {
            val originalPos = currPos - pushFromStart
            if (originalPos < 0) {
                return arr.size + originalPos
            } else {
                return originalPos
            }
        } else {
            return currPos
        }
    }


    fun testsearch(target: Int): Int {
        val nums = IntArray(2)
        nums[0] = 1
        nums[1] = 3
        if (nums.isEmpty()) return -1
        if (nums.size == 1) {
            return if (nums[0] == target) {
                0
            } else {
                -1
            }
        }


        // find the position of the the element in the rotated arr

        var start = 0
        var end = nums.size - 1

        var currPos = -1

        while (start < end) {
            val startEle = nums[start]
            val endEle = nums[end]


            if (target == startEle) {
                currPos = start
                break
            } else if (target == endEle) {
                currPos = end
                break
            } else if (target >= startEle) {
                start++
            } else {
                end--
            }

        }

        return currPos
    }


    fun searchRange(nums: IntArray, target: Int): IntArray {
        if (nums.isEmpty() || target < nums[0]) {
            return intArrayOf(-1, -1)
        }
        if (nums.size == 1) {
            if (nums[0] == target) {
                return intArrayOf(0, 0)
            } else {
                return intArrayOf(-1, -1)
            }
        }

        var left = 0
        var right = nums.size - 1

        var leftFound = -1
        var rightFound = -1

        while (left <= right) {
            val leftEle = nums[left]
            val rightEle = nums[right]

            if (leftEle == target) {
                leftFound = left
                right = left
                while (right + 1 < nums.size && (nums[right + 1] == leftEle)) {
                    right++
                }
                return intArrayOf(leftFound, right)
            }
            if (rightEle == target) {
                rightFound = right
                left = right

                while (left - 1 > 0 && (nums[left - 1] == rightEle)) {
                    left--
                }
                return intArrayOf(left, rightFound)
            }
            left++
            right--
        }

        return intArrayOf(leftFound, rightFound)
    }


    /*Important question - check the logic to navigate using '/' and '%' results
    * also shows how to navigate between a 2d array */
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        var rowCheck = false
        var columnCheck = false
        var cubeCheck = false
        for (i: Int in 0..8) {
            rowCheck = isValid(board, i, i, 0, 8)
            columnCheck = isValid(board, i, 8, i, i)
            cubeCheck = isValid(board, i / 3 * 3, i / 3 * 3 + 2, i % 3 * 3, i % 3 * 3 + 2)

        }


        return rowCheck && columnCheck && cubeCheck
    }

    private fun isValid(board: Array<CharArray>, xStart: Int, xEnd: Int, yStart: Int, yEnd: Int): Boolean {
        val hashSet = mutableSetOf<Char>()

        for (x: Int in xStart..xEnd) {
            for (y: Int in yStart..yEnd) {

                if (board[x][y] == '.' || hashSet.add(board[x][y])) {
                    continue
                } else {
                    return false
                }
            }
        }

        return true

    }


    /*Question - Given a collection of distinct integers, return all possible permutations. */
    fun permute(nums: IntArray) {
        /* val resList = mutableListOf<List<Int>>()
         calculatePermutations(resList, nums)
         return resList*/
        permuteTest(0, nums)

    }

    private fun calculatePermutations(resList: MutableList<List<Int>>, nums: IntArray) {

        for (i: Int in 0 until nums.size) {
            var start = i
            while ((start < nums.size)) {
                val temp = nums[i]
                nums[i] = nums[start]
                nums[start] = temp
                resList.add(nums.toList())
                start++
            }
        }

    }


    fun permuteTest(start: Int, input: IntArray) {
        if (start == input.size) {
            //System.out.println(input);
            for (x in input) {
                print(x)
            }
            println("")
            return
        }
        for (i in start until input.size) {
            // swapping
            val temp = input[i]
            input[i] = input[start]
            input[start] = temp
            // swap(input[i], input[start]);

            permuteTest(start + 1, input)
            // swap(input[i],input[start]);

            val temp2 = input[i]
            input[i] = input[start]
            input[start] = temp2
        }
    }


    /*Question - Given a 2D board and a word, find if the word exists in the grid*/

    fun exist(board: Array<CharArray>, word: String): Boolean {

        if (board.isEmpty()) return false
        if (word.isEmpty()) return true
        val xLength = board.size
        val yLength = board[0].size
        val resArray = Array(xLength) { BooleanArray(yLength) }

        for (x: Int in 0 until xLength) {
            for (y: Int in 0 until yLength) {
                if (checkLogic(board, resArray, word.toCharArray(), x, y, 0)) {
                    return true
                }
            }
        }

        return false
    }

    private fun checkLogic(
        board: Array<CharArray>,
        resArr: Array<BooleanArray>,
        charArrayToSearch: CharArray,
        startX: Int,
        startY: Int,
        wordPos: Int
    ): Boolean {


        if (wordPos == charArrayToSearch.size) {
            return true
        }

        // ever position is responsible to recursively check if its consecutive in any direction has the word as part of children
        if (startX >= 0 && startY >= 0 && startX < resArr.size && startY < resArr[0].size && !resArr[startX][startY] && board[startX][startY] == charArrayToSearch[wordPos]) {

            resArr[startX][startY] = true
            if (checkLogic(board, resArr, charArrayToSearch, startX, startY + 1, wordPos + 1)) {
                return true
            } else if (checkLogic(board, resArr, charArrayToSearch, startX + 1, startY, wordPos + 1)) {
                return true

            } else if (checkLogic(board, resArr, charArrayToSearch, startX, startY - 1, wordPos + 1)) {
                return true
            } else if (checkLogic(board, resArr, charArrayToSearch, startX - 1, startY, wordPos + 1)) {
                return true
            } else {
                resArr[startX][startY] = false
            }
        } else {
            return false
        }

        return false
    }


    // Combinations and Permutations


    fun subsets(nums: IntArray): List<List<Int>> {
        val list: MutableList<List<Int>> = ArrayList()
        Arrays.sort(nums)
        backtrack(list, ArrayList(), nums, 0)
        return list
    }

    private fun backtrack(list: MutableList<List<Int>>, tempList: MutableList<Int>, nums: IntArray, start: Int) {
        list.add(ArrayList(tempList))
        for (i in start until nums.size) {
            tempList.add(nums[i])
            backtrack(list, tempList, nums, i + 1)
            tempList.removeAt(tempList.size - 1)
        }
    }


    /* Reading a File using various Readers and writing to files   */

    fun readFromAFileUsingBufferedReader() {
        val file = File("/Users/rnanda/Desktop/sample.txt")
        val br = BufferedReader(FileReader(file));

        val lineNumberList = mutableListOf<Int>()

        var lineNumber = 0
        var line = br.readLine()
        while (line != null) {
            lineNumber++
            if (line.contains("Text")) {
                lineNumberList.add(lineNumber)
            }
            line = br.readLine()
        }

        println(lineNumberList)
    }

    fun readFromAFileUsingFileReader() {
        val file = File("/Users/rnanda/Desktop/sample.txt")
        val fr = FileInputStream(file)

        val sb = java.lang.StringBuilder()
        var read = fr.read()
        while (read != -1) {
            sb.append(read.toChar())
            read = fr.read()
        }

        println(sb.toString())

    }


    fun readFileUsingScanner() {
        val file = File("/Users/rnanda/Desktop/sample.txt")
        val scanner = Scanner(file)

        while (scanner.hasNextLine()) {
            println(scanner.nextLine())
        }
    }


    fun writeToTextFile(text: String) {
        val out = BufferedWriter(FileWriter("/Users/rnanda/Desktop/sample1.txt"))
        out.write(text)
        out.newLine()
        out.close()
    }


    fun readFromFileWriteToAnother() {
        val file = File("/Users/rnanda/Desktop/sample.txt")
        val br = BufferedReader(FileReader(file));

        val sb = java.lang.StringBuilder()
        var line = br.readLine()
        while (line != null) {
            sb.append(line)
            sb.append('\n')
            line = br.readLine()

        }
        writeToTextFile(sb.toString())
    }


    /*Sorting */
    fun sortColors(nums: IntArray) {

        if (nums.size < 2) {
            return
        }

        var p1 = 0
        var p2 = nums.size - 1
        var i = 0

        while (i <= p2) {
            if (nums[i] == 0) {
                nums[i] = nums[p1]
                nums[p1] = 0
                p1++
                i++
            } else if (nums[i] == 2) {
                nums[i] = nums[p2]
                nums[p2] = 2
                p2--
            } else {
                i++
            }
        }
    }


    /*A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
    The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
    How many possible unique paths are there*/


    fun uniquePath(m: Int, n: Int): Int {

        if (m == 1 || n == 1) {
            return 1
        }

        return uniquePath(m - 1, n) + uniquePath(m, n - 1)
    }
    /*Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.*/

    fun minPathSum(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        return minCost(grid, m - 1, n - 1)
    }

    private fun minCost(grid: Array<IntArray>, m: Int, n: Int): Int {

        if (n < 0 || m < 0)
            return Int.MAX_VALUE;
        if (m == 0 && n == 0) {
            return grid[m][n]
        }

        return grid[m][n] + Math.min(minCost(grid, m - 1, n), minCost(grid, m, n - 1))
    }


    /*Question - Given an array of strings, group anagrams together.*/
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val resList: MutableList<List<String>> = mutableListOf()
        if (strs.isEmpty()) {
            return resList
        }

        if (strs.size == 1) {
            resList.add(listOf(strs[0]))
            return resList
        }

        val testList = strs.toMutableList()

        while (testList.size > 1) {
            val subject = testList[0]
            val childRes = mutableListOf(subject)

            for (i: Int in 1 until testList.size) {
                if (ifStringsAreAnagrams(subject, testList[i])) {
                    childRes.add(testList[i])
                }
            }
            resList.add(childRes)
            testList.removeAll(childRes)

        }

        if (testList.size > 0) {
            resList.add(listOf(testList[0]))
        }


        return resList
    }

    private fun ifStringsAreAnagrams(s1: String, s2: String): Boolean {
        val arr1 = s1.toMutableList()
        val arr2 = s2.toMutableList()

        var result = true

        if (arr1.size == arr2.size) {

            while (arr1.size > 0 && arr2.size > 0) {
                val c = arr1[0]
                if (arr2.contains(c)) {
                    arr1.remove(c)
                    arr2.remove(c)
                } else {
                    result = false
                    return result
                }

            }

        } else {
            result = false
        }

        return result
    }


    /*Serialize and deserialize a binary tree */

    fun serialize(root: TreeNode?, array: MutableList<Int>) {

        if (root == null) {
            array.add(-1)
            return
        } else {
            array.add(root.value)
        }

        serialize(root.left, array)
        serialize(root.right, array)

    }

    fun deserializeTree(list: MutableList<Int>): MutableNode? {

        return TreeJava().deserializeTestInOrder(list)

    }


    // Search an element in a 2d matrix
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        if (matrix.isEmpty()) return false

        val rows = matrix.size
        val columns = matrix[0].size

        val resMatrix: Array<BooleanArray> = Array(rows) { BooleanArray(columns) { true } }

        for (i: Int in 0 until rows) {
            for (j: Int in 0 until columns) {

                val num = matrix[i][j]

                if (num == target) {
                    return true
                } else if (!resMatrix[i][j]) {
                    break
                } else if (resMatrix[i][j] && num > target) {
                    markMatrixFalse(resMatrix, j, columns - 1, i, rows - 1)
                    break
                }
            }
        }
        return false
    }

    private fun markMatrixFalse(
        resMatrix: Array<BooleanArray>,
        colStart: Int,
        maxCol: Int,
        rowStart: Int,
        maxRow: Int
    ) {
        for (i: Int in rowStart..maxRow) {
            for (j: Int in colStart..maxCol) {
                resMatrix[i][j] = false
            }
        }
    }

    fun searchMatrix2(matrix: Array<IntArray>, target: Int): Boolean {
        if (matrix.isEmpty()) return false

        var rowStart = 0
        val rows = matrix.size - 1
        var columns = matrix[0].size - 1

        while (rowStart <= rows) {
            for (j: Int in 0..columns) {

                val num = matrix[rowStart][j]
                if (num == target) {
                    return true
                } else if (num > target) {
                    columns = j - 1
                    break
                }

            }

            rowStart++
        }

        return false
    }


    /* Question - Find Duplicate */
    fun findDuplicate(nums: IntArray): Int {
        if (nums.isEmpty()) return -1

        if (nums.size < 2) {
            return nums[0]
        }
        nums.sort()

        for (i: Int in 0..nums.size - 2) {
            val first = nums[i]
            val second = nums[i + 1]
            if (first == second) {
                return nums[i]
            }
        }

        return -1

    }


    /*Question - combinations of phone numbers */

    fun combinePhoneNumber(digit: String): List<String> {

        if (digit.isEmpty()) return emptyList()

        val map = HashMap<Char, CharArray>()
        map['2'] = charArrayOf('a', 'b', 'c')
        map['3'] = charArrayOf('d', 'e', 'f')
        map['4'] = charArrayOf('g', 'h', 'i')
        map['5'] = charArrayOf('j', 'k', 'l')
        map['6'] = charArrayOf('m', 'n', 'o')
        map['7'] = charArrayOf('p', 'q', 'r', 's')
        map['8'] = charArrayOf('t', 'u', 'v')
        map['9'] = charArrayOf('w', 'x', 'y', 'z')


        val arr = Array(digit.length) { CharArray(3) }
        var pointer = 0
        digit.toCharArray().forEach {
            arr[pointer] = map.get(it)!!
            pointer++
        }
        val resList = mutableListOf<String>()
        getCombinations(resList, StringBuilder(), arr, 0)

        return resList

    }

    private fun getCombinations(
        res: MutableList<String>, sb: StringBuilder,
        arr: Array<CharArray>, pointer: Int
    ) {

        if (pointer == arr.size) {
            res.add(sb.toString())
            return
        }

        val subArr = arr[pointer]

        for (i: Int in 0 until subArr.size) {
            sb.append(subArr[i])
            getCombinations(res, sb, arr, pointer + 1)
            sb.deleteCharAt(sb.length - 1)

        }


    }

    /*Dynamic Programming*/
    /*Question - Buy and sell stock for max profit */

    fun maxProfit(prices: IntArray): Int {
        var maxProfit = 0
        for (i: Int in 0 until prices.size) {
            for (j: Int in i until prices.size) {

                if (prices[j] > prices[i]) {
                    val diff = prices[j] - prices[i]
                    if (diff > maxProfit) {
                        maxProfit = diff
                    }
                }
            }
        }

        return maxProfit

    }


    /*Question - number of meeting rooms needed */
    fun minMeetingRooms(intervals: Array<Interval>): Int {

        if (intervals.size < 2) {
            return intervals.size
        }

        intervals.sortBy { it.start }

        val meetingRoomList = mutableListOf<Int>()

        intervals.forEach {
            if (meetingRoomList.size == 0) {
                meetingRoomList.add(it.end)
            } else {
                var meetingRoomAvailable = false
                for (i: Int in meetingRoomList.size - 1 downTo 0) {
                    if (it.start >= meetingRoomList[i]) {
                        meetingRoomAvailable = true
                        meetingRoomList[i] = it.end
                        break
                    }
                }
                if (!meetingRoomAvailable) {
                    meetingRoomList.add(it.end)
                }
            }
        }


        return meetingRoomList.size
    }



    fun wordBreak(s: String, wordDict: List<String>): Boolean {



    }
}



class Interval constructor(var start: Int, var end: Int)