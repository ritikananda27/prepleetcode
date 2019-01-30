package com.prepcode.tree

class MiscEasy() {

    /* Question - Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
    You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.*/

    fun firstBadVersion(n: Int): Int {

        if (n <= 0) {
            return 0
        }

        var lastGoodVersion = 1
        var firstBadVersion = n

        var currentVersion = n / 2
        var lastBad = 1

        while (currentVersion > lastGoodVersion && currentVersion < firstBadVersion) {

            if (isBadVersion(currentVersion)) {
                lastBad = currentVersion
                firstBadVersion = currentVersion
                currentVersion = (firstBadVersion + lastGoodVersion) / 2
            } else {
                lastGoodVersion = currentVersion
                currentVersion = currentVersion + (firstBadVersion - lastGoodVersion) / 2
            }


        }

        if (lastBad < firstBadVersion && isBadVersion(firstBadVersion)) {
            return firstBadVersion
        } else {
            return lastBad
        }

    }

    private fun isBadVersion(i: Int): Boolean {
        return i == 1
    }
}