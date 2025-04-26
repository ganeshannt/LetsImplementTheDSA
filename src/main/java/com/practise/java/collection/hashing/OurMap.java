package com.practise.java.collection.hashing;

import java.util.ArrayList;
import java.util.List;


/*
 * Reference: https://www.youtube.com/watch?v=-oafFAPgLao , https://www.youtube.com/watch?v=sHpjxXX81Gw&t=2411s
 *
 * Separate chaining used as a hash collision resolution technique here.
 * */


public class OurMap<K, V> {
    private final int INITIAL_CAPACITY = 16;
    private List<MapNode<K, V>> bucket;
    private int capacity;
    private int size;

    public OurMap() {
        this.bucket = new ArrayList<>();
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
        for (int i = 0; i < capacity; i++) {
            bucket.add(null);
        }
    }

    public V get(K key) {
        int index = key.hashCode() % capacity;
        MapNode<K, V> head = bucket.get(index);
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public void put(K key, V value) {
        int index = key.hashCode() % capacity;
        MapNode<K, V> head = bucket.get(index);
        MapNode<K, V> newNode = new MapNode<>(key, value, null, null);
        if (head == null) {
            bucket.set(index, newNode);
            size++;
        } else {
            while (head != null) {
                if (head.key.equals(key)) {
                    head.value = value;
                    return;
                }
                head = head.next;
            }
            head = bucket.get(index);
            newNode.next = head;
            head.prev = newNode;
            bucket.set(index, newNode);

            /*
             * Based on load factor, will increase the bucket size.
             * Load factor = number element present in the bucket or size / capacity
             * if the bucket filled more than 70% then rehashing will be done to double the capacity.
             * The already existing entries of the map are copied, and new entries are added to this increased hashmap.
             * */

            double loadFactor = (1.0 * size) / capacity;

            System.out.println("inserting key: " + key);
            System.out.println("load factor: " + loadFactor);

            if (loadFactor > 0.75)
                rehash();

            size++;
        }
    }

    public V remove(K key) {
        int index = key.hashCode() % capacity;
        MapNode<K, V> head = bucket.get(index);
        if (head == null) {
            return null;
        }
        while (head != null) {
            if (head.key.equals(key)) {
                if (head.prev == null) {
                    bucket.set(index, head.next);
                } else {
                    head.prev.next = head.next;
                }
                if (head.next != null) {
                    head.next.prev = head.prev;
                }
                size--;
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public void rehash() {
        System.out.println("Rehashing...");
        List<MapNode<K, V>> temp = bucket;
        bucket = new ArrayList<>();
        capacity = capacity * 2;
        size = 0;
        for (int i = 0; i < capacity; i++) {
            bucket.add(null);
        }

        for (MapNode<K, V> head : temp) {
            while (head != null) {
                put(head.key, head.value);
                head = head.next;
            }
        }
    }


    private class MapNode<K, V> {
        K key;
        V value;
        MapNode<K, V> prev;
        MapNode<K, V> next;

        public MapNode(K key, V value, MapNode<K, V> prev, MapNode<K, V> next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
