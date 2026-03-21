package com.kuleuven.example;

/**
 * Example demonstrating the difference between old (static block-level) and
 * new (runtime edge-level) coverage heuristics.
 *
 * The key: the PARTIALLY_COVERED block (y > 0) is NOT the root node,
 * so the old heuristic's pathIsBlockCovered does NOT skip it.
 *
 * CFG:
 *          [Block 0: x > 0 ?]           COVERED (both branches taken)
 *           /              \
 *        true             false
 *          |                |
 *   [Block 1: y > 0 ?]  [Block 5: return 4]   PARTIALLY_COVERED / COVERED
 *      /         \
 *   true        false
 *     |           |
 * [Block 2:    [Block 4: return 3]             COVERED / NOT_COVERED
 *  z > 0 ?]
 *   /      \
 * true    false
 *  |        |
 * [Block 3: [Block 3b:
 *  return 1] return 2]                         COVERED / COVERED
 *
 * Partial test suite covers:
 *   - analyze(5, 5, 5)   -> x>0, y>0, z>0   -> return 1
 *   - analyze(5, 5, -5)  -> x>0, y>0, z<=0  -> return 2
 *   - analyze(-5, 0, 0)  -> x<=0             -> return 4
 *
 * Block coverage:
 *   Block 0 (x > 0):  COVERED           (both true and false taken)
 *   Block 1 (y > 0):  PARTIALLY_COVERED (only true branch taken)
 *   Block 2 (z > 0):  COVERED           (both true and false taken)
 *   return 1:         COVERED
 *   return 2:         COVERED
 *   return 3:         NOT_COVERED
 *   return 4:         COVERED
 *
 * OLD heuristic (pathIsBlockCovered, skips root only):
 *   Path x>0, y>0, z>0:   Block 1 PARTIALLY_COVERED -> OK  (REDUNDANT!)
 *   Path x>0, y>0, z<=0:  Block 1 PARTIALLY_COVERED -> OK  (REDUNDANT!)
 *   Path x>0, y<=0:       Block 1 PARTIALLY_COVERED -> OK  (correct)
 *   Path x<=0:            Block 0 is root (skipped), Block 5 COVERED -> IGNORE
 *   Result: 3 OK, 1 IGNORE
 *
 * NEW heuristic (runtime edge tracking, conservative init):
 *   Path x>0, y>0, z>0:   Edge 1->2 not pre-marked (conservative) -> OK, records edge 1->2
 *   Path x>0, y>0, z<=0:  Edge 1->2 NOW marked (runtime!) -> ALL covered -> IGNORE!
 *   Path x>0, y<=0:       Edge 1->3 not marked -> OK (correct)
 *   Path x<=0:            Edge 0->5 pre-marked (Block 0 COVERED) -> IGNORE
 *   Result: 2 OK, 2 IGNORE
 */
public class DeepBranchExample {

    public static void main(String[] args) {
        System.out.println(analyze(5, 5, 5));
    }

    public static int analyze(int x, int y, int z) {
        if (x > 0) {
            if (y > 0) {
                if (z > 0) {
                    return 1;
                } else {
                    return 2;
                }
            } else {
                return 3;
            }
        } else {
            return 4;
        }
    }
}
