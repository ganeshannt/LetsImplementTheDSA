package com.practise.java.generics;

import lombok.ToString;


/*
    What is wildcard in generics?
    A wildcard is a special kind of type argument that allows you to specify a type as a parameter to a method, or a variable.
    The wildcard can be used to represent an unknown type or a family of unknown types.
    The wildcard is represented by the question mark (?).


    What is the difference between T and ? in generics?
    You cannot use a wildcard (?) as a type parameter in a class declaration.
    Wildcards are used to specify unknown types in method parameters, return types, and field declarations, but not in the class or interface type parameter itself.
    The reason is that a wildcard represents an unknown type, and you cannot define a class with an unknown type parameter. Instead, you should use a specific type parameter, such as T, and then you can use bounds to restrict the types that can be used.

    The wildcard can be used in three ways:
    1. Unbounded Wildcard: ?
    2. Upper Bounded Wildcard: ? extends T
    3. Lower Bounded Wildcard: ? super T

    Unbounded Wildcard Example: [it can hold any type of object]
    List<?> list = new ArrayList<>();
    list.add("Hello"); // Compile-time error
    list.add(10); // Compile-time error
    list.add(10.5); // Compile-time error

    Valid operations:
    list.get(0); // Valid
    list.remove(0); // Valid


    Upper Bounded Wildcard Example: [it can hold any type of object that is a subclass of Number]
    List<? Extends Number> list = new ArrayList<>();
    list.add(10); // Compile-time error
    list.add(10.5); // Compile-time error
    list.add(new Integer(10)); // Compile-time error

    Valid operations:
    list.get(0); // Valid
    list.remove(0); // Valid


    Lower Bounded Wildcard Example: [it can hold any type of object that is a superclass of Integer]
    List<? Super Integer> list = new ArrayList<>();
    list.add(10); // Valid
    list.add(new Integer(10)); // Valid
    list.add(10.5); // Compile-time error
    list.add("Hello"); // Compile-time error

    Valid operations:
    list.get(0); // Compile-time error
    list.remove(0); // Compile-time error



 */

@ToString
class GenericArrayWildCard<T extends Number> {
    private static final int DEFAULT_CAPACITY = 10;
    private int index;
    // Why Object[] and not T[]? - during runtime, the type information is erased, so you cannot create an array of a generic type, hence you need to use Object[].
    // Type erasure ensures that no new classes are created for parameterized types; consequently, generics incur no runtime overhead.
    // During compile time, Type Erasure removes the type parameter and replaces it with the first bound if the type parameter is unbounded.
    // If it is bounded, it replaces it with the first bound. First bound means the first class or interface in the bound list.
    private Object[] array;

    public GenericArrayWildCard(int capacity) {
        this.index = 0;
        this.array = new Object[capacity];
    }

    public GenericArrayWildCard() {
        this.index = 0;
        this.array = new Object[DEFAULT_CAPACITY];
    }


    public void add(T element) {
        if (isFull()) {
            resize();
        }
        array[index++] = element;
    }

    public T get(int index) {
        if (index >= 0 && index < array.length) {
            return (T) array[index];
        } else {
            System.out.println("Invalid index. Cannot get the element.");
            return null;
        }
    }

    public void remove(T element) {
        if (isEmpty()) {
            System.out.println("Array is empty. Cannot remove the element.");
        } else {
            int i = 0;
            while (i < array.length) {
                if (array[i].equals(element)) {
                    break;
                }
                i++;
            }
            if (i == array.length) {
                System.out.println("Element not found. Cannot remove the element.");
            } else {
                for (int j = i; j < array.length - 1; j++) {
                    array[j] = array[j + 1];
                }
                index--;
            }
        }
    }

    private void resize() {
        Object[] newArray = new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }


    private boolean isFull() {
        return index >= array.length;
    }

    private boolean isEmpty() {
        return index == 0;
    }
}