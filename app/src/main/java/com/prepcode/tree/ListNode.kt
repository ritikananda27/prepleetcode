package com.prepcode.tree

data class ListNode(val value: Int, var next: ListNode?)

class ListNodeHelper() {

    private var n6 = ListNode(6, null)
    private var n5 = ListNode(5, n6)
    private var n4 = ListNode(4, n5)
    private var n3 = ListNode(3, n4)
    private var n2 = ListNode(2, n3)
    private var n1 = ListNode(1, n2)


    /* fun getBasicList1(): ListNode {
         return n1
     }

     fun getBasicList2(): ListNode {
         return n4
     }
 */

    fun getBasicList1(): ListNode {
        return n1
    }

    fun getBasicList2(): ListNode {
        return ListNode(5, null)
    }

    fun getCircularList(): ListNode {
        n5.next = n3
        return n1
    }

}
