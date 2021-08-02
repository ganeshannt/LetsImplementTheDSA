package com.practise.ds.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class BFS {

    Queue<Integer> queue;
    int[] distance;
    int[] predecessor;
    String[] color;
    Map<Integer, int[]> graph;

    public BFS(int graph_size) {
        queue = new ArrayDeque<>(graph_size);
        color = new String[graph_size];
        Arrays.fill(color, "white");
        distance = new int[graph_size];
        predecessor = new int[graph_size];
        graph = new HashMap<>(graph_size);
        graph.put(0, new int[] { 1 });
        graph.put(1, new int[] { 0, 2 });
        graph.put(2, new int[] { 1, 3 });
        graph.put(3, new int[] { 2, 4, 5 });
        graph.put(4, new int[] { 3, 5, 7 });
        graph.put(5, new int[] { 3, 4, 7 });
        graph.put(6, new int[] { 7, 5 });
        graph.put(7, new int[] { 4, 5, 6 });
    }

    public void breathFirstSearch(int source) {
        queue.add(source);
        distance[source] = 0;
        color[source] = "gray";
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.println("vertex ->" + current);
            int edges[] = graph.get(current);
            for (int i = 0; i < edges.length; i++) {
                if (color[edges[i]] == "white") {
                    color[edges[i]] = "gray";
                    distance[edges[i]] = distance[current] + 1;
                    predecessor[edges[i]] = current;
                    queue.add(edges[i]);
                }
                color[current] = "black";
            }
        }
    }

    public static void main(String[] args) {
        BFS bfs = new BFS(8);
        bfs.breathFirstSearch(2);
    }
}