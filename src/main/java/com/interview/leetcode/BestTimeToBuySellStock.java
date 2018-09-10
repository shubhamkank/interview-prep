package com.interview.leetcode;

public class BestTimeToBuySellStock {

    // Time complexity: O(n), Space complexity: O(1)
    public static int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) {
            return 0;
        }
        int size = prices.length;
        int rightMax = prices[size-1];
        int answer = 0;

        //Traverse from the left side updating right max
        //rightMax indicates maximum value to the right of the current element
        for(int i = size - 2; i >= 0; i--) {
            answer = Math.max(answer, rightMax - prices[i]);
            rightMax = Math.max(rightMax, prices[i]);
        }
        return answer;
    }

    //Brute Force
    //Time complexity: O(n^2), Space complexity: O(1)
    public static int maxProfit2(int[] prices) {
        if(prices == null) {
            return 0;
        }
        int maxProfit = 0;
        for(int i = 0; i < prices.length; i++) {
            for(int j = i+1; j < prices.length; j++) {
                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            }
        }
        return maxProfit;
    }

    //One Pass
    //Time complexity: O(n), Space complexity: O(1)
    public static int maxProfit3(int[] prices) {
        if(prices == null) {
            return 0;
        }
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        //Traverse right keeping account of the minPrice along the way
        for(int i = 0; i < prices.length; i++) {
            if(prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
        }
        return maxProfit;
    }

    public static void main(String [] args) {
        System.out.println(maxProfit3(new int [] {7,1,5,3,6,4}));
        System.out.println(maxProfit3(new int [] {7,6,4,3,1}));
    }
}
