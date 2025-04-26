package com.practise.java.collection.iterator;

public class IteratorTester {

    public static void main(String[] args) {
        OurGenericList<String> list = new OurGenericList<>();
        list.add("Hello");
        list.add("World");
        list.add("Java");

        // this for each loop internally uses iterator.
        // for each loop is just a syntactic sugar and compiler converts it to iterator like below in while loop
        for (String s : list) {
            System.out.println(s);
        }

//        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }

    }
}
