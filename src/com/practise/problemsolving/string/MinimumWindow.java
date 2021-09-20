package com.practise.problemsolving.string;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class MinimumWindow {

    private String minimumWindowBruteForeApproach(String s, String p) {
        Map<Character, Integer> stringMap = new HashMap<>(p.length() - 1);
        Map<Character, Integer> patternMap = new HashMap<>(p.length() - 1);

        for (char l : p.toCharArray()) {
            if (patternMap.containsKey(l)) {
                patternMap.put(l, patternMap.get(l) + 1);
            } else {
                patternMap.put(l, 1);
                stringMap.put(l, 0);
            }
        }
        int have = 0;
        int need = p.length();
        char[] chararr = s.toCharArray();
        int i = 0;
        int j = 0;
        int strlen = Integer.MAX_VALUE;
        int start = 0, end = 0;
        while (j < s.length()) {
            if (patternMap.containsKey(chararr[j])) {
                int val = stringMap.get(chararr[j]);
                if (val < patternMap.get(chararr[j])) {
                    have += 1;
                }
                stringMap.put(chararr[j], val + 1);
            }
            while (have == need) {
                if (strlen > (j - i + 1)) {
                    strlen = j - i + 1;
                    start = i;
                    end = j;
                }
                if (stringMap.containsKey(chararr[i])) {
                    stringMap.put(chararr[i], stringMap.get(chararr[i]) - 1);
                    have = (stringMap.get(chararr[i]) >= patternMap.get(chararr[i])) ? have : have - 1;
                }
                i++;
            }
            j++;
        }

        if (strlen == Integer.MAX_VALUE)
            return "";

        StringBuilder builder = new StringBuilder(j - i + 1);

        while (start <= end) {
            builder.append(chararr[start++]);
        }
        return builder.toString().trim();
    }

    @Test
    public void testMinimumWindow() {
        MinimumWindow minimumWindow = new MinimumWindow();
        String str = "ADOBECODEBANC";
        String substr = "ABC";
        String output = "BANC";
        String str1 = "aa";
        String substr1 = "aa";
        String output1 = "aa";
        Assert.assertEquals(output1, minimumWindow.minimumWindowBruteForeApproach(str1, substr1));
    }
}