package com.interview.leetcode;

import java.util.Arrays;

public class BuySellStock4 {

    /* Dynamic Programming - Bottom-up approach
       Time complexity: O(kn), Space complexity: O(k)
       dp[k][i] - max profit with atmost k transactions upto day i
       dp[k][i] = max(dp[k][i-1], max(prices[i] - prices[j] + dp[k-1][j-1]))  j = 0..i-1
       =>  dp[k][i] = max(dp[k][i-1], prices[i] - min(prices[j] - dp[k-1][j-1]))  j = 0..i-1
     */
    public static int maxProfit4(int K, int[] prices) {
        if(prices == null || prices.length == 0) {
            return 0;
        }

        int n = prices.length;

        if(K >= n / 2) {
            return quickSolve(prices);
        }

        int[] dp = new int[K + 1];
        int[] min = new int[K + 1];

        Arrays.fill(min, prices[0]);

        for(int i = 1; i < n; i++) {
            for(int k = 1; k <= K; k++) {
                min[k] = Math.min(min[k], prices[i] - dp[k - 1]);
                dp[k] = Math.max(dp[k], prices[i] - min[k]);
            }
        }

        return dp[K];
    }

    private static int quickSolve(int[] prices) {
        int n = prices.length;
        int profit = 0;
        for(int i = 1; i < n; i++) {
            if(prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit5(new int[] {3,3,5,0,0,3,1,4}));
        System.out.println(maxProfit5(new int[] {1,2,3,4,5}));
        System.out.println(maxProfit5(new int[] {7,6,4,3,1}));
        System.out.println(maxProfit5(new int[] {}));
    }
}
