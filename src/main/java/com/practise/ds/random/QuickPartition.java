package com.practise.ds.random;

import com.practise.commons.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ganeshan Nagarajan
 * @since 08-04-2022
 */

public class QuickPartition {

    public static void main(String[] args) {
        List<Integer> arrlist = new ArrayList<>(Arrays.asList(5, 6, 3, 4, 2, 3, 2, 1, 90));
        arrlist = quickSort(arrlist);
        for (int val : arrlist) {
            System.out.println(val);
        }
    }

    public static List<Integer> quickSort(List<Integer> arr) {

        if (arr.size() == 0 || arr.size() == 1) {
            return arr;
        }

        int start = 0;
        int p = arr.get(0);
        int end = arr.size() - 1;

        while (start < end) {
            if (arr.get(end) < p) {
                start++;
                if (arr.get(start) > p) {
                    Utils.swapByIndex(arr, start, end);
                }
            } else {
                end--;
            }
        }
        Utils.swapByIndex(arr, start, 0);
        return arr;
    }
}
