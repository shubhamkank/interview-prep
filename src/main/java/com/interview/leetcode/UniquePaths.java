package com.interview.leetcode;

public class UniquePaths {

    /* Dynamic Programming - Bottom-up Approach
       Time complexity: O(mn), Space complexity: O(mn)
       dp[i][j] - total unique paths to reach (i, j) element
       dp[i][j] = dp[i-1][j] + dp[i][j-1] (dp[m-1][n-1] is the result we are looking for)
     */
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for(int k = 0; k < n; k++) {
            dp[0][k] = 1;
        }

        for(int k = 0; k < m; k++) {
            dp[k][0] = 1;
        }

        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    /* Dynamic Programming - Bottom-up Approach
       Time complexity: O(mn), Space complexity: O(n) - Can be made O(min(m, n))
     */
    public static int uniquePaths2(int m, int n) {
        int[] dp = new int[n];

        for(int k = 0; k < n; k++) {
            dp[k] = 1;
        }

        for(int i = 1; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(j > 0) {
                    dp[j] = dp[j] + dp[j-1];
                }
            }
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths2(3, 2));
        System.out.println(uniquePaths2(7, 3));
    }
}
