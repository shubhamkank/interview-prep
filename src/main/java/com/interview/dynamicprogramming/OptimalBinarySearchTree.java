package com.interview.dynamicprogramming;

public class OptimalBinarySearchTree {

    /* Time complexity: O(n^3), Space complexity: O(n^2)
       optCost(i, k) = 0 if i > k
       optCost(i, k) = sum(f[j], j = i..k) + min(optCost(i, r-1) + optCost(r+1, k), r = i..k) */
    public static int optCost(int[] keys, int[] freqs, int n) {
        //Precompute f(i, j) for all i,j in [0, n-1]
        int[][] f = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                if(i == j) {
                    f[i][j] = freqs[i];
                } else {
                    f[i][j] = f[i][j-1] + freqs[j];
                }
            }
        }

        int[][] dp = new int[n+1][n+1];
        for(int i = n-1; i >= 0; i--) {
            for(int j = 0; j < n; j++) {
                int min = Integer.MAX_VALUE;
                for(int r = i; r <= j; r++) {
                    int temp = ((r > i) ? dp[i][r-1] : 0) + ((r < j) ? dp[r+1][j] : 0);
                    if(temp < min) {
                        min = temp;
                    }
                }
                dp[i][j] = f[i][j] + min;
            }
        }
        return dp[0][n-1];
    }

    public static void main(String [] args) {
        System.out.println(optCost(new int[]{10, 12}, new int[]{34, 50}, 2));
        System.out.println(optCost(new int[]{10, 12, 20}, new int[]{34, 8, 50}, 3));
    }
}
