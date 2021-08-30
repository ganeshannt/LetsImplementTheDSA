package com.practise.problemsolving.array;

public class NumberOfInversion {

    private void firstApproach(int[] arr) {
        int inversion_count = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                inversion_count += i;
            }
        }
        System.out.println(inversion_count);
    }

    public static void main(String[] args) {
        int arr[] = { 2, 3, 4, 5, 6 };
        // 1, 2, 4, 6, 7, 9, 8, 3, 10
        NumberOfInversion inversion = new NumberOfInversion();
        inversion.firstApproach(arr);
    }

}
