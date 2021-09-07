package com.practise.problemsolving.array;

public class CountNegativeNumber {

    private void firstApproach(int[][] arr) {
        int row = arr.length - 1;
        int col = arr[0].length - 1;

        int count = 0;
        for (int i = row; i >= 0; i--) {
            for (int j = col; j >= 0; j--) {
                if (arr[i][j] < 0) {
                    count++;
                } else {
                    break;
                }
            }
        }
        System.out.println(count);
    }

    private void bestApproach(int arr[][]) {
        int row = arr.length;
        int col = arr[0].length;
        int i = row - 1;
        int j = 0;
        int count = 0;
        while (i >= 0 && j < col) {
            if (arr[i][j] < 0) {
                count += (col - j);
                i--;
                j=0;
            } else {
                j++;
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) {
        CountNegativeNumber matrix = new CountNegativeNumber();
        int[][] arr = { { 4, 3, 2, -1 }, { 3, 2, 1, -1 }, { 1, 1, -1, -2 }, { -1, -1,
        -2, -3 } };
        // int[][] arr = { { 3, 2 }, { 1, 0 } };
        matrix.bestApproach(arr);
    }

}
