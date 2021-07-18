package com.practise.ds.heap;

/**
 * heap
 * 
 * some formula
 * 
 * get parent node = (i-1)/2 i is index of array
 * 
 * get left node = (2*i)+1
 * 
 * get right node = (2*i)+2
 * 
 * get internal node = 0 to [n/2]
 * 
 * get leaf node = [n/2] + 1 to n
 * 
 */
public class HeapImpl {

    int arr[];
    int size;

    public HeapImpl(int given[]) {
        arr = given;
        size = 0;
    }

    private void buildMaxHeapImpl(int[] arr, int n) {
        for (int i = n / 2; i >= 0;i--) {
            heapify(arr, n, i);
        }
    }

    private void heapify(int[] arr, int n, int i) {
        int largest_index = i;
        int left_index = (2 * i) + 1;
        int right_index = (2 * i) + 2;
        if (n > left_index && arr[largest_index] < arr[left_index]) {
            largest_index = left_index;
        }
        if (n > right_index && arr[largest_index] < arr[right_index]) {
            largest_index = right_index;
        }
        // if root is not largest then swap the element and call heapify recursively
        if (i != largest_index) {
            int temp = arr[largest_index];
            arr[largest_index] = arr[i];
            arr[i] = temp;
            heapify(arr, n, largest_index);
        }
    }

    private void buildMaxHeap() {
        buildMaxHeapImpl(arr, arr.length-1);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        int given[] = { 4, 1, 3, 2, 16, 9, 10, 14, 8, 7 };
        HeapImpl heap = new HeapImpl(given);
        heap.buildMaxHeap();
        
    }

}