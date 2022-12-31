package com.practise.problemsolving.patterns;

/**
 * @author Ganeshan Nagarajan
 * @since 25/12/22
 */


/*
Name - Count and say pattern
Link - https://leetcode.com/problems/count-and-say/

Input: n = 4
Output: "1211"
Explanation:
countAndSay(1) = "1"
countAndSay(2) = say "1" = one 1 = "11"
countAndSay(3) = say "11" = two 1's = "21"
countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"

 */


public class CountAndSay {

    public static void main(String[] args) {
        System.out.println(initialApproach(2));
    }

    private static String initialApproach(int n) {

        if (n == 1) return "1";

        StringBuilder current;
        StringBuilder output = new StringBuilder("11");
        for (int i = 2; i < n; i++) {
            current = output;
            output = new StringBuilder();
            for (int j = 0; j < current.length() - 1; j++) {
                int count = 1;
                while (j < current.length() - 1 && current.charAt(j) == current.charAt(j + 1)) {
                    count += 1;
                    j++;
                }
                output.append(count).append(current.charAt(j));
            }
            if (current.charAt(current.length() - 2) != current.charAt(current.length() - 1)) {
                output.append(1).append(current.charAt(current.length() - 1));
            }
        }
        return output.toString();
    }

}
