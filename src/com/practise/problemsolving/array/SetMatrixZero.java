package com.practise.problemsolving.array;

import java.util.HashSet;
import java.util.Set;

public class SetMatrixZero {

    private void firstApproach(int[][] arr) {
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

    private void bestApproach(int[][] arr) {
        int row = arr.length - 1;
        int col = arr[0].length - 1;
        boolean rowFlag = false;
        boolean colFlag = false;

        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= col; j++) {
                if (i == 0 && arr[i][j] == 0) {
                    rowFlag = true;
                }
                if (j == 0 && arr[i][j] == 0) {
                    colFlag = true;
                }
                if (arr[i][j] == 0) {
                    arr[0][j] = 0;
                    arr[i][0] = 0;
                }
            }
        }
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (arr[i][0] == 0 || arr[0][j] == 0) {
                    arr[i][j] = 0;
                }
            }
        }

        if (rowFlag) {
            for (int i = 0; i <= col; i++) {
                arr[0][i] = 0;
            }
        }

        if (colFlag) {
            for (int i = 0; i <= row; i++) {
                arr[i][0] = 0;
            }
        }

    }

    public static void main(String[] args) {
        SetMatrixZero matrix = new SetMatrixZero();
        int[][] arr = { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 1, 5 } };
        matrix.firstApproach(arr);
    }
}
