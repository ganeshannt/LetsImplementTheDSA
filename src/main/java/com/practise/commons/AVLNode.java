package com.practise.commons;

/**
 * @author Ganeshan Nagarajan
 * @since 10-04-2022
 */

public class AVLNode {
    public AVLNode left;
    public AVLNode right;
    public int value;
    public int height;

    public AVLNode(int value) {
        this.value = value;
        this.height = 1;
    }
}

