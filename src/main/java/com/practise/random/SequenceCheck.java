package com.practise.random;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Ganeshan Nagarajan
 * @since 07-04-2022
 */

public class SequenceCheck {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number to find sequence ");
        int n = scanner.nextInt();
        sequenceChecker(n);
    }

    public static void sequenceChecker(int n) {
        ArrayList<Integer> targetSeries = new ArrayList<>();
        int targetSum = 0;
        for (int i = 1; i < (n / 2) + 1; i++) {
            for (int j = i; j < (n / 2) + 1; j++) {
                targetSum += j;
                targetSeries.add(j);
                if (targetSum == n) {
                    for (int val : targetSeries) {
                        System.out.print(val);
                    }
                    System.out.println();
                    break;
                }
            }
            targetSeries.clear();
            targetSum = 0;
        }
    }
}
