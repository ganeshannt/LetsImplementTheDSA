package com.practise.random;

import com.practise.CommonUtils;
import com.practise.ds.linkedlist.Node;

/**
 * @author Ganeshan Nagarajan
 * @since 09-04-2022
 */

public class ReverseLinkedList {

    private Node head;

    public ReverseLinkedList() {
        this.head = null;
    }

    public void addNode(int value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
            return;
        }
        node.next = head;
        head = node;
    }

    private void reverse() {
        if (head == null || head.next == null)
            return;

        Node previous = null;
        Node current = head;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;
    }

    public static void main(String[] args) {

        ReverseLinkedList list = new ReverseLinkedList();
        list.addNode(10);
        list.addNode(20);
        list.addNode(30);
        list.addNode(40);
        list.addNode(50);
        CommonUtils.printSingleLinkedList(list.head);
        list.reverse();
        CommonUtils.printSingleLinkedList(list.head);
        list.reverse();
        CommonUtils.printSingleLinkedList(list.head);
    }

}
