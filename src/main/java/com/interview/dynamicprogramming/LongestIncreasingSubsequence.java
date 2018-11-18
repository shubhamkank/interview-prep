package com.interview.dynamicprogramming;

import java.util.Arrays;

public class LongestIncreasingSubsequence {


    /* dp[i] = max(dp[j]) + 1   j = 0 to i-1
       dp[i] indicates longest increasing subsequence in the arr[0..i]
       necessarily including the element at ith index.
       Time complexity: O(n^2)
       Space complexity: O(n) */
    public static int longestIncreasingSubsequence(int [] arr) {
        if(arr == null || arr.length == 0) {
            return 0;
        }
        int [] dp = new int[arr.length];
        dp[0] = 1;

        int result = 1;

        for(int i = 1; i < arr.length; i++) {
            int maxValue = 0;
            for(int j = 0; j < i; j++) {
                if(arr[i] > arr[j]) {
                    maxValue = Math.max(maxValue, dp[j]);
                }
            }
            dp[i] = 1 + maxValue;
            //Max amongst all dp[i] for i = 0 to n-1
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    /* The idea is that as you iterate the sequence, you keep track of the minimum value a subsequence of given length
       might end with, for all so far possible subsequence lengths. So dp[i] is the minimum value a subsequence of
       length i+1 might end with. Having this info, for each new number we iterate to, we can determine the longest
       subsequence where it can be appended using binary search. The final answer is the length of the longest
       subsequence we found so far.
       Time complexity: O(nlogn)
       Space complexity: O(n) */
    public static int longestIncreasingSubsequence2(int [] arr) {
        if(arr == null || arr.length == 0) {
            return 0;
        }
        int [] dp = new int [arr.length];
        int len = 0;

        for(int num : arr) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if(i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if(i == len) {
                len++;
            }
        }
        return len;
    }

    public static void main(String[] args) {
        System.out.println(longestIncreasingSubsequence2(new int[] {10,9,2,5,3,7,101,18}));
    }
}
