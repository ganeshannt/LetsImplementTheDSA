package com.practise.problemsolving.string;

import org.junit.Assert;
import org.junit.Test;

public class RunLengthEncoding {

    // tc=o(n)
    // sc=o(1)
    private int bestApproach(char[] chararr) {
        if (chararr.length == 1)
            return 1;
        int count = 0;
        for (int i = 0; i < chararr.length; i++) {
            int lettercount = 1;
            while (i < chararr.length - 1 && chararr[i] == chararr[i + 1]) {
                i++;
                lettercount++;
            }
            chararr[count++] = chararr[i];
            if (lettercount > 1) {
                String cnt = lettercount + "";
                for (char c : cnt.toCharArray()) {
                    chararr[count++] = c;
                }
            }
        }
        return count;
    }

    @Test
    public void testReverseWord() {
        RunLengthEncoding encoding = new RunLengthEncoding();
        char[] input = { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a',
                'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a',
                'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a',
                'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a',
                'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' };
        Assert.assertEquals(4, encoding.bestApproach(input));
    }
}
