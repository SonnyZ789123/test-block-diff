package com.kuleuven.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Partial test suite: covers x>0 with both z branches, and x<=0.
 * Leaves (y > 0) as PARTIALLY_COVERED (only true taken) and return 3 NOT_COVERED.
 */
public class DeepBranchExampleTest {

    @Test
    public void testPositiveAll() {
        // x>0, y>0, z>0 -> return 1
        assertEquals(1, DeepBranchExample.analyze(5, 5, 5));
    }

    @Test
    public void testPositiveXYNegativeZ() {
        // x>0, y>0, z<=0 -> return 2
        assertEquals(2, DeepBranchExample.analyze(5, 5, -5));
    }

    @Test
    public void testNegativeX() {
        // x<=0 -> return 4
        assertEquals(4, DeepBranchExample.analyze(-5, 0, 0));
    }
}
