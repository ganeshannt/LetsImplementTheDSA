package com.practise.ds.graph;

public class AdjacencyMatrix {

    int adjMatrix[][];

    public AdjacencyMatrix(int v, int e) {
        adjMatrix = new int[v][e];
    }

    public static void main(String[] args) {
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix(4, 4);
        adjacencyMatrix.addEdge(1, 0);
        adjacencyMatrix.addEdge(0, 2);
        adjacencyMatrix.printAdjacencyMatrix();
    }

    private void addEdge(int v1, int v2) {
        if (v1 == v2) {
            System.out.println("Pointing to same vertices");
            return;
        }
        adjMatrix[v1][v2] = 1;
        adjMatrix[v2][v1] = 1;
    }

    private void removeEdge(int v1, int v2) {
        if (v1 == v2) {
            System.out.println("Pointing to same vertices");
            return;
        }
        adjMatrix[v1][v2] = 0;
        adjMatrix[v2][v1] = 0;
    }

    private void printAdjacencyMatrix() {
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                System.out.println(adjMatrix[i][j]);
            }
            System.out.println();
        }
    }
}