package com.practise.problemsolving.linkedlist;

public class SSLProblems {

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

    private void splitSLL(Node head) {
        Node list1;
        Node list2;
        for (int i = 0; head != null; i++) {
            if (i % 2 == 0) {
                list1 = insert(head.data);
            } else {
                list2 = insert(head.data);
            }
            head = head.next;
        }
    }

    private Node addNumbers(Node first, Node second) {
        int q = 0;
        int r = 0;
        int sum = 0;
        Node head = null;
        Node temp = null;
        while (first != null || second != null) {
            sum = q + (((first != null) ? first.data : 0) + ((second != null) ? second.data : 0));
            r = sum % 10;
            q = sum / 10;
            Node newNode = new Node(r);
            if (head == null) {
                head = newNode;
            } else {
                temp = head;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = newNode;
                newNode.next = null;
            }
            if (first != null) {
                first = first.next;
            }
            if (second != null) {
                second = second.next;
            }
        }
        // to cover the edge case like 900+901 = 1801 - here we have to create extra one
        // node to store last digit
        if (q > 0) {
            Node newNode = new Node(q);
            temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = null;
        }
        return head;
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

    static Node mergeTwoSortedList(Node head1, Node head2) {
        // edge case - if one of the given list is null, it will return another one.
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        Node temp;
        Node mergeList;
        // Set correct head value to mergeList to keep track and it will be used as head of  mergeList 
        if (head1.data > head2.data) {
            mergeList = head2;
            head2 = head2.next;
        } else {
            mergeList = head1;
            head1 = head1.next;
        }
        temp = mergeList;
        while (head1 != null && head2 != null) {
            if (head1.data > head2.data) {
                temp.next = head2;
                head2 = head2.next;
            } else {
                temp.next = head1;
                head1 = head1.next;
            }
            temp = temp.next;
        }
        // edge case - will execute when number nodes of two list is not equal
        if (head1 != null) {
            temp.next = head1;
        }
        if (head2 != null) {
            temp.next = head2;
        }
        return mergeList;
    }

    public static void main(String[] args) {
        SSLProblems findKthNodeFromEnd = new SSLProblems();
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
