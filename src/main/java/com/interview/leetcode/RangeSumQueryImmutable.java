package com.interview.leetcode;

public class RangeSumQueryImmutable {

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[] {-2, 0, 3, -5, 2, -1});
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
    }

    static class NumArray {

        int[] prefixSum;

        public NumArray(int[] nums) {
            if(nums == null || nums.length == 0) {
                return;
            }

            prefixSum = new int[nums.length];
            prefixSum[0] = nums[0];

            for(int i = 1; i < nums.length; i++) {
                prefixSum[i] = prefixSum[i - 1] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            if(i == 0) {
                return prefixSum[j];
            }
            return prefixSum[j] - prefixSum[i - 1];
        }
    }
}
