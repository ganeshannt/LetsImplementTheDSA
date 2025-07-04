package com.practise.java.collection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Node {
    String fruit;
    int count;

    Node(String fruit, int count) {
        this.fruit = fruit;
        this.count = count;
    }


    // Override toString() for easy printing of the list
    @Override
    public String toString() {
        return "Node{" +
                "fruit='" + fruit + '\'' +
                ", count=" + count +
                '}';
    }
}

public class SortNodes {
    public static void main(String[] args) {
        // Create a list of Node objects
        List<Node> nodeList = new ArrayList<>();
        nodeList.add(new Node("banana", 10));
        nodeList.add(new Node("apple", 5));
        nodeList.add(new Node("orange", 8));
        nodeList.add(new Node("grape", 15));

        // Print the list before sorting
        System.out.println("Before sorting:");
        for (Node node : nodeList) {
            System.out.println(node);
        }

        // Sort the list using a lambda expression
        nodeList.sort(Comparator.comparing(node -> node.fruit));

        // Print the list after sorting
        System.out.println("\nAfter sorting by fruit name in ascending order:");
        for (Node node : nodeList) {
            System.out.println(node);
        }
    }
}

