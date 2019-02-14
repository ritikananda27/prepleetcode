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

    fun addToArrayForm(A: IntArray, K: Int): List<Int> {

        val intArr = K.toString().toCharArray()
        val k = IntArray(intArr.size)


        for (i: Int in 0 until intArr.size) {
            k[i] = intArr[i].toString().toInt()
        }

        val aSize = A.size
        val kSize = k.size

        val resList = mutableListOf<Int>()

        if (aSize > kSize) {
            addLists(A, k, resList)
        } else {
            addLists(k, A, resList)
        }

        resList.reverse()
        return resList

    }

    private fun addLists(longerList: IntArray, shortList: IntArray, resList: MutableList<Int>) {
        var shortListPointer = shortList.size - 1
        var carry = 0
        for (i: Int in longerList.size - 1 downTo 0) {
            var sum: Int
            if (shortListPointer >= 0) {
                val var1 = longerList[i]
                val var2 = shortList[shortListPointer]
                sum = var1 + var2 + carry
                shortListPointer--
            } else {
                sum = longerList[i] + carry
            }

            if (sum > 9) {
                sum %= 10
                carry = 1
            } else {
                carry = 0

            }
            resList.add(sum)
            if (i == 0 && carry > 0) {
                resList.add(carry)
            }
        }
    }


    /*Question */
    fun equationsPossible(equations: Array<String>): Boolean {
        val parent = IntArray(26);

        for (i: Int in 0 until 26) {
            parent[i] = i; //init union-find
        }


        equations.forEach {
            if (it[1] == '=') {
                val i = it[0] - 'a'
                val j = it[3] - 'a'
                union(parent, i, j)
            }
        }


        equations.forEach {
            if (it[1] == '!') {
                val i = it[0] - 'a'
                val j = it[3] - 'a'
                if (find(parent, i) == find(parent, j)) {
                    return false;
                }
            }
        }
        return true;

    }


    private fun union(parent: IntArray, i: Int, j: Int) {
        val x = find(parent, i)
        val y = find(parent, j)
        if (x != y) {
            parent[x] = y
        }
    }

    private fun find(parent: IntArray, i: Int): Int {
        if (parent[i] == i) return i
        parent[i] = find(parent, parent[i]) //path compression
        return parent[i]
    }


    fun equationsPossibleMine(equations: Array<String>): Boolean {

        val valueHolder = IntArray(26)

        for (i: Int in 0 until valueHolder.size) {
            valueHolder[i] = i
        }

        equations.forEach {
            if (it[1] == '=') {
                val i = it[0] - 'a'
                val j = it[3] - 'a'
                myUnion(valueHolder, myFind(valueHolder, i), myFind(valueHolder, j))
            }
        }

        equations.forEach {
            if (it[1] == '!') {
                val i = it[0] - 'a'
                val j = it[3] - 'a'

                if (find(valueHolder, i) == find(valueHolder, j)) {
                    return false
                }
            }
        }

        return true
    }

    private fun myFind(arr: IntArray, i: Int): Int {
        if (arr[i] == i) {
            return arr[i]
        }
        return myFind(arr, arr[i])
    }

    private fun myUnion(arr: IntArray, i: Int, j: Int) {
        if (i != j) {
            arr[i] = arr[j]
        }
    }

}