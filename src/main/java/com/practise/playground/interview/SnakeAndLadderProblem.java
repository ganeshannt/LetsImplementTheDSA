package com.practise.playground.interview;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class SnakeAndLadderProblem {

    static class Entry {
        int vertex;      // Current position (0-based)
        int distance;   // Minimum dice throws so far
    }

    static int minThrow(int n, int arr[]) {
        // The Standard board has 30 squares (0 to 29 in zero-based)
        final int BOARD_SIZE = 30;

        // Create a mapping of snake/ladder positions
        int[] moves = new int[BOARD_SIZE];
        Arrays.fill(moves, -1);

        // Process the snake and ladder pairs (adjusting to 0-based indexing)
        for (int i = 0; i < 2 * n; i += 2) {
            int start = arr[i] - 1;  // Convert 1-based to 0-based
            int end = arr[i + 1] - 1;  // Convert 1-based to 0-based
            moves[start] = end;
        }

        boolean[] visited = new boolean[BOARD_SIZE];
        Queue<Entry> queue = new LinkedList<>();

        // Start from position 0 (square 1 in the game)
        Entry e = new Entry();
        e.vertex = 0;  // Start at index 0
        e.distance = 0;

        visited[0] = true;  // Mark start as visited
        queue.add(e);

        while (!queue.isEmpty()) {
            e = queue.poll();
            int pos = e.vertex;

            // If we reached the final square (index 29)
            if (pos == BOARD_SIZE - 1) {
                return e.distance;
            }

            // Try all dice values
            for (int dice = 1; dice <= 6; dice++) {
                int nextPos = pos + dice;

                // If next position is within board limits
                if (nextPos < BOARD_SIZE) {
                    // If there's a snake or ladder at nextPos, use it
                    if (moves[nextPos] != -1) {
                        nextPos = moves[nextPos];
                    }

                    // If not visited, add to queue
                    if (!visited[nextPos]) {
                        Entry next = new Entry();
                        next.vertex = nextPos;
                        next.distance = e.distance + 1;

                        visited[nextPos] = true;
                        queue.add(next);
                    }
                }
            }
        }
        return -1;
    }
}
