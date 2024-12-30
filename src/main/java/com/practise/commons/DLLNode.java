package com.practise.commons;

import java.util.Objects;

/**
 * SLLNode class for a doubly linked list.
 * Author: Ganeshan Nagarajan
 * Date: 09-04-2022
 */
public class DLLNode {
    public final int value;
    public DLLNode previous;
    public DLLNode next;

    // Default constructor initializing with zero
    public DLLNode() {
        this(0);
    }

    // Constructor with specified value
    public DLLNode(int value) {
        this.value = value;
        this.previous = null;
        this.next = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DLLNode)) return false;
        DLLNode node = (DLLNode) o;
        return value == node.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "DLLNode{" + "value=" + value + '}';
    }
}
