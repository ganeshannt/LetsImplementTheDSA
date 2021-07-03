package com.practise.ds.Queue;

public class Queue {

    int front;
    int back;
    int[] queue;

    public Queue() {
        front = 0;
        back = 0;
        queue = new int[10];
    }

    // utility functions
    public int size() {
        return back - front;
    }

    public int peek() {
        return queue[front];
    }

    public void printQueue() {
        for (int i = 0; i < queue.length; i++) {
            System.out.println("[index , Value] => [ " + i + "," + queue[i] + " ]");
        }
    }

    /****************************************************************************************/

    // queue operations
    public void addInQueue(int value) {
        if (size() == queue.length) {
            int[] new_queue = new int[2 * queue.length];
            System.arraycopy(new_queue, 0, queue, 0, queue.length);
            queue = new_queue;
        }
        queue[back++] = value;
    }

    public void removeInQueue() {
        if (size() == 0) {
            front = back = 0;
            System.err.println("Empty queue");
            return;
        } else {
            queue[front++] = 0;
        }
    }

    public static void main(String[] args) {
        System.out.println("Queue yet to be created");
        Queue queue = new Queue();
        queue.addInQueue(10);
        queue.addInQueue(20);
        queue.addInQueue(30);
        queue.addInQueue(40);
        queue.addInQueue(50);
        queue.addInQueue(60);
        queue.removeInQueue();
        queue.removeInQueue();
        System.out.println(queue.peek());
        queue.printQueue();
    }
}
