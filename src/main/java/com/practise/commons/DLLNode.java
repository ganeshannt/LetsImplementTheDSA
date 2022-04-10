package com.practise.commons;

/**
 * @author Ganeshan Nagarajan
 * @since 09-04-2022
 */

public class DLLNode {
    public DLLNode previous;
    public DLLNode next;
    public int value;


    public DLLNode() {
        this.value = 0;
        this.previous = null;
        this.next = null;
    }

    public DLLNode(int value) {
        this.value = value;
        this.previous = null;
        this.next = null;
    }
}
