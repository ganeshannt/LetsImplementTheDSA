package com.practise.problemsolving.array;

public class CreateTargetArray {

    // best approach
    // best case - o(n), worst case -o(n*n)
    private void firstApproach(int[] arr, int[] index) {
        int j = 0;
        while (j < index.length) {
            if (j == index[j]) {
                j++;
            } else if (j > index[j]) {
                moveahead(arr, index[j], arr[j], j);
                j++;
            }
        }
        for (int k : arr) {
            System.out.println(k);
        }
    }

    private void moveahead(int arr[], int index, int value, int current_index) {
        int temp = arr[index];
        arr[index] = value;
        index += 1;
        while (index <= current_index) {
            value = arr[index];
            arr[index] = temp;
            temp = value;
            index++;
        }
    }

    public static void main(String[] args) {
        CreateTargetArray element = new CreateTargetArray();
        int arr[] = { 0, 1, 2, 3, 4 };
        int index[] = { 0, 1, 2, 2, 1 };
        // m = element.hashTableApproach(arr);
        element.firstApproach(arr, index);
        // element.moveahead(arr, 1, 4);
    }

}
