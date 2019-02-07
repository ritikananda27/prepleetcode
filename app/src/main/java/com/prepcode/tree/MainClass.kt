package com.prepcode.tree

object MainClass {

    @JvmStatic
    fun main(args: Array<String>) {
        val testList = mutableListOf<Int>()
        MiscModerate().serialize(Tree().getBasicTree(), testList)
        MiscModerate().deserializeTree(testList)


    }


    // Helpers
    private fun get2DArray(): Array<CharArray> {
        /*val sudoku = Array(3) { CharArray(3) }
        sudoku[0][0] = 'A'
        sudoku[0][1] = 'B'
        sudoku[0][2] = 'C'
        sudoku[1][0] = 'F'
        sudoku[1][1] = 'E'
        sudoku[1][2] = 'D'
        sudoku[2][0] = 'G'
        sudoku[2][1] = 'H'
        sudoku[2][2] = 'I'

        return sudoku*/


        val test = Array(1) { CharArray(2) }

        test[0][0] = 'a'
        test[0][1] = 'b'

        return test


    }

    private fun get2DIntArray(): Array<IntArray> {
        val arr = Array(3) { IntArray(3) }

        arr[0][0] = 1
        arr[0][1] = 1
        arr[0][2] = 1
        arr[1][0] = 1
        arr[1][1] = 1
        arr[1][2] = 1
        arr[2][0] = 1
        arr[2][1] = 1
        arr[2][2] = 1

        return arr
    }


    /*Points to remember */

    /*1. when you are mutating a strincture like a list do not use a pointer to navigate as it will change unexpected. Always use the condition like while (list.size>0) as
     the list should keep reducing in size
     2. Think about sorting for problems like anagrams */

}
