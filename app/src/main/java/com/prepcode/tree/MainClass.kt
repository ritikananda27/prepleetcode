package com.prepcode.tree

object MainClass {

    @JvmStatic
    fun main(args: Array<String>) {

        val test = MiscEasy().firstBadVersion(2)
        println(test)

    }

    // Helpers
    private fun get2DArray(): Array<CharArray> {
        val sudoku = Array(3) { CharArray(3) }
        sudoku[0][0] = 'A'
        sudoku[0][1] = 'B'
        sudoku[0][2] = 'C'
        sudoku[1][0] = 'F'
        sudoku[1][1] = 'E'
        sudoku[1][2] = 'D'
        sudoku[2][0] = 'G'
        sudoku[2][1] = 'H'
        sudoku[2][2] = 'I'

        return sudoku
    }


}
