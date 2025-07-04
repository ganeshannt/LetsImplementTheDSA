package com.practise.playground.interview;


import java.util.ArrayList;
import java.util.List;

public class FormCoilProblem {

    /**
     * Generates two "coils" of numbers based on a specific pattern derived from n.
     * The total number of elements in each coil is 8*n*n.
     *
     * @param n An integer parameter determining the size and pattern of the coils.
     * @return A List containing two Lists of Integers, representing the two coils.
     * The first list is coil1, and the second list is coil2.
     * Returns [[], []] if n=0.
     */
    static List<List<Integer>> formCoils(int n) {
        int m = 8 * n * n;
        if (m == 0) {
            List<List<Integer>> emptyResult = new ArrayList<>();
            emptyResult.add(new ArrayList<>()); // Add an empty list for coil1
            emptyResult.add(new ArrayList<>()); // Add an empty list for coil2
            return emptyResult;
        }
        List<Integer> coil1List = new ArrayList<>(m);
        List<Integer> coil2List = new ArrayList<>(m);
        int currentCoil1Value = 8 * n * n + 2 * n;
        coil1List.add(currentCoil1Value);
        coil2List.add(16 * n * n + 1 - currentCoil1Value);
        int nflg = 1;
        int step = 2;

        int elementsAdded = 1;

        while (elementsAdded < m) {

            for (int i = 0; i < step; i++) {
                currentCoil1Value = currentCoil1Value - 4 * n * nflg;
                coil1List.add(currentCoil1Value);
                coil2List.add(16 * n * n + 1 - currentCoil1Value);
                elementsAdded++;

                if (elementsAdded >= m) {
                    break;
                }
            }

            if (elementsAdded >= m) {
                break;
            }

            for (int i = 0; i < step; i++) {
                currentCoil1Value = currentCoil1Value + nflg;
                coil1List.add(currentCoil1Value);
                // Calculate and add the corresponding coil2 element immediately.
                coil2List.add(16 * n * n + 1 - currentCoil1Value);
                elementsAdded++; // Increment the count of added elements

                // Important Check: If we've added 'm' elements, we are done.
                // Break out of the inner loop.
                if (elementsAdded >= m) {
                    break;
                }
            }

            if (elementsAdded >= m) {
                break;
            }

            nflg = nflg * (-1);
            step += 2;
        }
        List<List<Integer>> result = new ArrayList<>();
        result.add(coil1List);
        result.add(coil2List);

        return result;
    }
}
