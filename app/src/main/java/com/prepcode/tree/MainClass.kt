package com.prepcode.tree

object MainClass {

    @JvmStatic
    fun main(args: Array<String>) {
        val arr = intArrayOf(-1,0,1,2,-1,-4)
        val test = LinkedList().removeNthFromEnd(ListNodeHelper().getBasicList1(),2)

        println(test?.value)
    }


}
