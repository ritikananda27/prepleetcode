package com.prepcode.tree;

import java.util.ArrayList;
import java.util.List;

public class InfiniteQueue<T> {

    private class Node {

        List<T> objectList;
        Node next;
        Node previous;

        Node(List<T> inputList) {
            this.objectList = inputList;
            this.next = null;
            this.previous = null;
        }
    }


    private Node head;
    private Node tail;


    public void enqueue(T t) {

        if (head == null) {
            head = createNewHead(t);
        } else {
            List<T> lastInputList = head.objectList;
            if (lastInputList.size() == 5) {
                Node newHead = createNewHead(t);
                Node temp = head;
                temp.next = newHead;
                head = newHead;
                head.previous = temp;
            } else {
                lastInputList.add(t);
            }
        }

        if (tail == null) {
            tail = head;
        }

    }


    public T dequeue() {
        if (tail != null) {
            List<T> objectList = tail.objectList;
            if (objectList != null && !objectList.isEmpty()) {
                T val = objectList.get(0);
                objectList.remove(0);
                if (objectList.size() == 0) {
                    tail = tail.next;
                    if (tail != null) {
                        tail.previous = null;
                    }
                }
                return val;
            }
        }
        return null;
    }


    private Node createNewHead(T t) {
        List<T> list = new ArrayList<>();
        list.add(t);
        return new Node(list);
    }
}
