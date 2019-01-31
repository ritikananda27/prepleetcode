package com.prepcode.tree

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
            columnCheck = isValid(board, 0, 8, i, i)
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

}