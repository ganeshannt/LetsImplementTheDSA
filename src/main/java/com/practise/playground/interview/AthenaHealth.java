package com.practise.playground.interview;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AthenaHealth {


    public static void featuredProducts(List<String> products) {
        Map<String, Long> productAndCount = products.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        Long count = 0L;
        String result = null;
        for (Map.Entry<String, Long> entry : productAndCount.entrySet()) {
            if (entry.getValue() > count) {
                count = entry.getValue();
                result = entry.getKey();
            }
        }
        System.out.println(result);
    }


    public int minCost(int[][] costs) {
        int n = costs.length;

        if (n == 0) {
            return 0;
        }

        // Initialize variables to store the minimum costs for the *previous* house,
        // ending with material 0, 1, or 2 respectively.
        // For the first house (house 0), these are simply its building costs.
        int prevMinCostMat0 = costs[0][0];
        int prevMinCostMat1 = costs[0][1];
        int prevMinCostMat2 = costs[0][2];

        // Iterate through the rest of the houses, from the second house (index 1)
        // up to the last house (index n-1).
        for (int i = 1; i < n; i++) {
            // Calculate the minimum costs for the *current* house 'i',
            // ending with each material.

            // Cost to build house 'i' with material 0:
            // It's the cost of material 0 for house 'i' PLUS
            // the minimum cost of building up to house 'i-1' ending with material 1 or 2.
            int currentMinCostMat0 = costs[i][0] + Math.min(prevMinCostMat1, prevMinCostMat2);

            // Cost to build house 'i' with material 1:
            int currentMinCostMat1 = costs[i][1] + Math.min(prevMinCostMat0, prevMinCostMat2);

            // Cost to build house 'i' with material 2:
            int currentMinCostMat2 = costs[i][2] + Math.min(prevMinCostMat0, prevMinCostMat1);

            // Update the 'previous' costs for the next iteration.
            // The current house's calculated minimum costs become the 'previous' costs
            // for processing the next house (i+1).
            prevMinCostMat0 = currentMinCostMat0;
            prevMinCostMat1 = currentMinCostMat1;
            prevMinCostMat2 = currentMinCostMat2;
        }
        return Math.min(prevMinCostMat0, Math.min(prevMinCostMat1, prevMinCostMat2));
    }


    public static void main(String[] args) {
        featuredProducts(List.of("redShirt", "redShirt", "blackPants", "blackPants", "blackPants"));
    }
}
