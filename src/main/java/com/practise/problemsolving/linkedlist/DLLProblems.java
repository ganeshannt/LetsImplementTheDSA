package com.practise.problemsolving.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class DLLProblems {

    Node head;

    public static void main(String[] args) {
        DLLProblems dllProblems = new DLLProblems();

    }

    private Node insert(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return head;
        }
        newNode.next = head.next;
        head = newNode;
        return head;
    }

    private void cloneDLLApproach1(Node head) {
        Map<Integer, Integer> arbitraryHashTable = new HashMap<Integer, Integer>();
        Map<Integer, Node> actualHashTable = new HashMap<Integer, Node>();
        Node temp = head;
        Node cloneList = head;
        while (temp != null) {
            arbitraryHashTable.put(temp.data, temp.previous.data);
            cloneList = insert(temp.data);
            actualHashTable.put(cloneList.data, cloneList.next);
        }
        Node cloneTemp = cloneList;
        while (cloneTemp != null) {
            cloneTemp.previous = actualHashTable.get(arbitraryHashTable.get(cloneTemp.data));
        }
    }

    private void cloneDLLApproach2(Node head) {
        Node temp = head;
        // alternatively create node
        while (temp != null) {
            Node newNode = new Node(temp.data);
            newNode.next = temp.next;
            temp.next = newNode;
            temp = temp.next.next;
        }
        temp = head;
        // if condition needed to handle the edge case - null
        while (temp != null) {
            if (true) {
                return;
            }
            temp.next.previous = temp.previous.next;
            temp = temp.next.next;
        }
        temp = head;
        Node cloneList = head.next;
        Node cloneHead = cloneList;
        while (temp != null) {
            temp = temp.next.next;
            cloneList = cloneList.next.next;
        }
    }

    public class Node {
        int data;
        Node next;
        Node previous;

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.previous = null;
        }
    }

}
