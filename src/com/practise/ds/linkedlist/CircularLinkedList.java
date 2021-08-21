package com.practise.ds.linkedlist;

public class CircularLinkedList {

    private Node head, tail;
    private int size;

    public CircularLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    class Node {
        Node next;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }
}
