package com.practise.ds.linkedlist.impl;

import com.practise.commons.DLLNode;
import com.practise.commons.Utils;

public class DoubleLinkedList {

    private DLLNode head;
    private DLLNode tail;
    private int size;

    public DoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.insertNodeAtFront(10);
        doubleLinkedList.insertNodeAtFront(20);
        doubleLinkedList.insertNodeAtFront(30);
        doubleLinkedList.insertNodeAtFront(40);
        doubleLinkedList.delete(30);
        Utils.printDoubleLinkedList(doubleLinkedList.head);
    }

    // Utility functions
    public boolean isEmpty() {
        return head == null;
    }

    public int LinkedListSize() {
        return size;
    }

    public boolean isContains(int value) {
        DLLNode node = head;
        while (node.next != null) {
            if (node.value == value)
                return true;
            node = node.next;
        }
        return false;
    }

    public void printDoubleLinkedList() {
        DLLNode node = head;
        int i = 0;
        while (node != null) {
            System.out.println(i + " -> " + node.value);
            node = node.next;
            i++;
        }
    }

    /************************************************************** */
    // Insert operation
    public void insertNodeAtFront(int value) {
        DLLNode node = new DLLNode(value);
        if (isEmpty()) {
            head = tail = node;
            return;
        }
        node.next = head;
        node.next.previous = node;
        head = node;
        size++;
    }

    public void insertNodeAtEnd(int value) {
        DLLNode node = new DLLNode(value);
        if (isEmpty()) {
            head = tail = node;
            return;
        }
        tail.next = node;
        node.previous = tail;
        tail = node;
        size++;
    }

    public void insertNodeAtSomePoint(int point, int value) {
        if (isEmpty()) {
            System.out.println("Empty list");
            return;
        }

        if (head.value == point) {
            insertNodeAtFront(value);
            return;
        }

        if (tail.value == point) {
            insertNodeAtEnd(value);
            return;
        }

        DLLNode node = new DLLNode(value);
        DLLNode temp = head;

        while (temp.next != null) {
            if (temp.next.value == point) {
                node.next = temp.next;
                node.next.previous = node;
                temp.next = node;
                node.previous = head;
                size++;
                return;
            }
            temp = temp.next;
        }
        System.err.println("Given element is not found");
    }

    /************************************************************** */
    // delete operation
    public void deleteAtFront() {
        if (isEmpty()) {
            System.out.println("Empty list");
            return;
        }
        head.next.previous = null;
        head = head.next;
        size--;
    }

    public void deleteAtEnd() {
        if (isEmpty()) {
            System.out.println("Empty list");
            return;
        }
        tail.previous.next = null;
        tail = tail.previous;
        size--;
    }

    public void delete(int point) {
        if (isEmpty()) {
            System.out.println("Empty list");
            return;
        }
        if (head.value == point) {
            deleteAtFront();
            return;
        }
        if (tail.value == point) {
            deleteAtEnd();
            return;
        }

        DLLNode temp = head;
        while (temp.next != null) {
            if (temp.next.value == point) {
                temp.next = temp.next.next;
                temp.next.previous = temp;
                size--;
                return;
            }
            temp = temp.next;
        }
        System.err.println("Element is not found");
    }
}