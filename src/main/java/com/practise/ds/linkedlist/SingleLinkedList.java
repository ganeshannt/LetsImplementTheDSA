package com.practise.ds.linkedlist;

import com.practise.commons.Node;
import com.practise.commons.Utils;

public class SingleLinkedList {

    public Node head, tail;
    private int size;

    public SingleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }


    // Utility functions
    private boolean isEmpty() {
        return head == null;
    }

    private int linkedListSzie() {
        return size;
    }

    private boolean isContains(int value) {
        if (isEmpty())
            return false;

        Node node = head;
        while (node.next != null) {
            if (node.value == value)
                return true;
            node = node.next;
        }
        return false;
    }

    /***********************************************************/

    public void insertNodeAtFront(int value) {
        Node node = new Node(value);
        if (isEmpty()) {
            head = tail = node;
        } else {
            node.next = head;
            head = node;
            size++;
        }
    }

    public void insertNodeAtEnd(int value) {
        Node node = new Node(value);
        if (isEmpty()) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
            size++;
        }
    }

    public void insertAtSomePoint(int point, int value) {
        Node node = new Node(value);
        if (isContains(value)) {
            System.err.println("Given element isn't found");
            return;
        }

        if (tail.value == point) {
            insertNodeAtEnd(value);
            return;
        }

        Node temp = head;
        while (temp != null) {
            if (temp.value == point && temp.next != null) {
                node.next = temp.next;
                temp.next = node;
                size++;
                break;
            }
            temp = temp.next;
        }
    }

    public void delete(int value) {
        if (!isContains(value)) {
            System.err.println("Given element isn't found");
            return;
        }

        Node node = head;
        while (node.next != null) {
            if (node.next.value == value) {
                node.next = node.next.next;
                size--;
                break;
            }
            node = node.next;
        }
    }

    public void deleteAtFront() {
        if (isEmpty()) {
            System.err.println("Empty List");
            return;
        }
        if (head.next.next != null) {
            head.next = head.next.next;
            size--;
            return;
        }
        head.next = null;
    }

    public void deleteAtEnd() {
        if (isEmpty()) {
            System.err.println("Empty List");
            return;
        }
        // condition to handle single node in linkedlist
        if (head == tail) {
            head = tail = null;
            return;
        }
        Node node = head;
        // go to previous node of last node
        while (node.next.next != null) {
            node = node.next;
        }
        tail = node.next;
        node.next = null;
        size--;
    }


    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.insertNodeAtFront(10);
        singleLinkedList.insertNodeAtFront(20);
        singleLinkedList.insertNodeAtFront(30);
        singleLinkedList.insertNodeAtFront(40);
        singleLinkedList.insertNodeAtEnd(50);
        singleLinkedList.insertNodeAtEnd(60);
        singleLinkedList.insertAtSomePoint(10, 90);
        singleLinkedList.insertAtSomePoint(90, 80);
        singleLinkedList.insertAtSomePoint(40, 100);
        singleLinkedList.insertAtSomePoint(60, 110);
        // singleLinkedList.delete(90);
        singleLinkedList.deleteAtEnd();
        Utils.printSingleLinkedList(singleLinkedList.head);
    }
}
