package com.practise.ds.array.problem;


/*

Name - Count Negative Numbers in a Sorted Matrix
Link - https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/

 */
public class CountNegativeNumber {

    public static void main(String[] args) {
        CountNegativeNumber matrix = new CountNegativeNumber();
        int[][] arr = {{4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1,
                -2, -3}};
        // int[][] arr = { { 3, 2 }, { 1, 0 } };
        matrix.bestApproach(arr);
    }

    /*
    Time Complexity [best] - o(nlogm)
    Time Complexity [worst] - o(n*m)
    Space Complexity - o(1)
    Note - iterate each row from last element to till positive element
     */

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


    /*
    Time Complexity - o(n+m)
    Space Complexity - o(1)
    Note - In problem they have mentioned like " non-increasing order both row-wise and column-wise"
            so here we are tracing array from last column to first by checking positivity of row element
     */

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
            } else {
                j++;
            }
        }
        System.out.println(count);
    }

}
