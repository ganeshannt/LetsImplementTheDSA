package com.practise.ds.linkedlist.impl;

import com.practise.commons.DLLNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A doubly linked list implementation that stores integer values.
 */
public class DoubleLinkedList {

    private DLLNode head;
    private DLLNode tail;
    private int size;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Creates a double linked list from a list of integers.
     * Returns head of the created list.
     */
    public static DLLNode createFromList(List<Integer> values) {
        Objects.requireNonNull(values, "Input list cannot be null");
        DoubleLinkedList dll = new DoubleLinkedList();
        for (int value : values) {
            dll.insertEnd(value);
        }
        return dll.head;
    }

    /**
     * Creates a DoubleLinkedList instance from a list of integers
     */
    public static DoubleLinkedList from(List<Integer> values) {
        Objects.requireNonNull(values, "Input list cannot be null");
        DoubleLinkedList dll = new DoubleLinkedList();
        for (int value : values) {
            dll.insertEnd(value);
        }
        return dll;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public boolean contains(int value) {
        DLLNode current = head;
        while (current != null) {
            if (current.value == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void insertFront(int value) {
        DLLNode newNode = new DLLNode(value);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
        size++;
    }

    public void insertEnd(int value) {
        DLLNode newNode = new DLLNode(value);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size++;
    }

    public void insertAfter(int point, int value) {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot insert into empty list");
        }

        DLLNode current = head;
        while (current != null) {
            if (current.value == point) {
                DLLNode newNode = new DLLNode(value);
                newNode.next = current.next;
                newNode.previous = current;

                if (current.next != null) {
                    current.next.previous = newNode;
                } else {
                    tail = newNode;
                }

                current.next = newNode;
                size++;
                return;
            }
            current = current.next;
        }
        throw new IllegalArgumentException("Point " + point + " not found in list");
    }

    public void deleteFront() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot delete from empty list");
        }

        if (size == 1) {
            head = tail = null;
        } else {
            head = head.next;
            head.previous = null;
        }
        size--;
    }

    public void deleteEnd() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot delete from empty list");
        }

        if (size == 1) {
            head = tail = null;
        } else {
            tail = tail.previous;
            tail.next = null;
        }
        size--;
    }

    public void delete(int value) {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot delete from empty list");
        }

        if (head.value == value) {
            deleteFront();
            return;
        }

        if (tail.value == value) {
            deleteEnd();
            return;
        }

        DLLNode current = head;
        while (current != null) {
            if (current.value == value) {
                current.previous.next = current.next;
                current.next.previous = current.previous;
                size--;
                return;
            }
            current = current.next;
        }
        throw new IllegalArgumentException("Value " + value + " not found in list");
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        DLLNode current = head;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) {
                sb.append(" ↔ ");
            }
            current = current.next;
        }
        return sb.append("]").toString();
    }
}

/**
 * Test class for DoubleLinkedList
 */
class DoubleLinkedListTest {
    private DoubleLinkedList list;

    @BeforeEach
    void setUp() {
        list = new DoubleLinkedList();
    }

    @Test
    void testCreateFromList() {
        // Test with normal list
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        DLLNode head = DoubleLinkedList.createFromList(numbers);

        // Verify forward traversal
        DLLNode current = head;
        int index = 0;
        while (current != null) {
            assertEquals(numbers.get(index), current.value);
            index++;
            current = current.next;
        }

        // Verify backward traversal
        current = head;
        while (current.next != null) {
            current = current.next;
        }
        index = numbers.size() - 1;
        while (current != null) {
            assertEquals(numbers.get(index), current.value);
            index--;
            current = current.previous;
        }
    }

    @Test
    void testFromList() {
        DoubleLinkedList list = DoubleLinkedList.from(Arrays.asList(1, 2, 3));
        assertEquals(3, list.size());
        assertEquals("[1 ↔ 2 ↔ 3]", list.toString());
    }

    @Test
    void testInitialState() {
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testInsertOperations() {
        // Using from() to setup initial list
        list = DoubleLinkedList.from(Arrays.asList(2, 3));
        list.insertFront(1);
        list.insertEnd(4);
        list.insertAfter(2, 5);

        assertEquals(5, list.size());
        assertEquals("[1 ↔ 2 ↔ 5 ↔ 3 ↔ 4]", list.toString());
    }

    @Test
    void testDeleteOperations() {
        // Using from() to setup initial list
        list = DoubleLinkedList.from(Arrays.asList(1, 2, 3, 4, 5));
        list.deleteFront();
        list.deleteEnd();
        list.delete(3);

        assertEquals(2, list.size());
        assertEquals("[2 ↔ 4]", list.toString());
    }

    @Test
    void testEdgeCases() {
        // Empty list
        assertNull(DoubleLinkedList.createFromList(Collections.emptyList()));

        // Null list
        assertThrows(NullPointerException.class, () ->
                DoubleLinkedList.createFromList(null));

        // Single element
        list = DoubleLinkedList.from(Collections.singletonList(1));
        assertEquals(1, list.size());
        assertEquals("[1]", list.toString());
    }

    @Test
    void testExceptionCases() {
        list = DoubleLinkedList.from(Arrays.asList(1, 2, 3));

        // Delete from empty list
        DoubleLinkedList emptyList = new DoubleLinkedList();
        assertThrows(IllegalStateException.class, () ->
                emptyList.delete(1));

        // Delete non-existent value
        assertThrows(IllegalArgumentException.class, () ->
                list.delete(5));

        // Insert after non-existent point
        assertThrows(IllegalArgumentException.class, () ->
                list.insertAfter(5, 6));
    }
}