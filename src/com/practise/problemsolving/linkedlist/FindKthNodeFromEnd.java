package com.practise.problemsolving.linkedlist;

import java.util.Stack;

public class FindKthNodeFromEnd {

    Node head;

    public class Node {
        Node next;
        int data;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public Node insert(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        return head;
    }

    private void approach1(Node head, int k) {
        System.out.println(head.data);
        Node p = head;
        Node q = head;
        for (int i = 0; i < k; i++) {
            q = q.next;
        }
        while (q != null) {
            p = p.next;
            q = q.next;
        }
        System.out.println(p.data);
    }

    private Node removeNodeFromNthEnd(Node head, int k) {
        Node p = new Node(0);
        p.next = head;
        Node q = head;
        Node dummy = p;
        for (int i = 0; i < k; i++) {
            q = q.next;
        }
        while (q != null) {
            p = p.next;
            q = q.next;
        }
        p.next = p.next.next;
        return dummy.next;
    }

    private void detectLoop(Node head) {
        Node fast = head;
        Node slow = head;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                System.out.println("loop detected");
            }
        }
        System.out.println("loop not detected");
    }

    private boolean palindromeInLinkedList(Node head) {
        if (head.next == null) {
            return true;
        }
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = reverse(slow);
        fast = head;
        while (slow != null) {
            if (fast.data == slow.data) {
                slow = slow.next;
                fast = fast.next;
            } else {
                return false;
            }
        }
        return true;
    }

    private Node isContainsIntersection(Node headA, Node headB) {
        Node head1 = headA;
        Node head2 = headB;
        int c1 = 0;
        int c2 = 0;
        while (head1 != null) {
            c1++;
            head1 = head1.next;
        }
        while (head2 != null) {
            c2++;
            head2 = head2.next;
        }
        int diff = Math.abs(c1 - c2);
        head1 = headA;
        head2 = headB;
        if (c1 > c2) {
            for (int i = 0; i < diff; i++) {
                head1 = head1.next;
            }
        } else {
            for (int i = 0; i < diff; i++) {
                head2 = head2.next;
            }
        }
        while (head1 != null && head2 != null) {
            if (head1 == head2) {
                return head1;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return null;
    }

    private Node reverse(Node head) {
        Node previous = null;
        Node current = head;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }

    public static void main(String[] args) {
        FindKthNodeFromEnd findKthNodeFromEnd = new FindKthNodeFromEnd();
        int arr[] = { 1, 2, 2, 2, 2, 1 };
        for (int i : arr) {
            findKthNodeFromEnd.head = findKthNodeFromEnd.insert(i);
        }
        // findKthNodeFromEnd.approach1(findKthNodeFromEnd.head, 3);
        // Node h = findKthNodeFromEnd.removeNodeFromNthEnd(findKthNodeFromEnd.head, 3);

        System.out.println(findKthNodeFromEnd.palindromeInLinkedList(findKthNodeFromEnd.head));
        // System.out.println(h.data);
    }

}
