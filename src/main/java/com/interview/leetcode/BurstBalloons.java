package com.interview.leetcode;

public class BurstBalloons {

    /* Dynamic Programming (Bottom-up iterative approach) - Similar to matrix chain multiplication problem
       Reverse thinking - last balloon k to burst in a subarray(i to j) in order to maximize coins in that subarray
       Time complexity: O(n^3), Space complexity: O(n^2)
       dp[i][j] - coins obtained by bursting all the balloons between index i and j (included)
       dp[i][j] = max(nums[i-1] * nums[k] * nums[j+1] + dp[i][k-1] + dp[k+1][j]) k = [i, j]
       Loop traversal - diagonally or row from bottom to top and column left to right
     */
    public static int maxCoins(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] newNums = new int[n + 2];
        for(int i = 1; i <= n; i++) {
            newNums[i] = nums[i - 1];
        }
        newNums[0] = 1;
        newNums[n + 1] = 1;


        int[][] dp = new int[n + 2][n + 2];

        for(int i = n; i >= 1; i--) {
            for(int j = i; j <= n; j++) {
                for(int k = i; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], newNums[i - 1] * newNums[k] * newNums[j + 1] + dp[i][k - 1] + dp[k+1][j]);
                }
            }
        }
        return dp[1][n];
    }

    /* Divide and Conquer (Top-down recursion with memoization approach)
       Reverse thinking - last balloon k to burst in a subarray(i to j) in order to maximize coins in that subarray
       Time complexity: O(n^3), Space complexity: O(n^2)
       dp[i][j] - coins obtained by bursting all the balloons between index i and j (included)
       dp[i][j] = max(nums[i-1] * nums[k] * nums[j+1] + dp[i][k-1] + dp[k+1][j]) k = [i, j]
       Loop traversal - diagonally or row from bottom to top and column left to right
     */
    public static int maxCoins2(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] newNums = new int[n + 2];
        for(int i = 1; i <= n; i++) {
            newNums[i] = nums[i - 1];
        }
        newNums[0] = 1;
        newNums[n + 1] = 1;

        int[][] memo = new int[n + 2][n + 2];
        return helper(memo, newNums, 1, n);
    }

    private static int helper(int[][] memo, int[] nums, int left, int right) {
        if(left > right) {
            return 0;
        }

        if(memo[left][right] > 0) {
            return memo[left][right];
        }

        int maxCoins = 0;
        for(int k = left; k <= right; k++) {
            maxCoins = Math.max(maxCoins, nums[left - 1] * nums[k] * nums[right + 1] +
                    helper(memo, nums, left, k - 1) + helper(memo, nums, k + 1, right));
        }
        memo[left][right] = maxCoins;
        return maxCoins;
    }

    public static void main(String[] args) {
        System.out.println(maxCoins(new int[]{3, 1, 5, 8}));
        System.out.println(maxCoins2(new int[]{3, 1, 5, 8}));
    }
}
