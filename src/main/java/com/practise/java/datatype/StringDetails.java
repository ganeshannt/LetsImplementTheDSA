package com.practise.java.datatype;

import java.util.ArrayList;

class StringDetails {

    public static void main(String[] args) {

        howStringStoredReferenced();
        howStringConcatenationWorks();
        howSubStringWorks();
        howStringBuilderWorks();
        howStringBufferWorks();

    }

    private static void howStringBufferWorks() {

        System.out.println("How StringBuffer works in Java");

                /*
                1) StringBuffer is mutable in Java
                2) StringBuffer is slower than StringBuilder for concatenation
                3) StringBuffer is synchronized so thread safe
                4) StringBuffer is present in Java 1.0
                5) StringBuffer is used when we need to modify the string frequently and thread safety is required
                */

        StringBuffer aToZStringBuffer = new StringBuffer("Hello");

        for (int i = 0; i < 26; i++) {
            aToZStringBuffer.append((char) ('a' + i));
        }
        System.out.println(aToZStringBuffer); // Helloabcdefghijklmnopqrstuvwxyz , StringBuffer is mutable so only one object is created not 26 objects
    }

    private static void howStringBuilderWorks() {

        System.out.println("How StringBuilder works in Java");

            /*
            1) StringBuilder is mutable in Java
            2) StringBuilder is faster than String for concatenation
            3) StringBuilder is not synchronized so not thread safe
            4) StringBuilder is present in Java 1.5 and late
            5) StringBuilder is used when we need to modify the string frequently
             */

        StringBuilder aToZStringBuilder = new StringBuilder("Hello");

        for (int i = 0; i < 26; i++) {
            aToZStringBuilder.append((char) ('a' + i));
        }

        System.out.println(aToZStringBuilder); // Helloabcdefghijklmnopqrstuvwxyz , StringBuilder is mutable so only one object is created not 26 objects

    }

    private static void howSubStringWorks() {

        System.out.println("How substring works in Java");

        String s = "Hello World";
        System.out.println(s.substring(6)); // World
        System.out.println(s.substring(0, 5)); // Hello
        System.out.println(s.substring(0, 0)); // Empty string
        System.out.println(s.substring(0, 11)); // Hello World
        System.out.println(s.substring(0, 12)); // Hello World
        System.out.println(s.substring(0, 13)); // Hello World
    }

    private static void howStringConcatenationWorks() {

        System.out.println("How String concatenation works in Java");

            /*
            1) String concatenation is done using + operator
            2) If one of the operands is a string, the other operand is converted to a string using the toString() method of the operand
            3) If both operands are numbers, they are added
            4) If both operands are strings, they are concatenated
            5) If one of the operands is a string and the other is a number, the number is converted to a string and concatenated
            6) if operand is char and other is string, char is converted to string and concatenated
            */

        System.out.println("Hello" + 1); // Hello1
        System.out.println(1 + 2); // 3
        System.out.println("Hello" + 1 + 2); // Hello12
        System.out.println('a' + 1); // 98, char 'a' is converted to ASCII value 97 and added to 1
        System.out.println((char) ('a' + 1)); // b, char 'a' is converted to ASCII value 97 and added to 1 and then converted back to char
        System.out.println("Hello" + 'a' + 1); // Helloa1
        System.out.println("Hello" + new ArrayList<>()); // Hello[],  toString() method of ArrayList is called
        System.out.println("Hello" + new Object()); // Hellojava.lang.Object@5e2de80c , toString() method of Object is called
        System.out.println("Hello" + null); // Hellonull , null is converted to string
    }

    private static void howStringStoredReferenced() {

        System.out.println("How Strings are stored and referenced in Java");

        /*
         1) == checks if both references point to the same memory location
         2) .equals() checks if the content of the String are same
         3) Strings are immutable in Java
         4) String Pool is a memory area in Java heap where all the string literals are stored not the string objects
         5) When a string is created using new keyword, it is stored in the heap memory not in the String Pool
         */


        String s1 = "ganeshan"; // Stored in String Pool
        String s2 = "ganeshan"; // Reuses the same literal from String Pool
        String s3 = new String("ganeshan"); // Creates a new object with value in the heap. this is not in the String Pool
        String s4 = new String("ganeshan"); // Creates a new object with value in the heap. this is not in the String Pool


        System.out.println(s1 == s2); // true, both point to the same String Pool literal
        System.out.println(s1 == s3); // false, different memory locations
        System.out.println(s3 == s4); //false, different memory locations

        System.out.println(s3.equals(s2)); //true, content is same so true. equals checks content/value
        System.out.println(s3.equals(s4)); //true, content is same so true. equals checks content/value

        s2 = s3;
        System.out.println(s2 == s3); //true because s2 now reference s3
        s2 = "ganeshan";
        s4 = "ganeshan";
        System.out.println(s1 == s2); // true, again both point to the same String Pool literal
        System.out.println(s1 == s4); // true, both point to the same String Pool literal
    }
}
