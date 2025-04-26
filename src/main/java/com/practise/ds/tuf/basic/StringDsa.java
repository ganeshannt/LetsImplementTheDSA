package com.practise.ds.tuf.basic;

import java.util.*;

class StringDsa {


    public static void reverseString(Vector<Character> s) {
        int start = 0;
        int end = s.size() - 1;

        while (start < end) {
            char a = s.get(start);
            char b = s.get(end);
            s.set(end--, a);
            s.set(start++, b);
        }
    }


    public static boolean palindromeCheck(String s) {
        int start = 0;
        int end = s.length() - 1;

        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }

    public static String largeOddNum(String s) {

        int startIndex = 0;
        int endIndex = s.length() - 1;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                startIndex += 1;
            } else {
                break;
            }
        }

        while (endIndex > 0) {
            if (s.charAt(endIndex) % 2 != 0) {
                break;
            }
            endIndex--;
        }

        return s.substring(startIndex, endIndex + 1);

    }


    public static String longestCommonPrefix(String[] str) {
        if (str.length == 1) return str[0];

        Arrays.sort(str); // sorting is the key to solve this

        String first = str[0];
        String last = str[str.length - 1];

        int lcpLength = 0;

        while (first.charAt(lcpLength) == last.charAt(lcpLength)) {
            lcpLength++;
        }
        return first.substring(0, lcpLength);
    }


    public static boolean isomorphicString(String s, String t) {
        if (s.length() != t.length()) return false;

        HashMap<Character, Character> strMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            strMap.putIfAbsent(s.charAt(i), t.charAt(i));
            if (strMap.get(s.charAt(i)) != t.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean rotateStringWithoutDuplicates(String s, String goal) {
        if (s.equals(goal)) return true;

        if (s.length() != goal.length()) return false;

        int startingIndex = goal.indexOf(s.charAt(0));

        for (int i = 0; i < s.length(); i++) {
            if (startingIndex >= goal.length()) {
                startingIndex = startingIndex - goal.length();
            }

            if (s.charAt(i) != goal.charAt(startingIndex)) {
                return false;
            }
            startingIndex++;
        }
        return true;
    }

    public static boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) return false;
        if (s.equals(goal)) return true;

        int n = s.length();
        for (int i = 0; i < n; i++) {
            boolean isRotation = true;
            for (int j = 0; j < n; j++) {
                if (s.charAt(j) != goal.charAt((i + j) % n)) {
                    isRotation = false;
                    break;
                }
            }
            if (isRotation) return true;
        }
        return false;
    }

    public static boolean rotateStringOptimal(String s, String goal) {
        if (s.length() != goal.length()) return false;
        String doubled = s + s;
        return doubled.contains(goal);
    }


    public static boolean anagramStrings(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] c1 = s.toCharArray();
        char[] c2 = t.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        return Arrays.equals(c1, c2);
    }


    public static List<Character> frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        List<Character> characterList = new ArrayList<>(s.length());

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            characterList.add(entry.getValue(), entry.getKey());
        }


        return characterList;

    }

    public static void main(String[] args) {
//        Vector<Character> str =
//                new Vector<>(Arrays.asList('h', 'e', 'l', 'l', 'o'));
//
//        reverseString(str);
        //largeOddNum("0214638");

        //System.out.println(longestCommonPrefix(new String[]{"apple", "app"}));
//        System.out.println(rotateString("mlmonmopyiltrvgbijkqihpzkmtions", "nsmlmonmopyiltrvgbijkqihpzkmtio"));

        StringDsa s = new StringDsa();

        s.frequencySortOptimized("bbccddaaa").forEach(System.out::println);

    }

    public List<Character> frequencySortOptimizedUsingStream(String s) {
        Pair[] charAndFreqPair = new Pair[26];

        for (char c : s.toCharArray()) {
            int freq = 0;
            if (charAndFreqPair[c - 'a'] != null) freq = charAndFreqPair[c - 'a'].getFrequency();
            charAndFreqPair[c - 'a'] = new Pair(freq + 1, c);
        }

        return Arrays.stream(charAndFreqPair).filter(Objects::nonNull).sorted((a, b) -> {
            if (a.getFrequency() == b.getFrequency()) {
                return a.getLetter() - b.getLetter();
            }
            return b.getFrequency() - a.getFrequency();
        }).map(Pair::getLetter).toList();
    }

    public List<Character> frequencySortOptimized(String s) {
        Pair[] charAndFreqPair = new Pair[26];


        for (int i = 0; i < 26; i++) {
            charAndFreqPair[i] = new Pair(0, (char) ('a' + i));
        }


        for (char c : s.toCharArray()) {
            charAndFreqPair[c - 'a'].setFrequency(charAndFreqPair[c - 'a'].getFrequency() + 1);
        }

        Arrays.sort(charAndFreqPair, (a, b) -> {
            return b.getFrequency() - a.getFrequency();
        });

        List<Character> characterList = new ArrayList<>();

        for (Pair p : charAndFreqPair) {
            if (p.getFrequency() != 0) characterList.add(p.letter);
        }

        return characterList;
    }

    public class Pair {
        private int frequency;
        private char letter;

        public Pair(int frequency, char letter) {
            this.frequency = frequency;
            this.letter = letter;
        }

        public int getFrequency() {
            return frequency;
        }

        public void setFrequency(int frequency) {
            this.frequency = frequency;
        }

        public char getLetter() {
            return letter;
        }

        public void setLetter(char letter) {
            this.letter = letter;
        }
    }
}
