package com.interview.dynamicprogramming;

public class FibonacciNumber {

    static int fibo(int n, int [] dp) {
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(fibo(1, new int[2]));
        System.out.println(fibo(2, new int[2]));
        System.out.println(fibo(3, new int[3]));
        System.out.println(fibo(4, new int[4]));
        System.out.println(fibo(5, new int[5]));
        System.out.println(fibo(6, new int[6]));
        System.out.println(fibo(7, new int[7]));
        System.out.println(fibo(8, new int[8]));

    }
}
