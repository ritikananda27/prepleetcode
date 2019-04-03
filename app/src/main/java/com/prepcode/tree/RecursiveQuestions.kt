package com.prepcode.tree

class RecursiveQuestions {

    fun reverseString(s: String) {
        printStr(s, 0)
    }

    private fun reverseSubString(s: String, start: Int) {

        if(s.isEmpty() || start>s.length-1){
            return
        }
        reverseSubString(s, start.plus(1))
        println(s[start])

    }

    private fun printStr(s:String, start: Int){
        if(s.isEmpty() || start>s.length-1){
            return
        }
        println(s[start])
        printStr(s, start.plus(1))
        println("pop{$start}")
    }

}