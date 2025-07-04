package com.practise.playground.interview;

class InterviewQuestions {

    public static String vowelConversion(String str, String vowel, int k) {
        char[] word = str.toCharArray();

        for (int i = 0; i < word.length; i++) {
            int index = vowel.indexOf(word[i]);
            if (index != -1) {
                int l = (k + index) % vowel.length();
                word[i] = vowel.charAt(l);
            }
        }
        return String.valueOf(word);
    }

}
