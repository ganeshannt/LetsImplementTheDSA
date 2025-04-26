package com.practise.java.generics;

import java.util.ArrayList;
import java.util.List;

public class GenericsTester {

    // A generic method that can print any type of array.
    public static <E> void printArray(E[] array) {
        for (E element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Create a GenericArray to hold an Integer.
        GenericArray<Integer> integerGenericArray = new GenericArray<>(5);
        System.out.println("Integer GenericArray: " + integerGenericArray);

        // Create a GenericArray to hold a String.
        GenericArray<String> stringGenericArray = new GenericArray<>();
        stringGenericArray.add("Hello");
        stringGenericArray.add("World");
        System.out.println("String GenericArray: " + stringGenericArray);

        // Create an array of Integers and print it using the generic method.
        Integer[] intArray = {1, 2, 3, 4, 5};
        System.out.print("Integer Array: ");
        printArray(intArray);

        // Create an array of Strings and print it using the generic method.
        String[] strArray = {"A", "B", "C", "D"};
        System.out.print("String Array: ");
        printArray(strArray);


        // you can use any Number types like Integer, Double, Float or Number itself to use integer, double, float or number values.
        GenericArrayWildCard<Number> numberGenericArrayWildCard = new GenericArrayWildCard<>(5);
        numberGenericArrayWildCard.add(10.5);
        numberGenericArrayWildCard.add(20);
        numberGenericArrayWildCard.add(30.5f);
        System.out.println("Integer GenericArrayWildCard: " + numberGenericArrayWildCard);


        // GenericArrayWildCard extends Number, so you can create an instance of GenericArrayWildCard with Numbers not with Strings.
        // GenericArrayWildCard<String> stringGenericArrayWildCard = new GenericArrayWildCard<>(); // Compilation error


        // Unbounded Wildcard Example
        List<? super Integer> list = new ArrayList<>();
        list.add(10); // Valid
        list.add(10); // Valid
    }
}