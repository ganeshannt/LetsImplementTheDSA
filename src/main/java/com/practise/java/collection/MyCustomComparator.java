package com.practise.java.collection;

import java.util.Comparator;


/*

Why do we need a Comparator interface?

* 1. If we want to sort the objects based on multiple properties, then we should go for a Comparator interface. (Custom sorting).
Pre-defined classes Integer, String, etc. already have a Comparable interface implemented, but if we want to sort based on multiple properties,
we need to implement a Comparator interface because we can't modify their Comparable interface implementation.
* 2. compare() method is present in a Comparator interface, and based on its implementation-only sorting will be done.
* 3. It can be a lambda expression instead of a separate class

How does the Comparator interface work?

We need to implement the compare() method of the Comparator interface.

If two objects are equal, then return 0
if the first object is greater than the second object, then return +ve value
if the first object is less than the second object, then return -ve value. Pretty same as compareTo() method of Comparable interface


Notable difference between comparable and comparator:

Comparable affects the original class, i.e., the actual class is modified.
Comparator doesn't affect the original class, i.e., the actual class is not modified.

* */


public class MyCustomComparator implements Comparator<Student> {


    @Override
    public int compare(Student o1, Student o2) {
        System.out.println("Comparator interface compare() method called");
        return o2.getPhysics() - o1.getPhysics();
    }
}
