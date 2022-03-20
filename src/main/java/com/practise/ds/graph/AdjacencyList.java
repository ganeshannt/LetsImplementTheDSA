package com.practise.ds.graph;

public class AdjacencyList {

    Node arr[];

    public AdjacencyList(int size) {
        this.arr = new Node[size];
    }

    public static void main(String[] args) {
        AdjacencyList adjacencyList = new AdjacencyList(4);
        adjacencyList.addEdge(1, 0);
    }

    private void addEdge(int v1, int v2) {
        Node node = new Node(v2);
        if (arr[v1] == null) {
            arr[v1] = node;
            return;
        }
        Node temp = arr[v1];
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    private void removeEdge(int v1, int v2) {
        Node temp = arr[v1];
//        if()
//        while(temp.next!=null){
//            if(temp.vertex == v2){
//
//            }
//        }
    }

    public class Node {
        int vertex;
        Node next;

        public Node(int vertex) {
            this.vertex = vertex;
            this.next = null;
        }
    }
}