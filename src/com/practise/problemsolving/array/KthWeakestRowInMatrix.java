package com.practise.problemsolving.array;

public class KthWeakestRowInMatrix {

    private void firstApproach(int[][] arr, int k) {
        int row = arr.length;
        int col = arr[0].length;
        int count = 0;
        int i = 0;
        int j = 0;
        int resultarr [] =  new int[arr.length];
        while (i < row && j < col) {
            if (arr[i][j] != 1){
                resultarr[count++] = i;
            }
        }

    }

    private void bestApproach(int[][] arr) {
    }

    public static void main(String[] args) {
        KthWeakestRowInMatrix matrix = new KthWeakestRowInMatrix();
        int[][] arr = { { 1, 1, 0, 0, 0 }, { 1, 1, 1, 1, 0 }, { 1, 0, 0, 0, 0 }, { 1, 1, 0, 0, 0 }, { 1, 1, 1, 1, 1 } };
        matrix.firstApproach(arr, 3);
    }
}
