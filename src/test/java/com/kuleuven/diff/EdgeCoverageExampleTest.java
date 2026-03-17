package com.kuleuven.diff;

import com.kuleuven.example.EdgeCoverageExample;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Partial test suite for EdgeCoverageExample.classify(int, int).
 *
 * Covers 5 of 6 CFG edges. Leaves one uncovered:
 *   - The false branch of (y < -10) -> return 4
 *
 * This makes the (y < -10) block PARTIALLY_COVERED in the pathcov block map.
 */
public class EdgeCoverageExampleTest {

    @Test
    public void testPositiveXPositiveY() {
        // Covers: entry->xPos, xPos->yPos, yPos->ret1
        assertEquals(1, EdgeCoverageExample.classify(5, 3));
    }

    @Test
    public void testPositiveXNegativeY() {
        // Covers: entry->xPos, xPos->yPos, yPos->ret2
        assertEquals(2, EdgeCoverageExample.classify(5, -3));
    }

    @Test
    public void testNegativeXVeryNegativeY() {
        // Covers: entry->xNeg, xNeg->yCheck, yCheck->ret3
        assertEquals(3, EdgeCoverageExample.classify(-1, -20));
    }
}
