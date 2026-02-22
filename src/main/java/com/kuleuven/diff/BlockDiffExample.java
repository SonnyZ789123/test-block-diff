package com.kuleuven.diff;

public class BlockDiffExample {

    public static void main(String[] args) {
        int result1 = foo(-20);

        System.out.println("Result: " + result1);
    }

    public static int foo(int x) {
        if (x < 0) {
            return transform(x) - adjust(x);
        }

        if (x == 0) {
            return 0;
        }

        if (x < 100) {
            return complexPath(x);
        } else {
            return x * 2;
        }
    }

    // ---------- Helper methods with additional branching ----------

    private static int transform(int x) {
        if (x < -50) {
            return x * 3;
        } else {
            return x * 2;
        }
    }

    private static int adjust(int x) {
        if (x % 5 == 0) {
            return 5;
        } else {
            return 1;
        }
    }

    private static int helper(int x) {
        if (x > 5) {
            return x + 3;
        } else {
            return x + 1;
        }
    }

    private static int simple(int x) {
        if (x == 7) {
            return x * 2;
        } else {
            return x - 1;
        }
    }

    private static int combine(int a, int b) {
        if (a > b) {
            return a + b;
        } else {
            return a + b;
        }
    }

    private static int complexPath(int x) {
        if (x % 4 == 0) {
            return transform(simple(x));
        } else {
            if (x % 3 == 0) {
                return combine(x, adjust(x));
            } else {
                return helper(transform(x));
            }
        }
    }
}
