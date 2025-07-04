package com.practise.playground.interview;

import com.practise.commons.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OspCompany {

    public static List<List<Integer>> generatePascalTriangle(int n) {

        List<List<Integer>> pascalList = new ArrayList<>();

        if (n == 1) {
            pascalList.add(List.of(1));
            return pascalList;
        } else if (n >= 2) {
            pascalList.add(List.of(1));
            pascalList.add(List.of(1, 1));
        }

        for (int i = 3; i <= n; i++) {
            List<Integer> previous = pascalList.getLast();
            List<Integer> current = new ArrayList<>();
            current.addFirst(1);
            current.addLast(1);
            for (int j = 1; j < i - 1; j++) {
                int sum = previous.get(j - 1) + previous.get(j);
                current.add(j, sum);
            }
            pascalList.add(current);
        }
        return pascalList;
    }


    public static void main(String[] args) {
        List<List<Integer>> pascalTriangle = generatePascalTriangle(10);
        Utils.printListOfList(Collections.singletonList(pascalTriangle));
    }
}
