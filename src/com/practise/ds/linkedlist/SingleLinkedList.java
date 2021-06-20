package com.practise.ds.linkedlist;

import com.practise.PrintOutput;

public class SingleLinkedList {

    public static Node head, tail;
    private static int size = 0;

    class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

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
            node = tail;
            size++;
        }
    }

    public void insertAtSomePoint(int point,int value){
        Node node = new Node(value);
        if(isEmpty())
            System.err.println("Given point isn't found");
        
        while(head!=null){
            if(head.value == point){
                
            }
        }
    }

    // Utility functions
    private boolean isEmpty() {
        return head == null;
    }

    private int linkedListSzie() {
        return size;
    }

    private boolean isContains(int value){
        if(isEmpty())
            return false;
        
        Node node = head;
        while(node.next !=null){
            if(node.value == value)
                return true;
            node = node.next;
        }
        return false;
    }

    public static void printLinkedList(Node head) {
        int i = 0;
        while (head != null) {
            System.out.println(i + " -> " + head.value);
            head = head.next;
            i++;
        }
    }

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.insertNodeAtFront(10);
        singleLinkedList.insertNodeAtFront(20);
        singleLinkedList.insertNodeAtFront(30);
        singleLinkedList.insertNodeAtFront(40);
        singleLinkedList.insertNodeAtEnd(50);
        printLinkedList(head);
    }
}
