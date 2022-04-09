package com.practise.ds.stack;

public class Stack {

    private int[] stack;
    private int size;
    private int pos;

    public Stack() {
        this.stack = new int[10];
        this.size = 0;
        this.pos = this.stack.length - 1;
    }

    /*******************************************************/

    // Utility function
    private boolean isOverFlow() {
        return size >= stack.length;
    }

    private boolean isUnderFlow() {
        return size <= 0;
    }

    public void printArray() {
        System.out.print("[ ");
        for (int i : stack) {
            System.out.print(i + " ");
        }
        System.out.print(" ]");
    }

    /*******************************************************/

    // stack operation
    public void push(int value) {
        if (isOverFlow()) {
            System.out.println("Stack is full");
            return;
        }
        stack[pos--] = value;
        size++;
    }

    public void pop() {
        if (isUnderFlow()) {
            System.out.println("Empty stack");
            return;
        }
        stack[++pos] = 0;
        size--;
    }

    public int peek() {
        return stack[pos + 1];
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);
        stack.push(60);
        stack.push(70);
        stack.push(80);
        stack.push(90);
        stack.printArray();
        System.out.println(stack.peek());
        System.out.println(stack.size);
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.printArray();
        System.out.println(stack.peek());
        System.out.println(stack.size);
    }
}
