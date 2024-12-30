package com.practise.ps.patterns;

/**
 * @author Ganeshan Nagarajan
 * @since 07/12/24
 */

public class MemoryExample {

    public static void main(String[] args) {

        int age = 1000; // Directly stored on stack
        double height = 1.75; // Directly stored on stack
        boolean isStudent = true; // Directly stored on stack
        Integer value = 1000;
        System.out.println(age==value);

        String s1 = "hello"; // Stored in String Pool
        String s2 = "hello"; // Reuses the same literal from String Pool
        String s3 = new String("hello"); // Creates a new object in the heap
        System.out.println(s1 == s2); // true, both point to the same String Pool literal
        System.out.println(s1 == s3); // false, different memory locations

        System.out.println(s3.equals(s2)); // content is same so true. equals checks content/value

        String s4 = "hello";
        s2 = s3;
        System.out.println(s2 == s3); //true because s2 now reference s3
        s2 = "hello";
        System.out.println(s1 == s2); // true, again both point to the same String Pool literal
        System.out.println(s1 == s4); // true, both point to the same String Pool literal
    }
}
