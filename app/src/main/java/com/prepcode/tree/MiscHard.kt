package com.prepcode.tree

import java.util.*
import kotlin.collections.HashMap
import kotlin.math.max


class MiscHard {

    /* Question 1 - Find the max number of points which lie on a line */

    /*TODO - revisit this */
    fun maxPoints(points: Array<LinePoint>): Int {
        if (points.size in 0..2) return points.size
        val map = mutableMapOf<PointAnchor, MutableList<LinePoint>>()
        for (i: Int in 0 until points.size) {
            val anchorPoint = points[i]
            val j = i + 1
            var dup = 0
            for (j in 0 until points.size) {
                val nextPoint = points[j]
                if (anchorPoint == nextPoint) {
                    dup++
                    continue
                } else {
                    val x1 = anchorPoint.x.toDouble()
                    val y1 = anchorPoint.y.toDouble()
                    val x2 = nextPoint.x.toDouble()
                    val y2 = nextPoint.y.toDouble()

                    var slope = (y2 - y1) / (x2 - x1)
                    if (slope < 0) {
                        slope *= -1
                    }
                    val anchorPoint = PointAnchor(slope, anchorPoint)
                    addToMapIfNotPresent(map, slope, anchorPoint, nextPoint)
                }
            }
        }

        val mapItr = map.iterator()
        var largest = mapItr.next()
        while (mapItr.hasNext()) {
            val nextVal = mapItr.next()
            if (largest.value.size < nextVal.value.size) {
                largest = nextVal
            } else {
                continue
            }
        }
        return largest.value.size
    }


    private fun addToMapIfNotPresent(
        map: MutableMap<PointAnchor, MutableList<LinePoint>>, slope: Double, anchorPoint: PointAnchor,
        nextPoint: LinePoint
    ) {
        if (map.containsKey(anchorPoint)) {
            val list = map[anchorPoint]?.add(nextPoint)
        } else {

        }
    }


/* Question - 2 - Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.*/

    fun numberOfValidParentheses(s: String): Int {
        var count = 0
        if (s.isEmpty() || s.length < 2) return count
        val charArr = s.toCharArray()
        val charList = mutableListOf<Char>()
        for (i: Int in 0 until charArr.size) {
            charList.add(charArr[i])
        }



        while (charList.size > 1) {
            val leftChar = charList[0]
            var rightParPointer = 1
            if (leftChar.equals('(')) {
                while (rightParPointer < charList.size) {
                    val rightPar = charList[rightParPointer]
                    if (rightPar.equals(')')) {
                        count++
                        charList.removeAt(rightParPointer)
                        charList.removeAt(0)
                        break
                    }
                    rightParPointer++
                }
            } else {
                charList.removeAt(0)
                continue
            }

        }

        return count * 2

    }

    /*function to return the length of the longest possible valid parentheses string*/
    fun longestValidParenthesesExcludingInner(s: String): Int {
        var largestLength = 0
        if (s.isEmpty() || s.length < 2) return largestLength
        val charArr = s.toCharArray()
        var currLength = 0
        var leftParPointer = 0

        while (leftParPointer < charArr.size) {
            if (charArr[leftParPointer] == '(') {
                val rightParPointer = leftParPointer + 1
                if (rightParPointer < charArr.size) {
                    if (charArr[rightParPointer] == ')') {
                        currLength++
                        leftParPointer = rightParPointer + 1
                    } else {
                        if (largestLength < currLength) {
                            largestLength = currLength
                            currLength = 0
                        }
                        leftParPointer = rightParPointer
                    }

                } else {
                    break
                }
            } else {
                leftParPointer++
            }
        }

        return max(largestLength, currLength) * 2
    }


    fun longestValidParentheses(s: String): Int {
        var largestLength = 0
        if (s.isEmpty() || s.length < 2) return largestLength
        val charArr = s.toCharArray()

        val stack = Stack<Int>()

        for (i: Int in 0 until charArr.size) {
            val c = charArr[i]
            if (stack.empty()) {
                stack.push(i)
            } else {
                if (c.equals('(')) {
                    stack.push(i)
                } else {
                    if (charArr[stack.peek()].equals('(')) {
                        stack.pop()
                    } else {
                        stack.push(i)
                    }
                }
            }
        }

        if (stack.isEmpty()) {
            largestLength = s.length
        } else {
            var a = s.length
            var b = 0
            while (!stack.empty()) {
                b = stack.peek()
                stack.pop()
                largestLength = max(largestLength, a - b - 1)
                a = b
            }
            largestLength = max(largestLength, a)
        }

        return largestLength
    }

    /*Question Given an unsorted integer array, find the smallest missing positive integer.*/

    fun firstMissingPositive(nums: IntArray): Int {

        if (nums.isEmpty()) return 1
        if (nums.size == 1) return findSmallestPositiveSuccessor(nums[0])
        var currSmall = nums[0]
        var currMissing = findSmallestPositiveSuccessor(currSmall)

        for (i: Int in 1 until nums.size) {
            val next = nums[i]
            if (currSmall > next) {
                currSmall = next
                currMissing = findSmallestPositiveSuccessor(currSmall)
            }

            if (next == currMissing) currMissing += 1
        }

        return currMissing

    }

    private fun findSmallestPositiveSuccessor(num: Int): Int {
        val test = num + 1
        if (test > 0) return test
        return findSmallestPositiveSuccessor(test)
    }


/*Question - Subarrays with K Different Integers*/


    fun subarraysWithKDistinct(A: IntArray, K: Int): Int {

        if (A.isEmpty()) return 0

        var result = 0
        var map: HashMap<Int, Int>
        for (i: Int in 0..A.size - K) {
            map = HashMap()
            map[A[i]] = 1
            if (map.size == K) {
                result++
            }
            for (j: Int in i + 1 until A.size) {
                val value = A[j]
                if (map.containsKey(value)) {
                    map[value] = map[value]!!.plus(1)
                } else {
                    map[value] = 1
                }

                if (map.size == K) {
                    result++
                }
            }

        }
        return result

    }


}

// Helper classes
data class PointAnchor(val slope: Double, val anchorPoint: LinePoint)

































