package com.practise.problemsolving.string;

import org.junit.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Anagrams {

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

    @Test
    public void testAnagrams() {
        Anagrams anagrams = new Anagrams();
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};

        String[][] output = {{"bat"}, {"nat", "tan"}, {"ate", "eat", "tea"}};
        assertEquals(output, anagrams.bruteForceApproach(input));
    }
}
