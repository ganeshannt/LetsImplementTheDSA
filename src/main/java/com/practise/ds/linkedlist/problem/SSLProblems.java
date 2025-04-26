package com.practise.ds.linkedlist.problem;

import com.practise.commons.SLLNode;
import com.practise.commons.Utils;
import com.practise.ds.linkedlist.impl.SingleLinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.practise.ds.linkedlist.problem.SSLProblems.mergeTwoSortedLists;

public class SSLProblems {

    /*
    Name - Merge Two Sorted Lists
    Link - https://leetcode.com/problems/merge-two-sorted-lists/
    Condition - Both lists are sorted in non-decreasing order
    Time Complexity - O(m + n), where m and n are the lengths of the two lists
    Space Complexity - O(1), as the solution only uses a few pointers for merging
    Note - This is a variation of the merge procedure used in merge sort, combining two sorted linked lists into one.
    */
    public static SLLNode mergeTwoSortedLists(SLLNode head1, SLLNode head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;

        SLLNode dummy = new SLLNode(0); // Placeholder to simplify merging
        SLLNode tail = dummy;

        while (head1 != null && head2 != null) {
            if (head1.value <= head2.value) {
                tail.next = head1;
                head1 = head1.next;
            } else {
                tail.next = head2;
                head2 = head2.next;
            }
            tail = tail.next;
        }

        tail.next = (head1 != null) ? head1 : head2; // Attach any remaining nodes
        return dummy.next;
    }

    /*
    Name - Remove Nth SLLNode From End of List
    Link - https://leetcode.com/problems/remove-nth-node-from-end-of-list/
    Condition - The list contains at least n nodes
    Time Complexity - O(n), where n is the length of the linked list
    Space Complexity - O(1), as we only use a few pointers for tracking positions
    Note - Uses the two-pointer technique (fast and slow pointers) to achieve a one-pass solution
    */
    public SLLNode removeNthFromEnd(SLLNode head, int n) {
        SLLNode dummy = new SLLNode(0); // Dummy node to handle edge cases more easily
        dummy.next = head;
        SLLNode fast = dummy, slow = dummy;

        for (int i = 0; i <= n; i++) {
            if (fast == null) throw new IllegalArgumentException("List is shorter than n");
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next; // Remove the nth node from end
        return dummy.next;
    }

    /*
    Name - Linked List Cycle Detection
    Link - https://leetcode.com/problems/linked-list-cycle/
    Condition - Detect if there is a cycle within a linked list
    Time Complexity - O(n), where n is the number of nodes in the list
    Space Complexity - O(1), as it only requires two pointers (slow and fast)
    Note - Uses Floyd’s Cycle-Finding Algorithm (Tortoise and Hare approach)
    */
    public boolean hasCycle(SLLNode head) {
        SLLNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true; // Cycle detected
        }
        return false;
    }

    /*
    Name - Palindrome Linked List
    Link - https://leetcode.com/problems/palindrome-linked-list/
    Condition - Determine if a linked list is a palindrome
    Time Complexity - O(n), where n is the length of the linked list
    Space Complexity - O(1), using a reverse operation on half of the list
    Note - Utilizes the fast and slow pointer technique, followed by reversing the second half of the list
    */
    public boolean isPalindrome(SLLNode head) {
        if (head == null || head.next == null) return true;

        SLLNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        SLLNode secondHalf = reverse(slow); // Reverse the second half of the list
        SLLNode firstHalf = head;

        while (secondHalf != null) {
            if (firstHalf.value != secondHalf.value) return false;
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        return true;
    }

    /*
    Name - Intersection of Two Linked Lists
    Link - https://leetcode.com/problems/intersection-of-two-linked-lists/
    Condition - Linked lists may intersect at a common node
    Time Complexity - O(m + n), where m and n are the lengths of the two lists
    Space Complexity - O(1), as only pointers are used for traversal
    Note - Uses a two-pointer approach by switching heads when the end is reached
    */
    public SLLNode getIntersectionNode(SLLNode headA, SLLNode headB) {
        if (headA == null || headB == null) return null;

        SLLNode a = headA, b = headB;
        while (a != b) {
            a = (a == null) ? headB : a.next;
            b = (b == null) ? headA : b.next;
        }

        return a;
    }

    /*
    Name - Split Linked List into Two Halves
    Condition - Distributes nodes into two separate lists by alternating nodes
    Time Complexity - O(n), where n is the length of the original linked list
    Space Complexity - O(n), for storing nodes in two new linked lists
    Note - Alternates nodes between two new lists, resulting in two lists with every other node
    */
    public void splitLinkedList(SLLNode head) {
        SingleLinkedList list1 = new SingleLinkedList();
        SingleLinkedList list2 = new SingleLinkedList();
        int index = 0;

        while (head != null) {
            if (index % 2 == 0) {
                list1.insertAtEnd(head.value);
            } else {
                list2.insertAtEnd(head.value);
            }
            head = head.next;
            index++;
        }

        Utils.printSingleLinkedList(list1.getHead());
        Utils.printSingleLinkedList(list2.getHead());
    }

    /*
    Name - Add Two Numbers Represented by Linked Lists
    Link - https://leetcode.com/problems/add-two-numbers/
    Time Complexity - O(max(m, n)), where m and n are the lengths of the two lists
    Space Complexity - O(1), aside from the output list
    Note - Uses digit-by-digit addition with carry to form the sum list
    */
    public SLLNode addTwoNumbers(SLLNode first, SLLNode second) {
        SLLNode dummy = new SLLNode(0);
        SLLNode current = dummy;
        int carry = 0;

        while (first != null || second != null || carry != 0) {
            int sum = carry;
            if (first != null) {
                sum += first.value;
                first = first.next;
            }
            if (second != null) {
                sum += second.value;
                second = second.next;
            }

            carry = sum / 10;
            current.next = new SLLNode(sum % 10);
            current = current.next;
        }

        return dummy.next;
    }

    /*
    Name - Reverse Linked List
    Condition - Reverses a linked list iteratively
    Time Complexity - O(n), where n is the length of the linked list
    Space Complexity - O(1), using three pointers to reverse in-place
    Note - Reverses a linked list in-place without creating new nodes
    */
    public SLLNode reverse(SLLNode current) {
        SLLNode previous = null;
        SLLNode next;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }
}


class SSLProblemsTest {

    private SLLNode head;
    private SSLProblems sslProblems;

    @BeforeEach
    void setUp() {
        sslProblems = new SSLProblems();
        head = new SLLNode();
    }


    @Test
    public void testRemoveNthFromEnd() {

        head = SingleLinkedList.createFromList(Arrays.asList(1, 2, 3, 4, 5, 6));

        head = sslProblems.removeNthFromEnd(head, 3);

        List<Integer> expectedList = Arrays.asList(1, 2, 3, 5, 6);
        SLLNode expectedHead = SingleLinkedList.createFromList(expectedList);

        Assertions.assertTrue(Utils.isLinkedListEqual(head, expectedHead));
    }

    @Test
    public void testHasCycle() {
        head = SingleLinkedList.createFromList(Arrays.asList(1, 2, 3, 4, 5));
        head.next.next.next.next.next = head.next; // Creating a cycle

        Assertions.assertTrue(sslProblems.hasCycle(head));
    }

    @Test
    public void testIsPalindrome() {
        head = SingleLinkedList.createFromList(Arrays.asList(1, 2, 2, 1));
        Assertions.assertTrue(sslProblems.isPalindrome(head));
    }

    @Test
    public void testAddTwoNumbers() {
        SLLNode list1 = SingleLinkedList.createFromList(Arrays.asList(9, 9, 9));
        SLLNode list2 = SingleLinkedList.createFromList(Arrays.asList(1));
        SLLNode result = sslProblems.addTwoNumbers(list1, list2);

        List<Integer> expectedList = Arrays.asList(0, 0, 0, 1);
        SLLNode expectedHead = SingleLinkedList.createFromList(expectedList);

        Assertions.assertTrue(Utils.isLinkedListEqual(result, expectedHead));
    }

    @Test
    public void testGetIntersectionNode() {
        // Creating two linked lists that intersect
        SLLNode common = new SLLNode(8);
        common.next = new SLLNode(4);
        common.next.next = new SLLNode(5);

        SLLNode list1 = new SLLNode(4);
        list1.next = new SLLNode(1);
        list1.next.next = common; // List 1: 4 -> 1 -> 8 -> 4 -> 5


        SLLNode list2 = new SLLNode(5);
        list2.next = new SLLNode(6);
        list2.next.next = new SLLNode(1);
        list2.next.next.next = common; // List 2: 5 -> 6 -> 1 -> 8 -> 4 -> 5

        SLLNode intersectionSLLNode = sslProblems.getIntersectionNode(list1, list2);

        // Verify that the intersection node is the common node (value 8)
        Assertions.assertNotNull(intersectionSLLNode);
        Assertions.assertEquals(8, intersectionSLLNode.value);

        // Verify that the intersection node is indeed the start of the intersection
        Assertions.assertEquals(common, intersectionSLLNode);
    }

    @Test
    public void testMergeTwoSortedLists() {
        // Creating the first sorted linked list: 1 -> 3 -> 5
        SLLNode list1 = new SLLNode(1);
        list1.next = new SLLNode(3);
        list1.next.next = new SLLNode(5);

        // Creating the second sorted linked list: 2 -> 4 -> 6
        SLLNode list2 = new SLLNode(2);
        list2.next = new SLLNode(4);
        list2.next.next = new SLLNode(6);

        // Merging the two sorted linked lists
        SLLNode mergedList = mergeTwoSortedLists(list1, list2);

        // Expected merged list: 1 -> 2 -> 3 -> 4 -> 5 -> 6
        List<Integer> expectedValues = Arrays.asList(1, 2, 3, 4, 5, 6);
        SLLNode expectedHead = SingleLinkedList.createFromList(expectedValues);

        // Check if the merged list matches the expected merged list
        Assertions.assertTrue(Utils.isLinkedListEqual(mergedList, expectedHead));
    }
}

