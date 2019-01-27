package com.prepcode.tree

object MainClass {

    @JvmStatic
    fun main(args: Array<String>) {

        val sudoku = Array(9) { CharArray(9) { '.' } }
        sudoku[0][0] = '5'
        sudoku[0][1] = '3'
        sudoku[0][4] = '7'
        sudoku[1][0] = '6'
        sudoku[3][0] = '8'
        sudoku[4][0] = '4'
        sudoku[5][0] = '7'
        sudoku[2][1] = '9'
        sudoku[6][1] = '6'
        sudoku[1][3] = '1'


        MiscModerate().isValidSudoku(sudoku)


    }


}
