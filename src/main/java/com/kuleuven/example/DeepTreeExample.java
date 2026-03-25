package com.kuleuven.example;

public class DeepTreeExample {

    public static void main(String[] args) {
        int result = compute(30, 3);
        System.out.println("Result: " + result);
    }

    // ======================== DEPTH 1 ========================

    public static int compute(int x, int y) {
        if (x > 0) {
            return categorize(x) + process(y);
        } else if (x == 0) {
            return y;
        } else {
            return negate(x, y);
        }
    }

    // ======================== DEPTH 2 ========================

    private static int categorize(int x) {
        switch (x % 3) {
            case 0:
                return scale(x, 2);
            case 1:
                return scale(x, 3);
            default:
                return scale(x, 5);
        }
    }

    private static int process(int y) {
        if (y > 5) {
            return blend(y, 10);
        } else {
            return blend(y, y * 2);
        }
    }

    private static int negate(int x, int y) {
        if (x < -10) {
            return reduce(x) + y;
        } else {
            return reduce(x) - y;
        }
    }

    // ======================== DEPTH 3 ========================

    private static int scale(int x, int factor) {
        if (x * factor > 20) {
            return boost(x * factor);
        } else {
            return x * factor;
        }
    }

    private static int blend(int a, int b) {
        if (a > b) {
            return step(a - b);
        } else {
            return step(b - a);
        }
    }

    private static int reduce(int x) {
        switch (x % 4) {
            case 0:
                return clamp(x, -100, 100);
            case 1:
                return clamp(-x, 0, 50);
            case 2:
                return clamp(x * 2, -200, 200);
            default:
                return clamp(x, -50, 50);
        }
    }

    // ======================== DEPTH 4 ========================

    private static int boost(int x) {
        if (x > 50) {
            return cap(x);
        } else {
            return x + 5;
        }
    }

    private static int step(int x) {
        if (x > 3) {
            return normalize(x);
        } else {
            return x + 1;
        }
    }

    private static int clamp(int x, int low, int high) {
        if (x < low) {
            return sign(low);
        } else if (x > high) {
            return sign(high);
        } else {
            return sign(x);
        }
    }

    // ======================== DEPTH 5 (leaves) ========================

    private static int cap(int x) {
        if (x > 100) {
            return 100;
        } else {
            return x;
        }
    }

    private static int normalize(int x) {
        if (x > 10) {
            return 10;
        } else {
            return x;
        }
    }

    private static int sign(int x) {
        if (x > 0) {
            return 1;
        } else if (x < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
