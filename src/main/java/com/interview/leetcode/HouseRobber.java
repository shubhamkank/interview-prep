package com.interview.leetcode;

public class HouseRobber {

    /* Time complexity: O(n), Space complexity: O(n) */
    public static int rob(int[] nums) {
        if(nums == null || nums.length == 0) {
             return 0;
        }

        int result = Integer.MIN_VALUE;
        int[] maxMoney = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            maxMoney[i] = Math.max(nums[i] + (i-2 < 0 ? 0 : maxMoney[i-2]), (i-1 < 0 ? 0 : maxMoney[i-1]));
            result = Math.max(result, maxMoney[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{1, 2, 3, 1}));
        System.out.println(rob(new int[]{2, 7, 9, 3, 1}));
        System.out.println(rob(new int[]{2, 1, 1, 2}));
    }
}
