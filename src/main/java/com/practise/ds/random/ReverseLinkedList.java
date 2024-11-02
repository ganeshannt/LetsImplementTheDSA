package com.practise.ds.random;

import com.practise.commons.SLLNode;
import com.practise.commons.Utils;

/**
 * @author Ganeshan Nagarajan
 * @since 09-04-2022
 */

public class ReverseLinkedList {

    private SLLNode head;

    public ReverseLinkedList() {
        this.head = null;
    }

    public static void main(String[] args) {

        ReverseLinkedList list = new ReverseLinkedList();
        list.addNode(10);
        list.addNode(20);
        list.addNode(30);
        list.addNode(40);
        list.addNode(50);
        Utils.printSingleLinkedList(list.head);
        list.reverse();
        Utils.printSingleLinkedList(list.head);
        list.reverse();
        Utils.printSingleLinkedList(list.head);
    }

    public void addNode(int value) {
        SLLNode SLLNode = new SLLNode(value);
        if (head == null) {
            head = SLLNode;
            return;
        }
        SLLNode.next = head;
        head = SLLNode;
    }

    private void reverse() {
        if (head == null || head.next == null)
            return;

        SLLNode previous = null;
        SLLNode current = head;
        SLLNode next = null;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;
    }

}
