package com.practise.problemsolving.array;

public class RotateMatrix {

    // it will work only if matrix is n*n
    private void firstApproach(int[][] arr) {
        // Transpose of matrix
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                swap(arr, i, j);
            }
        }
        // reverse the transposed matrix
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < (arr.length / 2); j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[i][arr.length - 1 - j];
                arr[i][arr.length - 1 - j] = temp;
            }
        }
    }

    private void swap(int arr[][], int i, int j) {
        if (i != j) {
            int temp = arr[i][j];
            arr[i][j] = arr[j][i];
            arr[j][i] = temp;
        }
    }

    private void printArray(int arr[][]) {
        for (int[] is : arr) {
            for (int i : is) {
                System.out.println(i);
            }
        }
        System.out.println("=====================================");
    }

    public static void main(String[] args) {
        RotateMatrix matrix = new RotateMatrix();
        int[][] arr = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        matrix.firstApproach(arr);
    }

}
