package com.interview.interviewbit.arrays;
import java.util.*;


public class MaxSumSubarray {

    //Time complexity: O(n^2)
    public int maxSubArray(final List<Integer> A) {
        int result = Integer.MIN_VALUE;
        for(int i = 0; i < A.size(); i++) {
            int sum = 0;
            for(int j = i; j < A.size(); j++) {
                sum += A.get(j);
                result = Math.max(result, sum);
            }
        }
        return result;
    }

    //Dynamic programming: dp[i] = max(a[i], a[i] + dp[i-1])
    //dp[i] = max sum contiguous subarray ending at i
    //Time complexity: O(n)
    public int maxSubArray2(final List<Integer> A) {
        int globalMax = A.get(0);
        int localMax = A.get(0); //Max subarray ending at i

        for(int i = 1; i < A.size(); i++) {
            localMax = Math.max(A.get(i), A.get(i) + localMax); //either extend the max subarray or start a new one
            globalMax = Math.max(globalMax, localMax);
        }
        return globalMax;
    }
}
