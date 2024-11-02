package com.practise.commons;

import java.util.Objects;

/**
 * SLLNode class for an AVL tree.
 * Author: Ganeshan Nagarajan
 * Date: 10-04-2022
 */
public class AVLNode {
    public AVLNode left;
    public AVLNode right;
    public final int value;
    public int height;

    // Constructor initializing value and setting default height to 1
    public AVLNode(int value) {
        this.value = value;
        this.height = 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AVLNode node)) return false;
        return value == node.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "AVLNode{" + "value=" + value + ", height=" + height + '}';
    }
}
