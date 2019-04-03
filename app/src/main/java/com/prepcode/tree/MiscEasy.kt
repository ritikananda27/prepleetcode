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


    fun mostCommonWord(paragraph: String, banned: Array<String>): String {

        var res = ""
        var resCount = 0

        val bannedList = banned.asList()
        val para = paragraph.split(' ')
        val map = HashMap<String, Int>()

        for (word in para) {
            var w = word.toLowerCase()
            if (w.length > 0) {
                if (!Character.isLetter(w[0])) {
                   w = w.substring(1)
                }
                if (!Character.isLetter(w[w.length - 1])) {

                    w = w.substring(0,w.length - 1)
                }

                if (!bannedList.contains(w)) {
                    if (map.containsKey(w)) {
                        map[w] = map[w]!!.plus(1)
                    } else {
                        map[w] = 1
                    }

                    if (map[w]!! > resCount) {
                        res = w
                        resCount = map[w]!!
                    }
                }
            }
        }

        return res
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

    fun findDisappearedNumbers(nums: IntArray): List<Int> {
        val resList = mutableListOf<Int>()
        for (i in 0 until nums.size) {
            val index = Math.abs(nums[i]) - 1
            if (nums[index] > 0) {
                nums[index] = nums[index] * -1
            }
        }
        for (n in nums) {
            if (n > 0) {
                resList.add(nums.indexOf(n) + 1)
            }
        }

        return resList

    }

    fun repeatedSubstringPattern(s: String): Boolean {
        if (s.length <= 1) return true
        val boolArr = BooleanArray(26)
        for (i in 0 until s.length) {
            val c = s[i]
            if (!boolArr[c - 'a']) {
                boolArr[c - 'a'] = true
            } else {
                val sub = s.substring(0, i)
                if (testSubString(sub, s)) {
                    return true
                }
            }
        }
        return false
    }

    private fun testSubString(subStr: String, mainStr: String): Boolean {
        var sub = subStr


        while (sub.length <= mainStr.length && mainStr.contains(subStr)) {
            if (sub == mainStr) {
                return true
            }
            sub = sub + subStr
        }
        return false

    }

    fun twoSum(nums: IntArray, target: Int): IntArray {
        val resArr = IntArray(2)
        for (i in 0 until nums.size) {
            val first = nums[i]
            val neededSecond = target - first
            if (nums.contains(neededSecond)) {
                resArr[0] = i
                resArr[1] = nums.indexOf(neededSecond)
            }
        }
        return resArr
    }


    fun isLongPressedName(name: String, typed: String): Boolean {

        if (name == typed) return true
        var res = true
        val map = HashMap<Char, Int>()

        for (i in 0 until typed.length) {
            val c = typed[i]
            if (map.contains(c)) {
                map[c] = map[c]!!.plus(1)
            } else {
                map[c] = 1
            }
        }

        for (i in 0 until name.length) {
            val c = name[i]
            if (map.contains(c)) {
                map[c] = map[c]!!.minus(1)
                if (map[c] == 0) {
                    map.remove(c)
                }
            } else {
                res = false
                break
            }
        }

        return res

    }

    enum class Move(val v: Int, val c: Char) {
        U(1, 'U'),
        D(-1, 'D'),
        L(2, 'L'),
        R(-2, 'R')
    }

    fun judgeCircle(moves: String): Boolean {
        var sum = 0
        for (c in moves) {

            when (c) {
                Move.U.c -> {
                    sum = sum + Move.U.v
                }
                Move.D.c -> {
                    sum = sum + Move.D.v
                }
                Move.L.c -> {
                    sum = sum + Move.L.v
                }
                Move.R.c -> {
                    sum = sum + Move.R.v
                }
            }
        }

        return sum == 0


    }

    fun findShortestSubArray(nums: IntArray): Int {
        if (nums.isEmpty()) return 0

        val map = HashMap<Int, MutableList<Int>>()
        for (i in 0 until nums.size) {
            if (map.contains(nums[i])) {
                map[nums[i]]!!.add(i)
            } else {
                map[nums[i]] = mutableListOf(i)
            }
        }

        val sortedMap = map.toList().sortedByDescending { it.second.size }.toMap<Int, List<Int>>()
        val degree = sortedMap.entries.first().value.size
        val resMap = HashMap<Int, List<Int>>()
        for (e in sortedMap) {
            if (e.value.size == degree) {
                resMap[e.key] = e.value
            } else {
                break
            }
        }
        var resEntry = resMap.entries.first()
        if (resMap.size > 1) {
            for (e in resMap) {
                val v = e.value
                val r = resEntry.value
                if ((v[v.size - 1] - v[0]) < r[r.size - 1] - r[0]) run {
                    resEntry = e
                }
            }
        }

        val li = resEntry.value

        return (li[li.size - 1] - li[0]) + 1
    }


    fun shortestCompletingWord(licensePlate: String, words: Array<String>): String {

        val charList = mutableListOf<Char>()
        for (c in licensePlate) {
            val t = c.toLowerCase()
            if (t >= 'a' && t <= 'z') {
                charList.add(c.toLowerCase())
            }
        }

        words.sortBy { selector(it) }
        var res: String = ""
        if (charList.size > 0) {
            for (word in words) {
                if (canListBeFormed(charList, word)) {
                    res = word
                    break
                }
            }
        } else {
            res = words[0]
        }

        return res

    }

    fun selector(s: String): Int = s.length

    private fun canListBeFormed(s1: List<Char>, s2: String): Boolean {
        val countArr = IntArray(26)
        for (c in s2) {
            val index = c - 'a'
            countArr[index] = countArr[index] + 1
        }

        for (c in s1) {
            val index = c - 'a'
            if (countArr[index] == 0) {
                return false
            } else {
                countArr[index] = countArr[index] - 1
            }
        }

        return true

    }




}