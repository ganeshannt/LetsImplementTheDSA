package com.practise.ds.linkedlist.problem;

import com.practise.commons.DLLNode;
import com.practise.ds.linkedlist.impl.DoubleLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Solutions for Doubly Linked List related problems, particularly focused on
 * the "Copy List with Random Pointer" problem from LeetCode.
 */
public class DLLProblems {

    private DLLNode head;

    /**
     * Clones a doubly linked list with arbitrary pointers using a HashMap approach.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param head the head of the list to be cloned
     * @return the head of the cloned list
     */
    public DLLNode cloneListUsingHashMap(DLLNode head) {
        if (head == null) {
            return null;
        }

        Map<DLLNode, DLLNode> nodeMap = new HashMap<>();

        // First pass: Create clone nodes
        DLLNode current = head;
        while (current != null) {
            nodeMap.put(current, new DLLNode(current.value));
            current = current.next;
        }

        // Second pass: Connect next and previous pointers
        current = head;
        while (current != null) {
            DLLNode cloneNode = nodeMap.get(current);
            cloneNode.next = nodeMap.get(current.next);
            cloneNode.previous = nodeMap.get(current.previous);
            current = current.next;
        }

        return nodeMap.get(head);
    }

    /**
     * Clones a doubly linked list with arbitrary pointers using constant extra space.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param head the head of the list to be cloned
     * @return the head of the cloned list
     */
    public DLLNode cloneListInPlace(DLLNode head) {
        if (head == null) {
            return null;
        }

        try {
            // Step 1: Create interleaved list
            DLLNode current = head;
            while (current != null) {
                DLLNode clone = new DLLNode(current.value);
                clone.next = current.next;
                current.next = clone;
                current = clone.next;
            }

            // Step 2: Set previous (random) pointers for clone nodes
            current = head;
            while (current != null && current.next != null) {
                if (current.previous != null) {
                    current.next.previous = current.previous.next;
                }
                current = current.next.next;
            }

            // Step 3: Separate original and clone lists
            current = head;
            DLLNode cloneHead = head.next;
            while (current != null && current.next != null) {
                DLLNode clone = current.next;
                current.next = clone.next;
                if (clone.next != null) {
                    clone.next = clone.next.next;
                }
                current = current.next;
            }

            return cloneHead;
        } catch (Exception e) {
            throw new IllegalStateException("Error during list cloning: " + e.getMessage(), e);
        }
    }
}

/**
 * Test class for DLLProblems
 */
class DLLProblemsTest {
    private DLLProblems dllProblems;
    private DLLNode original;

    @BeforeEach
    void setUp() {
        original = new DLLNode();
        dllProblems = new DLLProblems();
    }

    @Test
    void testCloneListUsingHashMap() {
        // Create a test list: 1 -> 2 -> 3
        original = DoubleLinkedList.createFromList(Arrays.asList(1, 2, 3));

        // Set some random (previous) pointers
        original.previous = original.next;
        original.next.previous = original;

        DLLNode clone = dllProblems.cloneListUsingHashMap(original);

        // Verify the structure
        assertNotNull(clone);
        assertNotEquals(original, clone);
        assertEquals(original.value, clone.value);
        assertEquals(original.next.value, clone.next.value);
        assertEquals(original.previous.value, clone.previous.value);
    }

    @Test
    void testCloneListInPlace() {
        // Create a test list: 1 -> 2 -> 3
        original = DoubleLinkedList.createFromList(Arrays.asList(1, 2, 3));

        // Set some random (previous) pointers
        original.previous = original.next;
        original.next.previous = original;

        DLLNode clone = dllProblems.cloneListInPlace(original);

        // Verify the structure
        assertNotNull(clone);
        assertNotEquals(original, clone);
        assertEquals(original.value, clone.value);
        assertEquals(original.next.value, clone.next.value);
        assertEquals(original.previous.value, clone.previous.value);
    }

    @Test
    void testNullInputs() {
        assertNull(dllProblems.cloneListUsingHashMap(null));
        assertNull(dllProblems.cloneListInPlace(null));
    }

    @Test
    void testSingleNodeList() {
        original = DoubleLinkedList.createFromList(Arrays.asList(1));
        original.previous = original; // Self-referencing random pointer

        // Test HashMap approach
        DLLNode cloneMap = dllProblems.cloneListUsingHashMap(original);
        assertNotNull(cloneMap);
        assertEquals(original.value, cloneMap.value);
        assertEquals(cloneMap, cloneMap.previous); // Verify self-referencing is maintained

        // Test in-place approach
        DLLNode cloneInPlace = dllProblems.cloneListInPlace(original);
        assertNotNull(cloneInPlace);
        assertEquals(original.value, cloneInPlace.value);
        assertEquals(cloneInPlace, cloneInPlace.previous);
    }
}