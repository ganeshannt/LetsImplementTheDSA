package com.practise.playground.interview;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Partition the cards into one or more groups such that:
 * <p>
 * Each group has exactly x cards where x > 1, and
 * All the cards in one group have the same integer written on them.
 * Return true if such partition is possible, or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: deck = [1,2,3,4,4,3,2,1]
 * Output: true
 * Explanation: Possible partition [1,1],[2,2],[3,3],[4,4].
 * Example 2:
 * <p>
 * Input: deck = [1,1,1,2,2,2,3,3]
 * Output: false
 * Explanation: No possible partition.
 */


public class FreshWorkTest {


    public boolean hasGroupsSizeX(int[] deck) {

        if (deck.length == 0 || deck.length == 1) return false;

        Map<Integer, Integer> numberAndOccurMap = new HashMap<>();

        for (int j : deck) {

            if (numberAndOccurMap.containsKey(j)) {
                if (numberAndOccurMap.get(j) == 1) {
                    numberAndOccurMap.put(j, 0);
                } else {
                    numberAndOccurMap.put(j, 1);
                }
            } else {
                numberAndOccurMap.put(j, 1);
            }
        }

        Set<Integer> set = numberAndOccurMap.values().stream().collect(Collectors.toSet());

        return set.size() == 1;

    }


    public int search(int[] nums, int target) {

        if (nums.length == 1) return nums[0];

        int fast = (nums.length / 2) - 1;

        int pivot = -1;

        for (int i = 0; i < (nums.length / 2); ++i, fast++) {
            if (nums[i] > nums[i + 1]) {
                pivot = i + 1;
            } else if (nums[fast] > nums[fast + 1]) {
                pivot = fast + 1;
            }
            if (pivot != -1) {
                break;
            }
        }

        if (pivot == -1) return nums[0];

        return nums[pivot];
    }


    public static void main(String[] args) {
        FreshWorkTest f = new FreshWorkTest();
        System.out.println(f.search(new int[]{2,3,1}, 0));
    }
}
