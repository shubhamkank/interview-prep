package com.interview.leetcode;

public class NumArray {

    //private int[][] dp;
    private int[] sum;

    public NumArray(int[] nums) {
        sum = new int[nums.length + 1];
        for(int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }

        //dp = new int[nums.length][nums.length];
        //populateDPMatrix(nums);
    }

    private void populateDPMatrix(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                if(i == j) {
                    dp[i][j] = nums[j];
                } else {
                    dp[i][j] = dp[i][j-1] + nums[j];
                }
            }
        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }

    public int sumRange2(int i, int j) {
        return dp[i][j];
    }

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[] {-2, 0, 3, -5, 2, -1});
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
    }
}
