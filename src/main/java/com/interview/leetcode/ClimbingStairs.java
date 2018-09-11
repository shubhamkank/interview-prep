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

    public static void main(String [] args) {
        System.out.println(climbStairs4(3));
        System.out.println(climbStairs4(4));
        System.out.println(climbStairs4(5));
    }
}
