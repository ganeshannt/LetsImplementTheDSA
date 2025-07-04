package com.practise.playground.interview;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SnakeAndLadderProblemTest {

    @Test
    void testExampleFromProblemStatement() {
        // From the example: 8 snake/ladder pairs
        // (3,22), (5,8), (11,26), (20,29), (17,4), (19,7), (27,1), (21,9)
        int n = 8;
        int[] arr = {3, 22, 5, 8, 11, 26, 20, 29, 17, 4, 19, 7, 27, 1, 21, 9};

        int result = SnakeAndLadderProblem.minThrow(n, arr);

        assertEquals(3, result, "Example case should return 3 moves");
    }

    @Test
    void testNoSnakesOrLadders() {
        int n = 0;
        int[] arr = {};

        int result = SnakeAndLadderProblem.minThrow(n, arr);

        assertEquals(5, result, "With no snakes/ladders, should take 5 throws to reach end");
    }

    @Test
    void testSingleThrowVictory() {
        // Test case where victory is possible in a single throw
        int n = 1;
        int[] arr = {6, 30}; // Ladder from square 6 to square 30

        int result = SnakeAndLadderProblem.minThrow(n, arr);

        assertEquals(1, result, "Should win in a single throw with a well-placed ladder");
    }

    @Test
    void testOptimalPath() {
        // Create a path where the optimal solution needs to avoid some tempting ladders
        // Ladder from 2->10, 10->25, Snake from 27->5
        // Optimal path is to use the first two ladders, then careful dice throws
        int n = 3;
        int[] arr = {2, 10, 10, 25, 27, 5};

        int result = SnakeAndLadderProblem.minThrow(n, arr);

        assertEquals(3, result, "Should find the optimal path requiring 3 throws");
    }

    @Test
    void testSnakesBlockingProgress() {
        // Place snakes to block progress and test if algorithm finds alternative paths
        // Snake at square 6 (index 5) to square 2 (index 1)
        // Ladder at square 4 (index 3) to square 15 (index 14)
        // Snake at square 17 (index 16) to square 3 (index 2)
        int n = 3;
        int[] arr = {6, 2, 4, 15, 17, 3};

        int result = SnakeAndLadderProblem.minThrow(n, arr);

        assertEquals(4, result, "Should navigate around snakes in 4 throws");
    }

    @Test
    void testLoopsInBoard() {
        // Create a board with loops to test if visited array works properly
        // Ladder from 2->10, ladder from 11->20, snake from 21->11
        // This creates a loop: 11->20->21->11
        int n = 3;
        int[] arr = {2, 10, 11, 20, 21, 11};

        int result = SnakeAndLadderProblem.minThrow(n, arr);

        // The algorithm should avoid infinite loops and find the correct path
        assertEquals(4, result, "Should handle loops and find path in 4 moves");
    }

    @Test
    void testComplexBoard() {
        // Set up a more complex board with multiple snakes and ladders
        int n = 10;
        int[] arr = {
                3, 8,   // Ladder from 3 to 8
                5, 12,  // Ladder from 5 to 12
                9, 18,  // Ladder from 9 to 18
                11, 2,  // Snake from 11 to 2
                14, 25, // Ladder from 14 to 25
                19, 7,  // Snake from 19 to 7
                20, 29, // Ladder from 20 to 29
                22, 15, // Snake from 22 to 15
                24, 26, // Ladder from 24 to 26
                27, 4   // Snake from 27 to 4
        };

        int result = SnakeAndLadderProblem.minThrow(n, arr);

        // The optimal path requires 3 moves
        assertEquals(3, result, "Complex board should be solved in 3 moves");
    }

    @Test
    void testLongRangeMoves() {
        // Test with ladders that make large jumps
        int n = 2;
        int[] arr = {2, 28, 15, 29};

        int result = SnakeAndLadderProblem.minThrow(n, arr);

        assertEquals(2, result, "Should use long ladders efficiently");
    }
}
