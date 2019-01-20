package com.prepcode.tree

class Sorting {


    fun medianSortedArraysOn(nums1: IntArray, nums2: IntArray): Double {

        val size1 = nums1.size
        val size2 = nums2.size

        var arr1Pointer = 0
        var arr2Pointer = 0
        var arrResPointer = 0
        val resArray = IntArray(size1 + size2)

        while (arr1Pointer < size1 && arr2Pointer < size2) {
            if (nums1[arr1Pointer] < nums2[arr2Pointer]) {
                resArray[arrResPointer] = nums1[arr1Pointer]
                arr1Pointer++
            } else {
                resArray[arrResPointer] = nums2[arr2Pointer]
                arr2Pointer++
            }
            arrResPointer++
        }

        if (arr1Pointer < size1) {
            while (arr1Pointer < size1) {
                resArray[arrResPointer] = nums1[arr1Pointer]
                arrResPointer++
                arr1Pointer++
            }
        }

        if (arr2Pointer < size2) {
            while (arr2Pointer < size2) {
                resArray[arrResPointer] = nums2[arr2Pointer]
                arrResPointer++
                arr2Pointer++
            }
        }

        val resultArrSize = resArray.size
        val median: Double
        if (resultArrSize % 2 == 0) {
            val medianIndex = resultArrSize / 2
            val median1 = resArray[medianIndex - 1].toDouble()
            val median2 = resArray[medianIndex].toDouble()
            median = (median1 + median2) / 2
        } else {
            val medianIndex = resultArrSize / 2
            median = resArray[medianIndex].toDouble()
        }

        return median
    }


    //    revisit this - find the median of 2 sorted arrays if they were combined into one in log (n) complexity
    fun medianSortedArraysOlogn(nums1: IntArray, nums2: IntArray): Int {

        val size1 = nums1.size
        val size2 = nums2.size
        val totalSize = (size1 + size2) / 2


        return findMedian(nums1, size1 / 2, nums2, totalSize - size2 / 2)
    }

    private fun findMedian(numsx: IntArray, cutoff1: Int, numsy: IntArray, cuttoff2: Int): Int {
        val median: Int
        val subArrx1 = IntArray(cutoff1)
        val subArrx2 = IntArray((numsx.size - subArrx1.size))
        val subArry1 = IntArray(cuttoff2)
        val subArry2 = IntArray(numsy.size - subArry1.size)

        numsx.copyInto(subArrx1, 0, 0, cutoff1)
        if (cutoff1 + 1 <= numsx.size) {
            numsx.copyInto(subArrx2, 0, cutoff1, numsx.size)
        }

        numsy.copyInto(subArry1, 0, 0, cuttoff2)
        if (cuttoff2 + 1 <= numsy.size) {
            numsy.copyInto(subArry2, 0, cuttoff2, numsy.size)
        }

        // numbers to compare
        val xleftmax = subArrx1[subArrx1.size - 1]
        val xrightmin = subArrx2[0]
        val yleftmax = subArry1[subArry1.size - 1]
        val yrightmin = subArry2[0]

        if ((xleftmax < yrightmin) && (yleftmax < xrightmin)) {
            median = (maxOf(xleftmax, yleftmax) + minOf(xrightmin, yrightmin)) / 2
            return median
        } else if (xleftmax > yrightmin) {
            return findMedian(numsx, cutoff1 - 1, numsy, cuttoff2 + 1)
        } else {
            return findMedian(numsx, cutoff1 + 1, numsy, cuttoff2 - 1)
        }

    }
}
