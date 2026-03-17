package com.kuleuven.example;

/**
 * Example program designed to test edge-level coverage tracking.
 *
 * CFG structure of classify(int x, int y):
 *
 *        [entry]
 *           |
 *      [x > 0 ?]
 *       /      \
 *    true     false
 *     |         |
 *  [y > 0 ?] [y < -10 ?]
 *   /    \      /      \
 * true  false  true   false
 *  |      |     |       |
 * ret 1  ret 2 ret 3   ret 4
 *
 * Branch coverage requires covering all 6 edges (3 decision blocks x 2 branches each).
 *
 * The partial test suite below covers:
 *   - x > 0, y > 0  -> return 1  (edges: entry->xPos, xPos->yPos, yPos->ret1)
 *   - x > 0, y <= 0 -> return 2  (edges: entry->xPos, xPos->yPos, yPos->ret2)
 *   - x <= 0, y < -10 -> return 3 (edges: entry->xNeg, xNeg->yCheck, yCheck->ret3)
 *
 * This leaves the block [y < -10 ?] as PARTIALLY_COVERED (true branch taken, false not).
 * The old heuristic would see PARTIALLY_COVERED and refuse to mark paths through it as
 * covered, even if they take the already-covered true branch. The new edge-level tracker
 * correctly identifies that edge as covered.
 *
 * Missing coverage: x <= 0, y >= -10 -> return 4 (edge: yCheck->ret4)
 */
public class EdgeCoverageExample {

    public static void main(String[] args) {
        System.out.println(classify(5, 3));
    }

    public static int classify(int x, int y) {
        if (x > 0) {
            if (y > 0) {
                return 1;
            } else {
                return 2;
            }
        } else {
            if (y < -10) {
                return 3;
            } else {
                return 4;
            }
        }
    }
}
