package com.practise.problemsolving.array;

import java.util.HashSet;
import java.util.Set;

/*

Name - Set Matrix Zeroes
Link - https://leetcode.com/problems/set-matrix-zeroes/
condition - You must do it in place.

 */
public class SetMatrixZero {

    public static void main(String[] args) {
        SetMatrixZero matrix = new SetMatrixZero();
        int[][] arr = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        matrix.bruteForceApproach(arr);
    }

    /*
    Time Complexity - O(nm)
    Space Complexity - o(n+m) worst case
    Note - find indices that have zero then traverse matrix to make its colum and row to zero
    */

    private void bruteForceApproach(int[][] arr) {
        int row = arr.length - 1;
        int col = arr[0].length - 1;
        Set<Integer> rowset = new HashSet<>();
        Set<Integer> colset = new HashSet<>();
        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= col; j++) {
                if (arr[i][j] == 0) {
                    rowset.add(i);
                    colset.add(j);
                }
            }
        }

        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= col; j++) {
                if (rowset.contains(i) || colset.contains(j)) {
                    arr[i][j] = 0;
                }
            }
        }

    }


    /*
    Time Complexity - O(nm)
    Space Complexity - o(1)
    Note - Instead of using set, use first row and column to store which row and colum has zero
         - Use row and colum flag to store whether first row and colum contains zero or not
    */
    private void bestApproach(int[][] arr) {
        int row = arr.length - 1;
        int col = arr[0].length - 1;
        boolean rowFlag = false;
        boolean colFlag = false;

        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= col; j++) {
//                first row zero existence check
                if (i == 0 && arr[i][j] == 0) {
                    rowFlag = true;
                }
//                first colum zero existence check
                if (j == 0 && arr[i][j] == 0) {
                    colFlag = true;
                }
//                update first row and colum if you found zero
                if (arr[i][j] == 0) {
                    arr[0][j] = 0;
                    arr[i][0] = 0;
                }
            }
        }

//      update the sub-matrix by referring the first row and colum value
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (arr[i][0] == 0 || arr[0][j] == 0) {
                    arr[i][j] = 0;
                }
            }
        }
//      update first row
        if (rowFlag) {
            for (int i = 0; i <= col; i++) {
                arr[0][i] = 0;
            }
        }

//      update first colum
        if (colFlag) {
            for (int i = 0; i <= row; i++) {
                arr[i][0] = 0;
            }
        }

    }
}
