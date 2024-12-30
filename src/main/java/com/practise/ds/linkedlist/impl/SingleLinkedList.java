package com.practise.ds.linkedlist.impl;

import com.practise.commons.SLLNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;

/**
 * An implementation of a singly linked list for integers.
 * This class provides basic operations like insertion, deletion, and traversal.
 */

public class SingleLinkedList {

    private SLLNode head;
    private SLLNode tail;
    private int size;

    /**
     * Creates an empty linked list
     */
    public SingleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Creates a linked list from an array of integers
     *
     * @param values array of integers to create the list from
     * @return new SingleLinkedList instance
     */
    public static SingleLinkedList of(int... values) {
        SingleLinkedList list = new SingleLinkedList();
        for (int value : values) {
            list.insertAtEnd(value);
        }
        return list;
    }

    /**
     * Creates a linked list from a List of integers
     *
     * @param values List of integers to create the list from
     * @return new SingleLinkedList instance
     */
    public static SLLNode createFromList(List<Integer> values) {
        SingleLinkedList list = new SingleLinkedList();
        values.forEach(list::insertAtEnd);
        return list.head;
    }

    public SLLNode getHead() {
        return head;
    }

    public SLLNode getTail() {
        return tail;
    }

    /**
     * Inserts an integer at the front of the list
     *
     * @param value integer to insert
     */
    public void insertAtFront(int value) {
        SLLNode sllNode = new SLLNode(value);
        if (isEmpty()) {
            head = tail = sllNode;
        } else {
            sllNode.next = head;
            head = sllNode;
        }
        size++;
    }

    /**
     * Inserts an integer at the end of the list
     *
     * @param value integer to insert
     */
    public void insertAtEnd(int value) {
        SLLNode sllNode = new SLLNode(value);
        if (isEmpty()) {
            head = tail = sllNode;
        } else {
            tail.next = sllNode;
            tail = sllNode;
        }
        size++;
    }

    /**
     * Inserts a new integer after the first occurrence of a specified integer
     *
     * @param afterValue integer after which to insert
     * @param newValue   integer to insert
     * @return true if insertion was successful, false if afterValue was not found
     */
    public boolean insertAfter(int afterValue, int newValue) {
        if (isEmpty()) {
            return false;
        }

        SLLNode current = head;
        while (current != null && current.value != afterValue) {
            current = current.next;
        }

        if (current == null) {
            return false;
        }

        SLLNode newSLLNode = new SLLNode(newValue);
        newSLLNode.next = current.next;
        current.next = newSLLNode;

        if (current == tail) {
            tail = newSLLNode;
        }
        size++;
        return true;
    }

    /**
     * Deletes the first occurrence of a value from the list
     *
     * @param value value to delete
     * @return true if deletion was successful, false if value was not found
     */
    public boolean delete(int value) {
        if (isEmpty()) {
            return false;
        }

        if (head.value == value) {
            deleteFirst();
            return true;
        }

        SLLNode current = head;
        while (current.next != null && current.next.value != value) {
            current = current.next;
        }

        if (current.next == null) {
            return false;
        }

        current.next = current.next.next;
        if (current.next == null) {
            tail = current;
        }
        size--;
        return true;
    }

    /**
     * Deletes the first element of the list
     *
     * @return OptionalInt containing the deleted value, or empty if list was empty
     */
    public OptionalInt deleteFirst() {
        if (isEmpty()) {
            return OptionalInt.empty();
        }

        int value = head.value;
        head = head.next;
        size--;

        if (isEmpty()) {
            tail = null;
        }

        return OptionalInt.of(value);
    }

    /**
     * Deletes the last element of the list
     *
     * @return OptionalInt containing the deleted value, or empty if list was empty
     */
    public OptionalInt deleteLast() {
        if (isEmpty()) {
            return OptionalInt.empty();
        }

        int value = tail.value;
        if (size == 1) {
            head = tail = null;
        } else {
            SLLNode current = head;
            while (current.next != tail) {
                current = current.next;
            }
            tail = current;
            current.next = null;
        }
        size--;
        return OptionalInt.of(value);
    }

    /**
     * Returns the maximum value in the list
     *
     * @return OptionalInt containing the maximum value, or empty if list is empty
     */
    public OptionalInt getMax() {
        if (isEmpty()) {
            return OptionalInt.empty();
        }

        int max = head.value;
        SLLNode current = head.next;
        while (current != null) {
            if (current.value > max) {
                max = current.value;
            }
            current = current.next;
        }
        return OptionalInt.of(max);
    }

    /**
     * Returns the minimum value in the list
     *
     * @return OptionalInt containing the minimum value, or empty if list is empty
     */
    public OptionalInt getMin() {
        if (isEmpty()) {
            return OptionalInt.empty();
        }

        int min = head.value;
        SLLNode current = head.next;
        while (current != null) {
            if (current.value < min) {
                min = current.value;
            }
            current = current.next;
        }
        return OptionalInt.of(min);
    }

    /**
     * Checks if a value exists in the list
     *
     * @param value value to search for
     * @return true if value exists, false otherwise
     */
    public boolean contains(int value) {
        SLLNode current = head;
        while (current != null) {
            if (current.value == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Returns the sum of all values in the list
     *
     * @return sum of all values
     */
    public int sum() {
        int sum = 0;
        SLLNode current = head;
        while (current != null) {
            sum += current.value;
            current = current.next;
        }
        return sum;
    }

    /**
     * Returns the average of all values in the list
     *
     * @return OptionalDouble containing the average, or empty if list is empty
     */
    public double average() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot calculate average of empty list");
        }
        return (double) sum() / size;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SingleLinkedList that)) return false;
        if (size != that.size) return false;

        SLLNode current1 = this.head;
        SLLNode current2 = that.head;

        while (current1 != null) {
            if (!current1.equals(current2)) return false;
            current1 = current1.next;
            current2 = current2.next;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, tail, size);
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder result = new StringBuilder("[");
        SLLNode current = head;
        while (current != null) {
            result.append(current.value);
            if (current.next != null) {
                result.append(" -> ");
            }
            current = current.next;
        }
        return result.append("]").toString();
    }
}

/**
 * Test class for SingleLinkedList
 */
class SingleLinkedListTest {
    private SingleLinkedList list;

    @BeforeEach
    void setUp() {
        list = new SingleLinkedList();
    }

    @Test
    void testInsertAtFront() {
        list.insertAtFront(1);
        list.insertAtFront(2);
        assertEquals("[2 -> 1]", list.toString());
        assertEquals(2, list.size());
    }

    @Test
    void testInsertAtEnd() {
        list.insertAtEnd(1);
        list.insertAtEnd(2);
        assertEquals("[1 -> 2]", list.toString());
        assertEquals(2, list.size());
    }

    @Test
    void testInsertAfter() {
        list.insertAtEnd(1);
        list.insertAtEnd(3);
        assertTrue(list.insertAfter(1, 2));
        assertEquals("[1 -> 2 -> 3]", list.toString());
        assertEquals(3, list.size());
    }

    @Test
    void testDelete() {
        list.insertAtEnd(1);
        list.insertAtEnd(2);
        list.insertAtEnd(3);
        assertTrue(list.delete(2));
        assertEquals("[1 -> 3]", list.toString());
        assertEquals(2, list.size());
    }

    @Test
    void testDeleteFirst() {
        list.insertAtEnd(1);
        list.insertAtEnd(2);
        OptionalInt deleted = list.deleteFirst();
        assertTrue(deleted.isPresent());
        assertEquals(1, deleted.getAsInt());
        assertEquals("[2]", list.toString());
        assertEquals(1, list.size());
    }

    @Test
    void testDeleteLast() {
        list.insertAtEnd(1);
        list.insertAtEnd(2);
        OptionalInt deleted = list.deleteLast();
        assertTrue(deleted.isPresent());
        assertEquals(2, deleted.getAsInt());
        assertEquals("[1]", list.toString());
        assertEquals(1, list.size());
    }

    @Test
    void testGetMaxMin() {
        list.insertAtEnd(3);
        list.insertAtEnd(1);
        list.insertAtEnd(4);
        list.insertAtEnd(2);

        OptionalInt max = list.getMax();
        OptionalInt min = list.getMin();

        assertTrue(max.isPresent());
        assertTrue(min.isPresent());
        assertEquals(4, max.getAsInt());
        assertEquals(1, min.getAsInt());
    }

    @Test
    void testSumAndAverage() {
        list.insertAtEnd(1);
        list.insertAtEnd(2);
        list.insertAtEnd(3);

        assertEquals(6, list.sum());
        assertEquals(2.0, list.average(), 0.001);
    }

    @Test
    void testContains() {
        list.insertAtEnd(1);
        list.insertAtEnd(2);
        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertFalse(list.contains(3));
    }

    @Test
    void testEmpty() {
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertEquals("[]", list.toString());
    }

    @Test
    void testFactoryMethods() {
        SingleLinkedList list1 = SingleLinkedList.of(1, 2, 3);
        assertEquals("[1 -> 2 -> 3]", list1.toString());

        SLLNode head = SingleLinkedList.createFromList(List.of(1, 2, 3));
        assertEquals("SLLNode{value=1}", head.toString());
    }

    @Test
    void testEmptyListOperations() {
        assertThrows(IllegalStateException.class, () -> list.average());
        assertTrue(list.getMax().isEmpty());
        assertTrue(list.getMin().isEmpty());
        assertEquals(0, list.sum());
        assertFalse(list.delete(1));
        assertTrue(list.deleteFirst().isEmpty());
        assertTrue(list.deleteLast().isEmpty());
    }
}