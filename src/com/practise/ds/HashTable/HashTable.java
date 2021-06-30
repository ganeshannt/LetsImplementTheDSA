package com.practise.ds.HashTable;

public class HashTable {

    public int[] hashtable;

    public HashTable() {
        this.hashtable = new int[10];
    }

    // Hash function to calculate the poisition to store the data
    private int hashKey(int value, int i) {
        System.out.println((value + i) / 10);
        return ((value + i) / 10);
    }

    private boolean isOccupied(int key) {
        return (hashtable[key] != 0);
    }

    public void printHashTable() {
        for (int i = 0; i < hashtable.length; i++) {
            System.out.println("[Key , Value] => [ " + i + "," + hashtable[i] + " ]");
        }
    }

    // TODO: handling collision

    // Hash table functions
    public void putByValue(int value) {
        int i = 0;
        int hash_key = hashKey(value, i++);
        boolean flag = true;
        while (flag) {
            if (isOccupied(hash_key)) {
                hash_key = hashKey(value, i++);
            } else {
                hashtable[hash_key] = value;
                flag = false;
            }
        }
    }

    public void putByKey(int key, int value) {
        hashtable[key] = value;
    }

    public void removeByKey(int key) {
        if (hashtable[key] == 0) {
            System.out.println("Given key do not have value");
        } else {
            hashtable[key] = 0;
        }
    }

    public void removeByValue(int value) {
        int i = 0;
        int hash_key = hashKey(value, i);
        boolean flag = true;
        while (flag) {
            if (hashtable[hash_key] == value) {
                hashtable[hash_key] = 0;
                flag = false;
            } else {
                hash_key = hashKey(value, i++);
            }
        }
    }

    public int getValue(int key) {
        return hashtable[key];
    }

    public int getKey(int value) {
        int i = 0;
        boolean flag = true;
        int hash_key;
        while (flag) {
            hash_key = hashKey(value, i++);
            if (hashtable[hash_key] == value) {
                flag = false;
                return hash_key;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        hashTable.putByValue(10);
        hashTable.putByValue(80);
        hashTable.putByValue(20);
        hashTable.putByValue(30);
        hashTable.putByValue(30);
        hashTable.putByValue(40);
        hashTable.printHashTable();
    }
}
