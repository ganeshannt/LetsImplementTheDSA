package com.practise.ds.linkedlist;

import com.practise.commons.Node;
import com.practise.commons.Utils;

import java.util.List;
import java.util.Objects;

public class SingleLinkedList {

    public Node head, tail;
    private int size;

    public SingleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();

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

    public static Node create(int arr[]) {
        if (arr.length == 0) return null;
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        for (int i : arr) {
            singleLinkedList.insertNodeAtEnd(i);
        }
        return singleLinkedList.head;
    }

    public static Node create(List<Integer> arrList) {
        if (arrList.isEmpty()) return null;
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        for (int i : arrList) {
            singleLinkedList.insertNodeAtEnd(i);
        }
        return singleLinkedList.head;
    }

    // Utility functions
    private boolean isEmpty() {
        return head == null;
    }

    private int linkedListSize() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SingleLinkedList)) return false;
        SingleLinkedList that = (SingleLinkedList) o;
        if (size == that.size && head.equals(that.head) && tail.equals(that.tail)) return false;
        Node temp1 = head;
        Node temp2 = that.head;
        while (temp1.next != null) {
            if (!temp1.equals(that)) return false;
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, tail, size);
    }
}
