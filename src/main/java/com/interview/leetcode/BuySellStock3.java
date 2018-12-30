package com.interview.leetcode;

import java.util.Arrays;

public class BuySellStock3 {

    /* Dynamic Programming - Bottom-up approach
       Time complexity: O(kn^2), Space complexity: O(kn)
       dp[k][i] - max profit with atmost k transactions upto day i
       dp[k][i] = max(dp[k][i-1], max(prices[i] - prices[j] + dp[k-1][j-1]))  j = 0..i-1
       =>  dp[k][i] = max(dp[k][i-1], prices[i] + max(dp[k-1][j-1] - prices[j]))  j = 0..i-1
     */
    public static int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int[][] dp = new int[3][n];

        for(int k = 1; k <= 2; k++) {
            for(int i = 1; i < n; i++) {
                int tempMax = Integer.MIN_VALUE;
                for(int j = 0; j <= i-1; j++) {
                    tempMax = Math.max(tempMax, (j == 0) ? -1 * prices[j] : dp[k-1][j-1] - prices[j]);
                }
                dp[k][i] = Math.max(dp[k][i-1], prices[i] + tempMax);
            }
        }
        return dp[2][n-1];
    }

    /* Dynamic Programming - Bottom-up approach
       Time complexity: O(kn), Space complexity: O(kn)
       dp[k][i] - max profit with atmost k transactions upto day i
       dp[k][i] = max(dp[k][i-1], max(prices[i] - prices[j] + dp[k-1][j-1]))  j = 0..i-1
       =>  dp[k][i] = max(dp[k][i-1], prices[i] + max(dp[k-1][j-1] - prices[j]))  j = 0..i-1
     */
    public static int maxProfit2(int[] prices) {
        if(prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int[][] dp = new int[3][n];

        for(int k = 1; k <= 2; k++) {
            //Here, tempMax saves time searching iteratively for each i => j E [0, i-1] max(dp[k-1][j-1] - prices[j])
            int tempMax = dp[k-1][0] - prices[0];
            for(int i = 1; i < n; i++) {
                dp[k][i] = Math.max(dp[k][i-1], prices[i] + tempMax);
                tempMax = Math.max(tempMax, dp[k-1][i-1] - prices[i]);
            }
        }
        return dp[2][n-1];
    }

    /* Dynamic Programming - Bottom-up approach
       Time complexity: O(kn), Space complexity: O(kn)
       dp[k][i] - max profit with atmost k transactions upto day i
       dp[k][i] = max(dp[k][i-1], max(prices[i] - prices[j] + dp[k-1][j-1]))  j = 0..i-1
       =>  dp[k][i] = max(dp[k][i-1], prices[i] - min(prices[j] - dp[k-1][j-1]))  j = 0..i-1
     */
    public static int maxProfit3(int[] prices) {
        if(prices == null || prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        int[][] dp = new int[3][n];
        int[] min = new int[3];

        Arrays.fill(min, prices[0]);

        for(int i = 1; i < n; i++) {
            for(int k = 1; k <= 2; k++) {
                min[k] = Math.min(min[k], prices[i] - dp[k - 1][i - 1]);
                dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min[k]);
            }
        }

        return dp[2][n-1];
    }

    /* Dynamic Programming - Bottom-up approach
       Time complexity: O(kn), Space complexity: O(k)
       dp[k][i] - max profit with atmost k transactions upto day i
       dp[k][i] = max(dp[k][i-1], max(prices[i] - prices[j] + dp[k-1][j-1]))  j = 0..i-1
       =>  dp[k][i] = max(dp[k][i-1], prices[i] - min(prices[j] - dp[k-1][j-1]))  j = 0..i-1
     */
    public static int maxProfit4(int[] prices) {
        if(prices == null || prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        int[] dp = new int[3];
        int[] min = new int[3];

        Arrays.fill(min, prices[0]);

        for(int i = 1; i < n; i++) {
            for(int k = 1; k <= 2; k++) {
                min[k] = Math.min(min[k], prices[i] - dp[k - 1]);
                dp[k] = Math.max(dp[k], prices[i] - min[k]);
            }
        }

        return dp[2];
    }

    /* Time complexity: O(n), Space complexity: O(1)
     */
    public static int maxProfit5(int[] prices) {
        if(prices == null || prices.length == 0) {
            return 0;
        }

        int buy1 = Integer.MAX_VALUE, buy2 = Integer.MAX_VALUE;
        int sell1 = 0, sell2 = 0;

        for(int i = 0; i < prices.length; i++) {
            buy1 = Math.min(buy1, prices[i]);
            sell1 = Math.max(sell1, prices[i] - buy1);
            buy2 = Math.min(buy2, prices[i] - sell1);
            sell2 = Math.max(sell2, prices[i] - buy2);
        }
        return sell2;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit5(new int[] {3,3,5,0,0,3,1,4}));
        System.out.println(maxProfit5(new int[] {1,2,3,4,5}));
        System.out.println(maxProfit5(new int[] {7,6,4,3,1}));
        System.out.println(maxProfit5(new int[] {}));
    }
}
