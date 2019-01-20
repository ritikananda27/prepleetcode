package com.prepcode.tree

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
                    l = i+1
                }
            }

        }

        return listOfList.toList()

    }


}