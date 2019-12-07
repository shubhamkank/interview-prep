package com.interview.leetcode;

public class MaxSubArray {

    public static int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;

        for(int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            maxSum = Math.max(sum, maxSum);
            for(int j = i+1; j < nums.length; j++) {
                sum += nums[j];
                maxSum = Math.max(sum, maxSum);
            }
        }
        return maxSum;
    }

    /**
     * Kadane's Algorithm: maxSum[i] = max(A[i], A[i] + maxSum[i-1])
     * It is same as Dynamic programming sub problem: max sum subarray ending at i which includes element at i
     * Time Complexity: O(n) Space Complexity: O(1)
     */
    public static int maxSubArray2(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int maxSum = nums[0];
        int maxGlobalSum = nums[0];

        for(int i = 1; i < nums.length; i++) {
            maxSum = Math.max(nums[i], maxSum + nums[i]);
            maxGlobalSum = Math.max(maxGlobalSum, maxSum);
        }
        return maxGlobalSum;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray2(new int [] {-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(maxSubArray2(new int [] {1, 2, 3, 4, 5}));
        System.out.println(maxSubArray2(new int [] {1, -2, -3, -4, -5}));
    }
}
