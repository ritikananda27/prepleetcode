package com.prepcode.tree

class Search {

    fun binarySearch(arr: DoubleArray, num: Double): Boolean {
        val size = arr.size
        if (size == 0) {
            return false
        }
        val potentialFind: Double
        if (size == 1) {
            potentialFind = arr[0]
            return num == potentialFind
        }
        val indexToCheck = size / 2
        potentialFind = arr[indexToCheck]
        return if (potentialFind == num) {
            true
        } else {
            val newArr = DoubleArray(indexToCheck)
            if (num < potentialFind) {
                arr.copyInto(newArr, 0, 0, indexToCheck)
                binarySearch(newArr, num)
            } else {
                arr.copyInto(newArr, 0, indexToCheck, size)
                binarySearch(newArr, num)
            }
        }

    }

}


