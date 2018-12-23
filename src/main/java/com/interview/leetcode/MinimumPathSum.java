package com.interview.leetcode;

public class MinimumPathSum {

    /* Dynamic Programming - Bottom-up Approach
       Time complexity: O(mn), Space complexity: O(mn)
       dp[i][j] - minimum path sum to reach (i, j)th element from (0, 0)th element in the grid
       dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + nums[i][j]
       dp[m-1][n-1] is the result we are looking for
     */
    public static int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        int sum = 0;
        for(int j = 0; j < n; j++) {
            sum += grid[0][j];
            dp[0][j] = sum;
        }

        for(int i = 1; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(j == 0) {
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
                }
            }
        }
        return dp[m-1][n-1];
    }

    /* Dynamic Programming - Bottom-up Approach
       Time complexity: O(mn), Space complexity: O(n)
     */
    public static int minPathSum2(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];

        int sum = 0;
        for(int j = 0; j < n; j++) {
            sum += grid[0][j];
            dp[j] = sum;
        }

        for(int i = 1; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(j == 0) {
                    dp[j] = dp[j] + grid[i][j];
                } else {
                    dp[j] = Math.min(dp[j], dp[j-1]) + grid[i][j];
                }
            }
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        int[][] grid1 = new int[][] {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(minPathSum2(grid1));
    }
}
