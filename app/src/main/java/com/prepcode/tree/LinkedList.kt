package com.prepcode.tree


/* Two things to remember
   * 1. if fp and sp meet linked list is circular
   * 2. when fp and sp meet, the distance of the meeting point from the beginning of the loop and the distance of the
    * beginning of the loop from the start of the list will be equal*/
class LinkedList {


    /*Question - group all nodes at odd positions followed by nodes at even positions
     * First node is considered odd */
    fun groupOddEvenNodes(head: ListNode?): ListNode? {

        if (head == null) return head
        var odd = head
        var even = head.next
        val evenHead = even

        while (even?.next != null) {
            odd?.next = even.next
            even.next = even.next?.next

            odd = odd?.next
            even = even.next

        }

        odd?.next = evenHead
        return head

    }

    fun isLinkedListCircular(head: ListNode?): ListNode? {

        if (head == null) return null
        var sp = head
        var fp = head

        while (true) {
            sp = sp?.next
            fp = fp?.next?.next

            if (sp == fp) {
                return detectLoopNode(sp!!, head)
            } else if (sp == null || fp == null || fp.next == null) {
                return null
            }

        }
    }

    fun detectLoopNode(circularHead: ListNode, head: ListNode): ListNode {
        var start = head
        var circular = circularHead

        while (true) {
            if (circular.equals(start)) {
                return circular
            }
            circular = circular.next!!
            start = start.next!!

        }
    }

    /*Question - Remove Nth TreeNodeT From End of List - keep a pointer n distance apart and remove when the second pointer is null - logic defined by me  */
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        if (head == null) return head

        var count = 1
        var start = head
        var curr = head
        while (count <= n) {
            if (curr != null) {
                curr = curr.next
            }
            count++
        }

        if (count < n) return null
        if (curr == null) {
            return start.next
        }

        while (curr?.next != null) {
            curr = curr?.next
            start = start?.next
        }


        start?.next = start?.next?.next

        return head
    }


    fun reverseList(head: ListNode?): ListNode? {
        var nhh: ListNode? = null
        var h: ListNode? = head
        while (h != null) {
            val next = h.next
            h.next = nhh
            nhh = h
            h = next
        }
        return nhh
    }

    fun reverseListRecursive(head: ListNode?): ListNode? {
        return reverseListRecursiveHelper(head, null)
    }

    private fun reverseListRecursiveHelper(oldListNode: ListNode?, newListNode: ListNode?): ListNode? {
        if (oldListNode == null) return null
        val next = oldListNode.next
        oldListNode.next = newListNode
        return reverseListRecursiveHelper(next, oldListNode)
    }


}