package com.practise.ds.string.problem;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


/*
Name - Group Anagrams
Link - https://leetcode.com/problems/group-anagrams/
*/

public class Anagrams {

    /*
    Time Complexity -  O(m*n)
    Space Complexity - O(n)
    Note - Hash Map
    */
    private List<List<String>> bruteForceApproach(String[] strs) {
        List<List<String>> resultList = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] chararr = strs[i].toCharArray();
            Arrays.sort(chararr);
            String sorted = new String(chararr);
            if (!map.containsKey(sorted)) {
                map.put(sorted, new ArrayList<>());
            }
            map.get(sorted).add(strs[i]);
        }
        resultList.addAll(map.values());
        return resultList;
    }

    public boolean isAnagram(String s, String t) {
        char[] ca1 = s.toCharArray();
        char[] ca2 = t.toCharArray();
        Arrays.sort(ca1);
        Arrays.sort(ca2);
        if (Objects.equals(ca1, ca2)) {
            return true;
        }
        return false;
    }

    @Test
    public void testAnagrams() {
        Anagrams anagrams = new Anagrams();
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};

        String[][] output = {{"bat"}, {"nat", "tan"}, {"ate", "eat", "tea"}};
        assertEquals(output, anagrams.bruteForceApproach(input));
    }
}
