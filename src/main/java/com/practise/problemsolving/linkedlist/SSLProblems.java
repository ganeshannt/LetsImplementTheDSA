package com.practise.problemsolving.linkedlist;

import com.practise.commons.Node;
import com.practise.commons.Utils;
import com.practise.ds.linkedlist.SingleLinkedList;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SSLProblems {
    public static void main(String[] args) {
        List<Integer> inputList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Node output;
        Node solution;

        output = SingleLinkedList.create(inputList);

//        int k = 3;
//        Utils.printSingleLinkedList(output);
//        Utils.printSingleLinkedList(removeNodeFromNthEnd(output, k));
        SSLProblems.splitSLL(output);


    }


    /*
    Name - Merge Two Sorted Lists
    Link - https://leetcode.com/problems/merge-two-sorted-lists/
    Condition - Both list1 and list2 are sorted in non-decreasing order
    Time Complexity - O(n)
    Space Complexity - o(1)
    Note - make use of merge procedure of merge sort
    */

    public static Node mergeTwoSortedList(Node head1, Node head2) {
        // edge case - if one of the given list is null, it will return another one.
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        Node temp;
        Node mergeList;
        // Set correct head value to mergeList to keep track, and it will be used as head of  mergeList
        if (head1.value > head2.value) {
            mergeList = head2;
            head2 = head2.next;
        } else {
            mergeList = head1;
            head1 = head1.next;
        }

        temp = mergeList;

        while (head1 != null && head2 != null) {
            if (head1.value > head2.value) {
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

    private static Node removeNodeFromNthEnd(Node head, int k) {
        Node start = new Node(0);

        Node slow = start;
        Node fast = start;

        slow.next = head;

        //Move fast in front so that the gap between slow and fast becomes n
        for (int i = 1; i <= k + 1; i++) {
            fast = fast.next;
        }
        //Move fast to the end, maintaining the gap
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        //Skip the desired node
        slow.next = slow.next.next;
        return start.next;
    }


    /*
    Name - Remove Nth Node From End of List
    Link - https://leetcode.com/problems/remove-nth-node-from-end-of-list/
    Condition - Could you do this in one pass?
    Time Complexity - O(n)
    Space Complexity - o(1)
    Note - Fast and slow pointer technique
    */

    private static boolean detectLoop(Node head) {
        Node fast = head;
        Node slow = head;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                System.out.println("loop detected");
                return true;
            }
        }
        System.out.println("loop not detected");
        return false;
    }

    /*
    Name - Palindrome Linked List
    Link - https://leetcode.com/problems/palindrome-linked-list/
    Condition - Could you do it in O(n) time and O(1) space?
    Time Complexity - O(n)
    Space Complexity - o(1)
    Note - Fast and slow pointer technique
    */
    private static boolean palindromeInLinkedList(Node head) {
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
            if (fast.value == slow.value) {
                slow = slow.next;
                fast = fast.next;
            } else {
                return false;
            }
        }
        return true;
    }



    /*
    Name - Linked List Cycle
    Link - https://leetcode.com/problems/linked-list-cycle/
    Condition - Can you solve it using O(1) (i.e. constant) memory?
    Time Complexity - O(n)
    Space Complexity - o(1)
    Note - Fast and slow pointer technique
    */

    private static Node getIntersectionApproach1(Node headA, Node headB) {
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

    private static void splitSLL(Node head) {
        SingleLinkedList list1 = new SingleLinkedList();
        SingleLinkedList list2 = new SingleLinkedList();
        for (int i = 0; head != null; i++) {
            if (i % 2 == 0) {
                list1.insertNodeAtEnd(head.value);
            } else {
                list2.insertNodeAtEnd(head.value);
            }
            head = head.next;
        }
        Utils.printSingleLinkedList(list1.head);
        Utils.printSingleLinkedList(list2.head);
    }

    /*
    Name - Intersection of Two Linked Lists
    Link - https://leetcode.com/problems/intersection-of-two-linked-lists/
    Condition - Linked lists must retain their original structure after the function returns.
              - Could you write a solution that runs in O(m + n) time and use only O(1) memory?
    Time Complexity - O(m + n)
    Space Complexity - o(1)
    Note - Fast and slow pointer technique
    */

    private static Node addNumbers(Node first, Node second) {
        int quotient = 0;
        int remainder = 0;
        int sum = 0;
        Node head = null;
        Node temp = null;
        while (first != null || second != null) {
            sum = quotient + (((first != null) ? first.value : 0) + ((second != null) ? second.value : 0));
            remainder = sum % 10;
            quotient = sum / 10;
            Node newNode = new Node(remainder);
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
        if (quotient > 0) {
            Node newNode = new Node(quotient);
            temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = null;
        }
        return head;
    }

    private static Node reverse(Node head) {
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

    @Test
    public void testRemoveNodeFromNthEnd() {
        List<Integer> inputList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Node output, solution;

        int k = 3;
        output = SingleLinkedList.create(inputList);
        output = removeNodeFromNthEnd(output, k);

        inputList.remove(inputList.size() - k);
        solution = SingleLinkedList.create(inputList);
        Assertions.assertTrue(Utils.isLinkedListEqual(output, solution));

        inputList.clear();
        inputList.add(1);
        k = 1;
        output = SingleLinkedList.create(inputList);
        output = removeNodeFromNthEnd(output, k);

        inputList.remove(inputList.size() - k);
        solution = SingleLinkedList.create(inputList);
        Assertions.assertTrue(Utils.isLinkedListEqual(output, solution));
    }


    /*
    Name - Add Two Numbers
    Link - https://leetcode.com/problems/add-two-numbers/
    Time Complexity - O(m + n)
    Space Complexity - o(1)
    Note - make use of modulo (give remainder) and division (give quotient)
    */

    @Test
    public void testDetectLoop() {
        List<Integer> inputList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Node output, solution;

        int k = 3;
        output = SingleLinkedList.create(inputList);
        output = removeNodeFromNthEnd(output, k);

        inputList.remove(inputList.size() - k);
        solution = SingleLinkedList.create(inputList);
        Assertions.assertTrue(Utils.isLinkedListEqual(output, solution));
    }

    /*
     *  Genius solution
     * */
    private Node getIntersectionApproach2(Node headA, Node headB) {
        //boundary check
        if (headA == null || headB == null) return null;

        Node a = headA;
        Node b = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        while (a != b) {
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}
