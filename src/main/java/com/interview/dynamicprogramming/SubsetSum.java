package com.interview.dynamicprogramming;

public class SubsetSum {

    /* Time complexity: O(n * T), Space complexity: O(n * T)
       SS(i, t) = true iff some subset of X[i...n] sums to t
       SS(i, t) = true if t = 0
       SS(i, t) = false if t < 0 or i > n
       SS(i, t) = SS(i+1, t) if t < X[i]
       SS(i, t) = SS(i+1, t) V SS(i+1, t-X[i]) otherwise
    */
    public static boolean isSubsetSum(int [] nums, int target) {
        int n = nums.length;
        boolean [][] dp = new boolean[n+1][target+1];

        dp[n][0] = true;

        for(int t = 1; t <= target; t++) {
            dp[n][t] = false;
        }

        for(int i = n-1; i >= 0; i--) {
            dp[i][0] = true;
            for(int t = 1; t <= target; t++) {
                if(t < nums[i]) {
                    dp[i][t] = dp[i+1][t];
                } else {
                    dp[i][t] = dp[i+1][t] || dp[i+1][t-nums[i]];
                }
            }
        }
        return dp[0][target];
    }

    public static void main(String [] args) {
        System.out.println(isSubsetSum(new int[]{8, 6, 7, 5, 3, 10, 9}, 15));
        System.out.println(isSubsetSum(new int[]{8, 6, 7, 5, 3, 10, 9}, 10));
        System.out.println(isSubsetSum(new int[]{8, 6, 7, 5, 3, 10, 9}, 3));
        System.out.println(isSubsetSum(new int[]{8, 6, 7, 5, 3, 10, 9}, 48));
        System.out.println(isSubsetSum(new int[]{8, 6, 7, 5, 3, 10, 9}, 49));

        System.out.println(isSubsetSum(new int[]{3, 34, 4, 12, 5, 2}, 9));
        System.out.println(isSubsetSum(new int[]{11, 6, 5, 1, 7, 13, 12}, 15));
    }
}
