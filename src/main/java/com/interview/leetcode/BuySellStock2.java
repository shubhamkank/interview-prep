package com.interview.leetcode;

public class BuySellStock2 {

    // Brute Force Method
    // Time complexity: O(n^n), Space complexity: O(n)
    public static int maxProfit(int[] prices) {
        return calculate(prices, 0);
    }

    private static int calculate(int [] prices, int start) {
        if(start >= prices.length) {
            return 0;
        }

        int max = 0;
        for(int i = start; i < prices.length; i++) {
            int maxProfit = 0;
            for(int j = i + 1; j < prices.length; j++) {
                if(prices[i] < prices[j]) {
                    int profit = calculate(prices, j+1) + prices[j] - prices[i];
                    if(profit > maxProfit) {
                        maxProfit = profit;
                    }
                }
            }
            if(maxProfit > max) {
                max = maxProfit;
            }
        }
        return max;
    }

    // Peak Valley Method
    // Time complexity: O(n), Space complexity: O(1)
    public static int maxProfit2(int[] prices) {
        int peak = prices[0];
        int valley = prices[0];

        int i = 0;
        int maxProfit = 0;

        while(i < prices.length - 1) {
            while(i < prices.length - 1 && prices[i] >= prices[i+1]) {
                i++;
            }
            valley = prices[i];

            while(i < prices.length - 1 && prices[i] <= prices[i+1]) {
                i++;
            }
            peak = prices[i];

            maxProfit += peak - valley;
        }
        return maxProfit;
    }

    // Peak Valley Method II
    // Time complexity: O(n), Space complexity: O(1)
    public static int maxProfit3(int[] prices) {
        int maxProfit = 0;

        for(int i = 0; i < prices.length - 1; i++) {
            if(prices[i] < prices[i+1]) {
                maxProfit += prices[i+1] - prices[i];
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit3(new int [] {7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfit3(new int [] {1, 2, 3, 4, 5}));
        System.out.println(maxProfit3(new int [] {7, 6, 4, 3, 1}));
    }
}
