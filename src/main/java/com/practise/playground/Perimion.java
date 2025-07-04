package com.practise.playground;


/**
 * input: abcdefgh , abce
 * output:  true
 * <p>
 * input: aaaaaaaaaabaaaaaaaaaac
 * output: aaaaac
 */


public class Perimion {


    static boolean isExists(String input, String word) {

        if (input.isBlank() || word.isBlank()) return false;

        for (int i = 0; i < input.length(); i++) {
            for (int j = 0; j < word.length(); j++) {
                if (input.charAt(i + j) != word.charAt(j)) {
                    break;
                }
                if (word.length() - 1 == j) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isExists("aaaaaaaaaabaaaaaaaaaac", "aaaaac")); //true
        System.out.println(isExists("abcdefgh", "abce")); //false
        System.out.println(isExists("abcdefgh", "abc")); //true
        System.out.println(isExists("abcdefgh", "defg")); //true
        System.out.println(isExists("abcdefgh", "db")); //false
    }
}
