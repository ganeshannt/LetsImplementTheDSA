package com.practise.commons;

import java.util.Objects;

/**
 * @author Ganeshan Nagarajan
 * @since 09-04-2022
 */

public class Node {
    public int value;
    public Node next;

    public Node() {
        this.value = 0;
        this.next = null;
    }

    public Node(int value) {
        this.value = value;
        this.next = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return value == node.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, next);
    }
}

