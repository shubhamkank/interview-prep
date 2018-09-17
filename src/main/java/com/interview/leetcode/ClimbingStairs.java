package com.interview.leetcode;

public class ClimbingStairs {

    // Naive Solution
    // Time complexity: O(2^n), Space complexity: O(n)
    public static int climbStairs(int n) {
        if(n == 1) {
            return 1;
        } else if(n == 2) {
            return 2;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    // Memoization
    // Time complexity: O(n), Space complexity: O(n)
    public static int climbStairs2(int n) {
        return climbStairs(n, new int[n + 1]);
    }

    private static int climbStairs(int n, int[] result) {
        if(n == 1) {
            return 1;
        }
        if(n == 2) {
            return 2;
        }

        if(result[n] != 0) {
            return result[n];
        } else {
            result[n] = climbStairs(n - 1) + climbStairs(n - 2);
            return result[n];
        }
    }


    // Dynamic Programming
    // Time complexity: O(n), Space complexity: O(n)
    public static int climbStairs3(int n) {
        if(n == 1) {
            return 1;
        }
        int [] result = new int[n + 1];
        result[1] = 1;
        result[2] = 2;

        for(int i = 3; i <= n; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
        return result[n];
    }

    // Fibonacci Number
    // Time complexity: O(n), Space complexity: O(1)
    public static int climbStairs4(int n) {
        int first = 0;
        int second = 1;

        for(int i = 0; i < n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }

    // Fibonacci Number - Binets Method
    // Time complexity: O(logn), Space complexity: O(1)
    // M^n = Product(M^(2^p)) where value of p is the position of set bits in n
    // For example, M^7 = M^2^0 * M^2^1 * M^2^2
    public static int climbStairs5(int n) {
        int [][] q = {{1, 1}, {1, 0}};
        int [][] result = pow(q, n);
        return result[0][0];
    }

    private static int[][] pow(int[][] a, int n) {
        int [][] ret = {{1, 0}, {0, 1}};

        while (n > 0) {
            if((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    private static int[][] multiply(int[][] a, int[][] b) {
        int [][] c = new int[2][2];
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }

    // Fibonacci Number - Formula
    // Time complexity: O(logn), Space complexity: O(1) - Math.pow takes logn time
    public static int climbStairs6(int n) {
        double sqrt5 = Math.sqrt(5);
        double fibn = Math.pow((1 + sqrt5)/2, n + 1) - Math.pow((1 - sqrt5)/2, n + 1);
        return (int)(fibn/sqrt5);
    }

    public static void main(String [] args) {
        System.out.println(climbStairs6(3));
        System.out.println(climbStairs6(4));
        System.out.println(climbStairs6(5));
    }
}
