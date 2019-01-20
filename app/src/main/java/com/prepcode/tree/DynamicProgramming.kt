package com.prepcode.tree

class DynamicProgramming() {

    fun calculateFibSeries(n: Int): Int {
        val arr = IntArray(n + 1)

        arr[0] = 0
        arr[1] = 1

        if (n > 2) {
            for (i: Int in 2..n) {
                arr[i] = arr[i - 1] + arr[i - 2]
            }
            return arr[n]
        } else {
            return arr[1]
        }
    }

    fun calculateLongestPalindromicSubstringLength(s: String): Int {
        if (s.isEmpty()) return 0

        val len = s.length

        if (len == 1) return 1

        var longestLength = 1
        val arr = Array(len) { BooleanArray(len) }

        // every single char is a palindrome so in the 2d arr all the elements  where i == j (diagonal) will be true
        for (i: Int in 0..len - 1) {
            arr[i][i] = true
        }

        // check for substrings of 2
        var start = 0
        for (i: Int in 0..len - 2) {
            val j = i + 1
            if (s[i] == s[j]) {
                arr[i][j] = true
                start = i
                longestLength = 2
            }
        }

        // check for substrings >2

        var subLen = 2
        while (subLen <= len) {
            for (i: Int in 0..(len - subLen)) {
                val j = i + subLen - 1
                if (s[i] == s[j] && arr[i + 1][j - 1]) {
                    arr[i][j] = true
                    longestLength = subLen
                    start = i

                }
            }
            subLen++
        }


        return longestLength
    }




}