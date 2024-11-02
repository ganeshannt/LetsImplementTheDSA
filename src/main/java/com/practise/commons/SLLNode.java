package com.practise.commons;

import java.util.Objects;

/**
 * SLLNode class for a singly linked list.
 * Author: Ganeshan Nagarajan
 * Date: 09-04-2022
 */

public class SLLNode {
    public final int value;
    public SLLNode next;

    // Default constructor initializing with zero
    public SLLNode() {
        this(0);
    }

    // Constructor with specified value
    public SLLNode(int value) {
        this.value = value;
        this.next = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SLLNode)) return false;
        SLLNode SLLNode = (SLLNode) o;
        return value == SLLNode.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "SLLNode{" + "value=" + value + '}';
    }
}
