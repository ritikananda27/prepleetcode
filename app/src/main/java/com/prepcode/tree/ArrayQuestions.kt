package com.prepcode.tree

class ArrayQuestions {

    fun productExceptSelf(nums: IntArray): IntArray {
        val result = IntArray(nums.size)
        var numberOfZeros = 0
        var indexZero = -1
        var totalmultiplication = 1
        for (i in 0 until nums.size) {
            val numberToMultiply = nums[i]
            if (numberToMultiply == 0) {
                if (numberOfZeros > 1) {
                    totalmultiplication = 0
                    break
                } else {
                    numberOfZeros++
                    indexZero = i
                }
            } else {
                totalmultiplication *= nums[i]
            }
        }


        if (numberOfZeros > 1) {
            for (i in 0 until nums.size) {
                result[i] = 0
            }
        } else if (numberOfZeros == 1) {
            for (i in 0 until nums.size) {
                if (i != indexZero) {
                    result[i] = 0
                } else {
                    result[i] = totalmultiplication
                }
            }
        } else {
            for (i in 0 until nums.size) {
                result[i] = totalmultiplication / nums[i]
            }
        }



        return result
    }

    fun test(nums: IntArray) {
        val result = IntArray(nums.size)

        var i = 0
        var tmp = 1
        while (i < nums.size) {
            result[i] = tmp
            tmp = tmp * nums[i]
            i++
        }

        var j = nums.size - 1
        var tmp1 = 1
        while (j >= 0) {
            result[j] *= tmp1
            tmp1 *= nums[j]
            j--
        }
        println(result.size)
    }

}