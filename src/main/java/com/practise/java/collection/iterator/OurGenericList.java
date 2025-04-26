package com.practise.java.collection.iterator;

/*
 we will cover why iterable is needed in the collection interface and how to implement it and what it offers.
 */

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class OurGenericList<T> implements Iterable<T> {
    private T[] item;
    private int size;

    public OurGenericList(int size) {
        this.size = 0;
        item = (T[]) new Object[size];
    }

    public OurGenericList() {
        this.size = 0;
        item = (T[]) new Object[100];
    }

    public void add(T t) {
        item[size++] = t;
    }

    public T get(int index) {
        return item[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new OurGenericListIterator(this);
    }

    /*
     *
     * */
    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }


    /*
    The Spliterator interface, introduced in Java 8, can traverse and partition sequences.
    It’s a base utility for Streams, especially parallel ones.
     */
    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }

    /*
     * Why is class implemented iterator interface private?
     * 1. It is unnecessary to expose the iterator class to the outside world.
     * */
    private class OurGenericListIterator implements Iterator<T> {
        private final OurGenericList<T> list;
        private int currentIndex = 0;


        public OurGenericListIterator(OurGenericList<T> genericList) {
            this.list = genericList;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < list.size;
        }

        @Override
        public T next() {
            return list.item[currentIndex++];
        }
    }
}


