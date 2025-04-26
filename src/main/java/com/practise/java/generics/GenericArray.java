package com.practise.java.generics;

import lombok.ToString;

// https://docs.oracle.com/javase/tutorial/java/generics/restrictions.html#createObjects
// https://docs.oracle.com/javase/tutorial/extra/generics/index.html
@ToString
class GenericArray<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int index;
    // Why Object[] and not T[]? - during runtime, the type information is erased, so you cannot create an array of a generic type, hence you need to use Object[].
    // Type erasure ensures that no new classes are created for parameterized types; consequently, generics incur no runtime overhead.
    // During compile time, Type Erasure removes the type parameter and replaces it with the first bound if the type parameter is unbounded.
    // If it is bounded, it replaces it with the first bound. First bound means the first class or interface in the bound list.
    private Object[] array;

    public GenericArray(int capacity) {
        this.index = 0;
        this.array = new Object[capacity];
    }

    public GenericArray() {
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
            while (i < index) { // Only iterate up to the current index
                if ((array[i] == null && element == null) || (array[i] != null && array[i].equals(element))) {
                    break;
                }
                i++;
            }
            if (i == index) { // Element not found
                System.out.println("Element not found. Cannot remove the element.");
            } else {
                for (int j = i; j < index - 1; j++) {
                    array[j] = array[j + 1];
                }
                array[--index] = null; // Clear the last element
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