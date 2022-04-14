package com.practise.problemsolving.array;


import com.practise.commons.Utils;

/*

Name - Rotate Image
Link - https://leetcode.com/problems/rotate-image/

 */
public class RotateMatrix {

    public static void main(String[] args) {
        RotateMatrix matrix = new RotateMatrix();
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        matrix.firstApproach(arr);
        Utils.printArray(arr);
    }


    /*
    Time Complexity - Transpose - O(n*m) & reverse - O(n*m/2) => overall O(n*m)
    Space Complexity - Transpose - o(1) & reverse - o(1) => overall o(1)
    Note - Transpose and reverse. it will work only if matrix is row and colum is equal
    */
    private void firstApproach(int[][] arr) {

        // Transpose of matrix (row -> colum)
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                Utils.swapByIndex(arr, i, j);
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


}
