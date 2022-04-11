package com.practise.ds.heap;

import com.practise.commons.Utils;

/**
 * heap
 * <p>
 * some formula
 * <p>
 * get parent node = (i-1)/2 i is index of array
 * <p>
 * get left node = (2*i)+1
 * <p>
 * get right node = (2*i)+2
 * <p>
 * get internal node = 0 to [n/2]
 * <p>
 * get leaf node = [n/2] + 1 to n
 */
public class HeapImpl {

    int arr[];
    int size;

    public HeapImpl(int given[]) {
        arr = given;
        size = given.length;
    }

    public static void main(String[] args) {
        int given[] = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        HeapImpl heap = new HeapImpl(given);
        // heap.buildMaxHeap();
        heap.heapSort();
        System.out.println("==================================");
        // heap.insertIntoHeap(5);
        // heap.printHeap();
        // heap.increaseKey(3, 18);
        // heap.printHeap();
        // heap.extractMax();
        // heap.printHeap();
    }

    private void buildMaxHeapImpl(int[] arr, int n) {
        for (int i = n / 2; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    private void heapify(int[] arr, int n, int i) {
        int largest_index = i;
        int left_index = getLeftIndex(i);
        int right_index = getRightIndex(i);
        if (n > left_index && arr[largest_index] < arr[left_index]) {
            largest_index = left_index;
        }
        if (n > right_index && arr[largest_index] < arr[right_index]) {
            largest_index = right_index;
        }
        // if root is not largest then swap the element and call heapify recursively
        if (i != largest_index) {
            Utils.swapByIndex(arr, i, largest_index);
            heapify(arr, n, largest_index);
        }
    }

    private void heapSortImpl(int[] arr, int n) {
        buildMaxHeapImpl(arr, n);

        for (int i = n - 1; i >= 0; i--) {

            // arr[0] is a root of the heap and is the max element in heap

            Utils.swapByIndex(arr, 0, i);

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    private void insertIntoHeap(int value) {
        int n = arr.length;
        if (isFull()) {
            int arr2[] = new int[arr.length * 2];
            System.arraycopy(arr, 0, arr2, 0, arr.length);
            arr = arr2;
        }
        arr[n] = value;
        size++;
        int parent_index = getParentIndex(n);
        while (n > 0 && arr[n] > arr[parent_index]) {
            Utils.swapByIndex(arr, n, parent_index);
            n = parent_index;
        }
    }

    private void extractMax() {
        if (isEmpty()) {
            System.err.println("Empty heap");
            return;
        }
        // get max value from index 0
        int max = arr[0];
        // assign least value in root do heapify to maintain the heap property
        arr[0] = arr[size - 1];
        // delete element and decrease size of the array
        arr[size - 1] = 0;
        size--;
        // do heapify
        heapify(arr, arr.length, 0);
        System.out.println("extract Max value : " + max);
    }

    private void increaseKeyImpl(int arr[], int oldValue, int newValue) {
        if (isEmpty()) {
            System.err.println("Empty heap");
            return;
        }

        int oldValue_index = getIndexByValue(oldValue);
        arr[oldValue_index] = newValue;
        while (oldValue > 0 && arr[oldValue_index] > arr[getParentIndex(oldValue_index)]) {
            Utils.swapByIndex(arr, oldValue_index, getParentIndex(oldValue_index));
            oldValue_index = getParentIndex(oldValue_index);
        }
    }

    // Utility functions
    private int getIndexByValue(int oldValue) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == oldValue) {
                return i;
            }
        }
        return -1;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private int getLeftIndex(int index) {
        return (2 * index) + 1;
    }

    private int getRightIndex(int index) {
        return (2 * index) + 2;
    }

    private int getMax() {
        if (isEmpty()) {
            System.err.println("Empty heap");
            return -1;
        }
        return arr[0];
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return arr.length == size;
    }

    // Wrapper class

    private void printHeap(int[] heap) {
        for (int i : heap) {
            System.out.println(i);
        }
    }

    private void printHeap() {
        for (int i : arr) {
            System.out.println(i);
        }
    }

    private void buildMaxHeap() {
        buildMaxHeapImpl(arr, arr.length);
        printHeap(arr);
    }

    private void heapSort() {
        heapSortImpl(arr, arr.length);
        printHeap(arr);
    }

    private void increaseKey(int oldValue, int newValue) {
        increaseKeyImpl(arr, oldValue, newValue);
    }
}