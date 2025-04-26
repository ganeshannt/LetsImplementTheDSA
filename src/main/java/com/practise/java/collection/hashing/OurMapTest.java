package com.practise.java.collection.hashing;

public class OurMapTest {

    public static void main(String[] args) {
        OurMap<Integer, String> map = new OurMap<>();
        map.put(0, "A");
        map.put(1, "B");
        map.put(2, "C");
        map.put(3, "D");

        for (int i = 0; i < map.size(); i++) {
            System.out.println(map.get(i));
        }
    }
}
