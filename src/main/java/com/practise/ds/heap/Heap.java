package com.practise.ds.heap;

/**
 * heap
 * <p>
 * some formula
 * <p>
 * get parent node  = (i-1)/2  i is index of array
 * <p>
 * get left node = (2*i)+1
 * <p>
 * get right node = (2*i)+2
 */
public class Heap {

    int heap[];
    int size;

    public Heap(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    public static void main(String[] args) {
        Heap h = new Heap(10);
        h.insert(4);
        h.insert(1);
        h.insert(3);
        h.insert(2);
        h.insert(16);
        h.insert(9);
        h.insert(10);
        h.insert(14);
        h.insert(8);
        h.insert(7);
        h.printHeap();
    }

    public void insert(int value) {
        if (isFull()) {
            System.err.println("Heap is full");
            return;
        }

        heap[size] = value;
        maxHeapify(size);
        size++;
    }

    private void maxHeapify(int index) {
        int newValue = heap[index];
        while (index > 0 && newValue > heap[getParentIndex(index)]) {
            heap[index] = heap[getParentIndex(index)];
            index = getParentIndex(index);
        }
        heap[index] = newValue;
    }

    /******************************************************************************************/
    // helper function
    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private boolean isFull() {
        return (size == heap.length);
    }

    private void printHeap() {
        for (int i : heap) {
            System.out.println(i);
        }
    }
}