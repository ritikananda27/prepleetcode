package com.prepcode.tree

import android.os.Build
import android.support.annotation.RequiresApi

object MainClass {


    @RequiresApi(Build.VERSION_CODES.N)
    @JvmStatic
    fun main(args: Array<String>) {

        val q = InfiniteQueue<Int>()
        q.enqueue(1)
        q.enqueue(2)
        q.enqueue(3)
        q.enqueue(4)
        q.enqueue(5)
        q.enqueue(6)
        q.enqueue(7)
        q.enqueue(8)

        println( q.dequeue())
        println( q.dequeue())
        println( q.dequeue())


        q.enqueue(9)
        q.enqueue(10)
        q.enqueue(11)
        q.enqueue(12)
        q.enqueue(13)

        println( q.dequeue())
        println( q.dequeue())
        println( q.dequeue())

        println( q.dequeue())
        println( q.dequeue())
        println( q.dequeue())

        println( q.dequeue())
        println( q.dequeue())
        println( q.dequeue())



    }


    // Helpers'T

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
        val arr = Array(5) { IntArray(5) }

        arr[0][0] = 1
        arr[0][1] = 4
        arr[0][2] = 7
        arr[0][3] = 11
        arr[0][4] = 15

        arr[1][0] = 2
        arr[1][1] = 5
        arr[1][2] = 8
        arr[1][3] = 12
        arr[1][4] = 19

        arr[2][0] = 3
        arr[2][1] = 6
        arr[2][2] = 9
        arr[2][3] = 16
        arr[2][4] = 22

        arr[3][0] = 10
        arr[3][1] = 13
        arr[3][2] = 14
        arr[3][3] = 17
        arr[3][4] = 24

        arr[4][0] = 18
        arr[4][1] = 21
        arr[4][2] = 23
        arr[4][3] = 26
        arr[4][4] = 30


        return arr
    }


    /*Points to remember */

    /*1. when you are mutating a strincture like a list do not use a pointer to navigate as it will change unexpected. Always use the condition like while (list.size>0) as
     the list should keep reducing in size
     2. Think about sorting for problems like anagrams */

}

/*Helper classes */

