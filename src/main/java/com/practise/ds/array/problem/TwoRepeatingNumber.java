package com.practise.ds.array.problem;


/*

Name - Two Repeating Number
Link - refer disk

 */
public class TwoRepeatingNumber {

    public static void main(String[] args) {
        int arr[] = {1, 2, 2, 3, 4, 5, 5};
        TwoRepeatingNumber number = new TwoRepeatingNumber();
        number.bestApproach(arr);
    }

    // formula to remember
    // n(n+1)/2 => get sum of n number
    // (x-y)^2 = (x+y)^2-4xy => we can find x-y value here
    // using x+y and x-y you can find either x or y
    // here a bit overflow may happen if the integer length is inadequates for given number
    private void AvarageApproach(int[] arr) {
        int addition = 0;
        int m = arr.length - 2;
        int addition_dash = (m * (m + 1)) / 2;
        int multi = 1;
        int multi_dash = 1;

        for (int i = 1; i <= arr.length - 2; i++) {
            multi_dash *= i;
        }

        for (int i = 0; i < arr.length; i++) {
            addition += arr[i];
            multi *= arr[i];
        }

        int x = addition - addition_dash;
        int y = multi / multi_dash;

        int z = (int) Math.sqrt((x * x) - 4 * y);
        z = (x + z) / 2;
        y = x - z;
        x = z;

        System.out.println(x);
        System.out.println(y);
    }

    private void bestApproach(int[] arr) {
    }

}
